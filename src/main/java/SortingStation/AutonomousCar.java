package SortingStation;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import event.UnloadTruck;

public class AutonomousCar {
    private EventBus eventBus;

    public AutonomousCar(EventBus eventBus){
        this.eventBus = eventBus;
    }

    public void post(Object object){
        eventBus.post(object);
    }

    @Subscribe
    public void receive(UnloadTruck event){

    }
}
