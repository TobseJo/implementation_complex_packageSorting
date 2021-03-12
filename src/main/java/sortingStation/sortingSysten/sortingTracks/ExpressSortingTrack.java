package sortingStation.sortingSysten.sortingTracks;

import sortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import event.SortEveryThing;
import packageSorting.Package;
import packageSorting.Type;

public class ExpressSortingTrack extends SortingTrack {
    private EventBus eventBus;

    public ExpressSortingTrack(Scanner scanner, SortingTrack successor, SortingSystem sortingSystem, EventBus eventBus) {
        super(scanner, sortingSystem);
        this.setSuccessor(successor);
        this.eventBus = eventBus;
    }

    public void scan(Package currentPackage) {
        if (canHandlePackage(currentPackage, Type.EXPRESS)) {
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
        for (var track : event.getSortingSystem().getWarehouseTracks()) {
            while(!track.getPackageTrack().isEmpty()) {
                scan(track.getPackageTrack().poll());
            }
        }
    }
}
