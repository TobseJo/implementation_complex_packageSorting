package sortingStation.sortingSysten;

import packageSorting.Package;
import sortingStation.ZS;

import java.util.ArrayList;
import java.util.LinkedList;
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
        listeners.add(new SensorForFilling(zs, id));
        isFull = false;
    }

    public void addToPackageTrack(Package currentPackage) {
        if (!isFull) {
            packageTrack.add(currentPackage);
            if (packageTrack.size() < 600) {
            } else {
                notifyViaEventbus();
                isFull = true;
            }
        }
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

    public void setFull(boolean full) {
        isFull = full;
    }
}
