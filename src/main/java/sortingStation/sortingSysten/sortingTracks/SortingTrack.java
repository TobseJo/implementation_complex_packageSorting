package sortingStation.sortingSysten.sortingTracks;

import packageSorting.Package;
import packageSorting.Type;
import sortingStation.sortingSysten.SortingSystem;

public class SortingTrack {
    protected SortingTrack successor;
    protected Scanner scanner;
    protected int amountOfScannedPackages;
    protected SortingSystem sortingSystem;

    public SortingTrack(Scanner scanner, SortingSystem sortingSystem) {
        this.sortingSystem = sortingSystem;
        this.scanner = scanner;
    }

    protected boolean canHandlePackage(Package currentPackage, Type currentTrackType) {
        return (currentPackage != null) && (currentPackage.getType() == currentTrackType);
    }

    public void scan(Package currentPackage) {
        if (getSuccessor() != null) {
            getSuccessor().scan(currentPackage);
        } else {
            System.out.println("Unable to find the correct sortingTrack for the package: " + currentPackage.getId());
        }
    }

    public String execute(String message) {
        if (getSuccessor() != null) {
            return getSuccessor().execute(message);
        } else {
            System.out.println("unable to find the correct ControlCenter for the message : " + message);
            return null;
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public SortingTrack getSuccessor() {
        return successor;
    }

    public void setSuccessor(SortingTrack successor) {
        this.successor = successor;
    }

    public int getAmountOfScannedPackages() {
        return amountOfScannedPackages;
    }

    public SortingSystem getSortingSystem() {
        return sortingSystem;
    }

}
