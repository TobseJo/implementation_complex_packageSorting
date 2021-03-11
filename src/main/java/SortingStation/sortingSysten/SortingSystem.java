package SortingStation.sortingSysten;

import SortingStation.sortingSysten.SortingTracks.ExpressSortingTrack;
import SortingStation.sortingSysten.SortingTracks.NormalSortingTrack;
import SortingStation.sortingSysten.SortingTracks.SortingTrack;
import SortingStation.sortingSysten.SortingTracks.ValueSortingTrack;

public class SortingSystem {
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
        sortingTracks[0] = new NormalSortingTrack();
        sortingTracks[1] = new ExpressSortingTrack();
        sortingTracks[2] = new ValueSortingTrack();
    }

    public InterimStorage getInterimStorage() {
        return interimStorage;
    }
}
