package sortingStation.sortingSysten.sortingTracks;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import event.SortEveryThing;
import sortingStation.sortingSysten.SortingSystem;
import packageSorting.Package;
import packageSorting.Type;

public class NormalSortingTrack extends SortingTrack {
    private EventBus eventBus;

    public NormalSortingTrack(Scanner scanner, SortingTrack successor, SortingSystem sortingSystem, EventBus eventBus) {
        super(scanner, sortingSystem);
        this.setSuccessor(successor);
        this.eventBus = eventBus;
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
        for (var track : event.getSortingSystem().getWarehouseTracks()) {
            while(!track.getPackageTrack().isEmpty()) {
                scan(track.getPackageTrack().poll());
            }
        }
    }
}
