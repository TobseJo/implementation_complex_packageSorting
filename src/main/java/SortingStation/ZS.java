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
    public void receive(Next next){

    }

    @Subscribe
    public void receive(Shutdown shutdown){

    }

    @Subscribe
    public void receive(Lock lock){

    }

    @Subscribe
    public void receive(Unlock unlock){

    }

    @Subscribe
    public void receive(ShowStatistics showStatistics){

    }

    @Subscribe
    public void receive(ChangeSearchAlgorithm changeSearchAlogrithm){

    }
}
