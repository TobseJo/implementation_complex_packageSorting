package SortingStation;

import com.google.common.eventbus.EventBus;

public class AutonomousCar {
    private EventBus eventBus;

    public AutonomousCar(EventBus eventBus){
        this.eventBus = eventBus;
    }
}
