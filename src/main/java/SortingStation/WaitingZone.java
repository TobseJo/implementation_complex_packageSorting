package SortingStation;

import packageSorting.Truck;

import java.util.PriorityQueue;
import java.util.Queue;

public class WaitingZone {

    private Queue<Truck> trucks = new PriorityQueue<>();


    public Queue<Truck> getTrucks() {
        return trucks;
    }

    public void setTrucks(Queue<Truck> trucks) {
        this.trucks = trucks;
    }
}
