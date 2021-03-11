package SortingStation;

import SortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.FinishedTruckUnload;
import event.UnloadTruckAndLoadInterimStorage;
import packageSorting.Pallet;

import java.util.ArrayList;

public class AutonomousVehicle {
    private EventBus eventBus;
    private ZS zs;
    private ParkingPlaceForAutonomousVehicle parkingPlaceForAutonomousVehicle;
    private ZoneForUnloadingTruck zoneForUnloadingTruck;
    private SortingSystem sortingSystem;
    private ArrayList<Pallet> palletsFromTruck;

    public AutonomousVehicle(EventBus eventBus, ZS zs, SortingSystem sortingSystem) {
        this.sortingSystem = sortingSystem;
        this.zs = zs;
        this.eventBus = eventBus;
        palletsFromTruck = new ArrayList<>();
    }

    public void post(Object object) {
        eventBus.post(object);
    }

    @Subscribe
    public void receive(UnloadTruckAndLoadInterimStorage event) {
        unloadTruck();
        loadInterimStorage();
        searchForFreeParkingSpace();
        zs.post(new FinishedTruckUnload());
    }

    private void searchForFreeParkingSpace(){
        for (var parkingSpace : parkingPlaceForAutonomousVehicle.getAutonomousVehicles()) {
            if(parkingSpace == null){
                parkingSpace = this;
                break;
            }
        }
    }

    private void loadInterimStorage() {
        for (int i = 0; i < Configuration.instance.numberOfPalletsPerTruck / 2; i++) {
            for (int j = 0; j < 2; j++) {
                sortingSystem.getInterimStorage().getPallets()[i][j] = palletsFromTruck.remove(0);
            }
        }
    }

    private void unloadTruck() {
        //TODO ich weiß nicht ob des array [2][5] oder umgekehrt ist
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                palletsFromTruck.add(zoneForUnloadingTruck.getTruck().getTrailer().getPallets()[i][j]);
            }
        }
    }
}
