package SortingStation;

import SortingStation.sortingSysten.state.Unlocked;
import event.TruckArrivedSendVehicle;
import SortingStation.sortingSysten.state.IState;

public class Sensor implements ITruckArriving {
    private ZS zs;
    private int id;
    private IState state;

    public Sensor(ZS zs, int id){
        this.zs = zs;
        this.id = id;
        this.state = new Unlocked();
    }

    @Override
    public void informZsTruckArriving() {
        if(state instanceof Unlocked) {
            System.out.println("Truck is arriving!");
            zs.post(new TruckArrivedSendVehicle(id));
        }else {
            throw new RuntimeException("Sensor State is locked");
        }
    }

    public void setState(IState state) {
        this.state = state;
    }
}
