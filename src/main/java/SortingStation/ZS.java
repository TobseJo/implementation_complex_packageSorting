package SortingStation;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import configuration.ObjectGenerator;
import event.*;

public class ZS {

    private ObjectGenerator objectGenerator = new ObjectGenerator();
    private EventBus eventBus;
    private WaitingZone waitingZone;

    public ZS(EventBus eventBus){
        this.eventBus = eventBus;
        this.waitingZone = new WaitingZone();
    }

    public void post(Object object){
        eventBus.post(object);
    }

    @Subscribe
    public void receive(Init event){
        waitingZone.setTrucks(objectGenerator.generateTrucks());
    }

    @Subscribe
    public void receive(Next event){

    }

    @Subscribe
    public void receive(Shutdown event){

    }

    @Subscribe
    public void receive(Lock event){

    }

    @Subscribe
    public void receive(Unlock event){

    }

    @Subscribe
    public void receive(ShowStatistics event){

    }

    @Subscribe
    public void receive(ChangeSearchAlgorithm event){

    }
//    public void sendEventToRandomVehicle(int id) {
//        eventBus.post();
//    }
}
