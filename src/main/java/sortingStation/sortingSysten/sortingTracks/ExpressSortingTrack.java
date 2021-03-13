package sortingStation.sortingSysten.sortingTracks;

import sortingStation.sortingSysten.SortingSystem;
import packageSorting.Package;
import packageSorting.Type;

public class ExpressSortingTrack extends SortingTrack {

    public ExpressSortingTrack(Scanner scanner, SortingTrack successor, SortingSystem sortingSystem) {
        super(scanner, sortingSystem);
        this.setSuccessor(successor);
    }

    public void scan(Package currentPackage) {
        if (canHandlePackage(currentPackage, Type.EXPRESS)) {
            if(currentPackage.getType() != Type.EXPRESS){
                throw new RuntimeException("Fähler");
            }
            amountOfScannedPackages++;
            if (getScanner().scanForExplosive(currentPackage.getContentAsString())) {
                sortingSystem.getPackagesWithExplosive().add(currentPackage);
            }
            packages.add(currentPackage);
        } else {
            super.scan(currentPackage);
        }
    }
}
