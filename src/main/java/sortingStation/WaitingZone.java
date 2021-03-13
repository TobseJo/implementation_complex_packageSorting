package sortingStation;

import items.Truck;

import java.util.Stack;

public class WaitingZone {
    private Stack<Truck> trucks;

    public WaitingZone() {
        trucks = new Stack<>();
    }

    public Stack<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(Stack<Truck> trucks) {
        this.trucks = trucks;
    }
}
