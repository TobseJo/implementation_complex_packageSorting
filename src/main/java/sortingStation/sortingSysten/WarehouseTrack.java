package sortingStation.sortingSysten;

import sortingStation.ZS;
import packageSorting.Package;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class WarehouseTrack {
    private ZS zs;
    private Queue<Package> packageTrack;
    private ArrayList<ITrackObserver> listeners;
    private boolean isFull;

    public WarehouseTrack(ZS zs, int id) {
        this.zs = zs;
        packageTrack = new LinkedList<>();
        listeners = new ArrayList<>();
        listeners.add( new SensorForFilling(zs, id));
        isFull = false;
    }

    //returnt false bis es voll ist
    public boolean addToPackageTrack(Package currentPackage) {
        if (!isFull) {
            packageTrack.add(currentPackage);
            if (packageTrack.size() < 600) {
            }else {
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
