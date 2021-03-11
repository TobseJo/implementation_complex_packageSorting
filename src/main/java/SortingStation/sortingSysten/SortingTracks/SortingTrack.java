package SortingStation.sortingSysten.SortingTracks;

import packageSorting.Box;

public class SortingTrack {
    protected SortingTrack successor;
    protected Scanner scanner;

    public SortingTrack(Scanner scanner){
        this.scanner = scanner;
    }

    public boolean canHandleBox(Box box){
//        return (box == null) || (box.get);
    return true;
    }

    public void scan(Box box){

    }

    public Scanner getScanner() {
        return scanner;
    }
}
