package SortingStation.sortingSysten.SortingTracks;

import packageSorting.Box;

public abstract class SortingTrack {
    protected SortingTrack successor;

    public boolean canHandleBox(Box box){
        return true;
    }

    public void scan(Box box){

    }
}
