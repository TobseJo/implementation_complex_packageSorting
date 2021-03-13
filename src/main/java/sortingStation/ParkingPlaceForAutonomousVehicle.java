package sortingStation;

import com.google.common.eventbus.EventBus;
import sortingStation.sortingSysten.SortingSystem;

public class ParkingPlaceForAutonomousVehicle {

    private AutonomousVehicle[] autonomousVehicles;

    public ParkingPlaceForAutonomousVehicle(EventBus eventBus, ZS zs, SortingSystem sortingSystem) {
        autonomousVehicles = new AutonomousVehicle[5];
        for (int i = 0; i < 5; i++) {
            autonomousVehicles[i] = new AutonomousVehicle(eventBus, zs, sortingSystem, this);
        }
    }

    public AutonomousVehicle[] getAutonomousVehicles() {
        return autonomousVehicles;
    }
}
