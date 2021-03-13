package sortingStation.sortingSysten.sortingTracks;

import org.checkerframework.checker.units.qual.A;
import sortingStation.sortingSysten.SortingSystem;
import packageSorting.Type;
import packageSorting.Package;

import java.util.ArrayList;

public class SortingTrack {
    protected SortingTrack successor;
    protected Scanner scanner;
    protected int amountOfScannedPackages;
    protected SortingSystem sortingSystem;
    protected ArrayList<Package> packages;

    public SortingTrack(Scanner scanner, SortingSystem sortingSystem){
        this.sortingSystem = sortingSystem;
        this.scanner = scanner;
        packages = new ArrayList<>();
    }

    protected boolean canHandlePackage(Package currentPackage, Type currentTrackType){
            return (currentPackage != null) && (currentPackage.getType() == currentTrackType);
    }

    public void scan(Package currentPackage){
        if (getSuccessor() != null) {
            getSuccessor().scan(currentPackage);
        } else {
            System.out.println("Unable to find the correct sortingTrack for the package: " + currentPackage.getId());
        }
    }

    public String execute(String message){
        if(getSuccessor() != null){
            return getSuccessor().execute(message);
        }else {
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

    public ArrayList<Package> getPackages() {
        return packages;
    }
}
