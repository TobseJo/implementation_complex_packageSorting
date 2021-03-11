package SortingStation.sortingSysten;

import SortingStation.sortingSysten.state.IState;
import SortingStation.sortingSysten.SortingTracks.*;
import SortingStation.sortingSysten.state.Locked;
import SortingStation.sortingSysten.state.Unlocked;

public class SortingSystem {
    private IState state = new Unlocked();
    private InterimStorage interimStorage;
    private Robot robot;
    private StorageForEmptyBoxes storageForEmptyBoxes;
    private StorageForEmptyPallets storageForEmptyPallets;
    private WarehouseTrack[] warehouseTracks;
    private SortingTrack[] sortingTracks;

    public SortingSystem(){
        interimStorage = new InterimStorage();
        robot = new Robot();
        storageForEmptyBoxes = new StorageForEmptyBoxes();
        storageForEmptyPallets = new StorageForEmptyPallets();
        warehouseTracks = new WarehouseTrack[8];
        for (int i = 0; i < 8; i++) {
            warehouseTracks[i] = new WarehouseTrack();
        }
        sortingTracks = new SortingTrack[3];
        sortingTracks[0] = new NormalSortingTrack(new Scanner());
        sortingTracks[1] = new ExpressSortingTrack(new Scanner());
        sortingTracks[2] = new ValueSortingTrack(new Scanner());
    }

    public InterimStorage getInterimStorage() {
        return interimStorage;
    }

    public SortingTrack[] getSortingTracks() {
        return sortingTracks;
    }

    public void unlock(){
        setState(new Unlocked());
    }

    public void lock(){
        setState(new Locked());
    }

    public void setState(IState state) {
        this.state = state;
    }
}
