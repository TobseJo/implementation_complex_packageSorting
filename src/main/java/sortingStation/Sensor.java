package sortingStation;

import event.TruckArrivedSendVehicle;
import sortingStation.sortingSysten.state.IState;
import sortingStation.sortingSysten.state.Unlocked;

public class Sensor implements ITruckArriving {
    private ZS zs;
    private int id;
    private IState state;

    public Sensor(ZS zs, int id) {
        this.zs = zs;
        this.id = id;
        this.state = new Unlocked();
    }

    @Override
    public void informZsTruckArriving() {
        if (state instanceof Unlocked) {
            System.out.println("Truck is arriving!");
            zs.getSortingStation().increaseAmountOfTrucks();
            zs.post(new TruckArrivedSendVehicle(id));
        } else {
            throw new RuntimeException("Sensor State is locked");
        }
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }
}
