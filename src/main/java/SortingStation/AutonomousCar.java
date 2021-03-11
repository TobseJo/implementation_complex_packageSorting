package SortingStation;

import SortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import event.UnloadTruckAndLoadInterimStorage;
import packageSorting.Pallet;

import java.util.ArrayList;

public class AutonomousCar {
    private EventBus eventBus;
    private ZoneForUnloadingTruck zoneForUnloadingTruck;
    private SortingSystem sortingSystem;
    private ArrayList<Pallet> palletsFromTruck;

    public AutonomousCar(EventBus eventBus, SortingSystem sortingSystem) {
        this.sortingSystem = sortingSystem;
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
        //Drive Back to parkingAutonomVehicle
        //sendEvent TruckIsUnloaded To ZS
        //TODO sind beide Eventbusse dieseleben und woher kommt die connection zum Parkplatz
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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                palletsFromTruck.add(zoneForUnloadingTruck.getTruck().getTrailer().getPallets()[i][j]);
            }
        }
    }
}
