package SortingStation.sortingSysten;

import packageSorting.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class WarehouseTrack {
    private Queue<Package> packageTrack;
    private ArrayList<ITrackObserver> listeners;
    private boolean isFull;

    public WarehouseTrack() {
        packageTrack = new PriorityQueue<>();
        listeners = new ArrayList<>();
        listeners.add( new SensorForFilling());
        isFull = false;
    }

    //returnt false bis es voll ist
    public boolean addToPackageTrack(Package currentPackage) {
        if (!isFull) {
            packageTrack.add(currentPackage);
            if (packageTrack.size() == 599) {
                notifyViaEventbus();
                isFull = true;
            }
        }
        return isFull;
    }

    public void addListener(ITrackObserver listener) {
        listeners.add(listener);
    }

    public void removeListener(ITrackObserver listener) {
        listeners.remove(listener);
    }

    public void notifyViaEventbus() {
        for (var listener : listeners) {
            listener.trackIsFull();
        }
    }

    public Queue<Package> getPackageTrack() {
        return packageTrack;
    }
}
