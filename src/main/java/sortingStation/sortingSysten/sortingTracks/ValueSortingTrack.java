package sortingStation.sortingSysten.sortingTracks;

import items.Package;
import items.Type;
import sortingStation.sortingSysten.SortingSystem;

public class ValueSortingTrack extends SortingTrack {
    public ValueSortingTrack(Scanner scanner, SortingSystem sortingSystem) {
        super(scanner, sortingSystem);
    }

    public void scan(Package currentPackage) {
        if (canHandlePackage(currentPackage, Type.VALUE)) {
            amountOfScannedPackages++;
            if (getScanner().scanForExplosive(currentPackage.getContentAsString())) {
                sortingSystem.getPackagesWithExplosive().add(currentPackage);
            }
        } else {
            super.scan(currentPackage);
        }
    }
}
