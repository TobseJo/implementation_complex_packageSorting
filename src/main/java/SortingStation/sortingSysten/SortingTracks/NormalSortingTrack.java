package SortingStation.sortingSysten.SortingTracks;

import SortingStation.sortingSysten.SortingSystem;
import packageSorting.Package;
import packageSorting.Type;

public class NormalSortingTrack extends SortingTrack {
    public NormalSortingTrack(Scanner scanner, SortingTrack successor, SortingSystem sortingSystem) {
        super(scanner, sortingSystem);
        this.setSuccessor(successor);
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
}
