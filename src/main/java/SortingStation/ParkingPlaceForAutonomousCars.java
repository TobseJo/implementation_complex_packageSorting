package SortingStation;

import SortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;

public class ParkingPlaceForAutonomousCars {

    private AutonomousCar[] autonomousCars;

    public ParkingPlaceForAutonomousCars(EventBus eventBus, ZS zs, SortingSystem sortingSystem){
        autonomousCars = new AutonomousCar[5];
        for (int i = 0; i < 5; i++) {
            autonomousCars[i] = (new AutonomousCar(eventBus, zs, sortingSystem));
        }
    }

    public AutonomousCar[] getAutonomousCars() {
        return autonomousCars;
    }
}
