package SortingStation.sortingSysten.SortingTracks;

import SortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;
import packageSorting.Package;
import packageSorting.Type;

public class ExpressSortingTrack extends SortingTrack{
    private EventBus eventBus;

    public ExpressSortingTrack(Scanner scanner, SortingTrack successor, SortingSystem sortingSystem) {
        super(scanner, sortingSystem);
        this.setSuccessor(successor);
        eventBus = new EventBus();
    }

    public void scan(Package currentPackage){
        if(canHandlePackage(currentPackage, Type.EXPRESS)){
            amountOfScannedPackages++;
            if(getScanner().scanForExplosive(currentPackage.getContentAsString())){
                sortingSystem.getPackagesWithExplosive().add(currentPackage);
            }
        }else{
            super.scan(currentPackage);
        }
    }

}
