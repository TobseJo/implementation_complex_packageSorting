package sortingStation.sortingSysten.sortingTracks;

import sortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;
import packageSorting.Package;
import packageSorting.Type;

public class ExpressSortingTrack extends SortingTrack {

    public ExpressSortingTrack(Scanner scanner, SortingTrack successor, SortingSystem sortingSystem) {
        super(scanner, sortingSystem);
        this.setSuccessor(successor);
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
}
