package sortingStation.sortingSysten.sortingTracks;

import sortingStation.sortingSysten.SortingSystem;
import packageSorting.Package;
import packageSorting.Type;

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
