package sortingStation.sortingSysten.sortingTracks;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import event.SortEveryThing;
import org.checkerframework.checker.units.qual.A;
import sortingStation.sortingSysten.SortingSystem;
import packageSorting.Package;
import packageSorting.Type;
import sortingStation.sortingSysten.WarehouseTrack;

import java.util.ArrayList;

public class NormalSortingTrack extends SortingTrack {
    private EventBus eventBus;
    private ArrayList<Package> packages2 = new ArrayList<>();

    public NormalSortingTrack(Scanner scanner, SortingTrack successor, SortingSystem sortingSystem, EventBus eventBus) {
        super(scanner, sortingSystem);
        this.setSuccessor(successor);
        this.eventBus = new EventBus();
        this.eventBus.register(this);
    }

    public void scan(Package currentPackage) {
        if (canHandlePackage(currentPackage, Type.NORMAL)) {
            if(currentPackage.getType() != Type.NORMAL){
                System.out.println(currentPackage.getType());
                throw new RuntimeException("Fähler");
            }
            amountOfScannedPackages++;
            if (getScanner().scanForExplosive(currentPackage.getContentAsString())) {
                sortingSystem.getPackagesWithExplosive().add(currentPackage);
            }
//            packages.add(currentPackage);
        } else {
            super.scan(currentPackage);
        }
    }

    public void post(Object object) {
        eventBus.post(object);
    }

    @Subscribe
    public void receive(SortEveryThing event) {
        System.out.println(event);
        for (WarehouseTrack track : event.getSortingSystem().getWarehouseTracks()) {
            while(!track.getPackageTrack().isEmpty()) {
                Package packagee = track.getPackageTrack().poll();
                scan(packagee);
                packages2.add(packagee);
            }
            track.setFull(false);
        }
    }

    public ArrayList<Package> getPackages2() {
        return packages2;
    }
}
