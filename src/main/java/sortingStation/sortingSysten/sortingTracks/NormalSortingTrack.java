package sortingStation.sortingSysten.sortingTracks;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import event.SortEveryThing;
import packageSorting.Package;
import packageSorting.Type;
import sortingStation.sortingSysten.SortingSystem;
import sortingStation.sortingSysten.WarehouseTrack;


public class NormalSortingTrack extends SortingTrack {
    private EventBus eventBus;

    public NormalSortingTrack(Scanner scanner, SortingTrack successor, SortingSystem sortingSystem, EventBus eventBus) {
        super(scanner, sortingSystem);
        this.setSuccessor(successor);
        this.eventBus = new EventBus();
        this.eventBus.register(this);
    }

    public void scan(Package currentPackage) {
        if (canHandlePackage(currentPackage, Type.NORMAL)) {
            amountOfScannedPackages++;
            if (getScanner().scanForExplosive(currentPackage.getContentAsString())) {
                sortingSystem.getPackagesWithExplosive().add(currentPackage);
            }
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
            while (!track.getPackageTrack().isEmpty()) {
                scan(track.getPackageTrack().poll());
            }
            track.setFull(false);
        }
    }
}
