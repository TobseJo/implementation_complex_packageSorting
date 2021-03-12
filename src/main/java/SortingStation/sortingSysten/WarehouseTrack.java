package SortingStation.sortingSysten;

import packageSorting.Package;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class WarehouseTrack {
    private Queue<Package> packageTrack;
    private SensorForFilling sensor;

    public WarehouseTrack(SensorForFilling sensorForFilling) {
        packageTrack = new PriorityQueue<>();
        this.sensor = sensorForFilling;
    }

    public void addToPackageTrack(Package currentPackage) {
        packageTrack.add(currentPackage);
        if(packageTrack.size() == 599){
            sensor.trackIsFull();
        }
    }

}
