package sortingStation.sortingSysten.sortingTracks;

import packageSorting.Package;
import packageSorting.Type;
import sortingStation.sortingSysten.SortingSystem;

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
