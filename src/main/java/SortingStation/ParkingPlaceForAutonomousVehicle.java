package SortingStation;

import SortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;

public class ParkingPlaceForAutonomousVehicle {

    private AutonomousVehicle[] autonomousVehicles;

    public ParkingPlaceForAutonomousVehicle(EventBus eventBus, ZS zs, SortingSystem sortingSystem){
        autonomousVehicles = new AutonomousVehicle[5];
        for (int i = 0; i < 5; i++) {
            autonomousVehicles[i] = new AutonomousVehicle(eventBus, zs, sortingSystem);
        }
    }

    public AutonomousVehicle[] getAutonomousVehicles() {
        return autonomousVehicles;
    }
}
