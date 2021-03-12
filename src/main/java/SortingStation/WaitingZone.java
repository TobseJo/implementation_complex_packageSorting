package SortingStation;

import packageSorting.Truck;

import java.util.List;
import java.util.Stack;

public class WaitingZone {

    private Stack<Truck> trucks = new Stack<>();

    public Stack<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(Stack<Truck> trucks) {
        this.trucks = trucks;
    }
}
