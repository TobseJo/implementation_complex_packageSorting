package sortingStation.sortingSysten;

import packageSorting.Package;
import sortingStation.ZS;
import sortingStation.sortingSysten.sortingTracks.*;
import sortingStation.sortingSysten.state.IState;
import sortingStation.sortingSysten.state.Unlocked;

import java.util.ArrayList;

public class SortingSystem {
    private IState state;
    private InterimStorage interimStorage;
    private Robot robot;
    private StorageForEmptyBoxes storageForEmptyBoxes;
    private StorageForEmptyPallets storageForEmptyPallets;
    private WarehouseTrack[] warehouseTracks;
    private SortingTrack[] sortingTracks;
    private ArrayList<Package> packagesWithExplosive;

    public SortingSystem(ZS zs) {
        interimStorage = new InterimStorage();
        packagesWithExplosive = new ArrayList<>();
        storageForEmptyBoxes = new StorageForEmptyBoxes();
        storageForEmptyPallets = new StorageForEmptyPallets();
        warehouseTracks = new WarehouseTrack[8];
        for (int i = 0; i < 8; i++) {
            warehouseTracks[i] = new WarehouseTrack(zs, i);
        }
        sortingTracks = new SortingTrack[3];
        sortingTracks[0] = new ValueSortingTrack(new Scanner(), this);
        sortingTracks[1] = new ExpressSortingTrack(new Scanner(), sortingTracks[0], this, zs.getEventBus());
        sortingTracks[2] = new NormalSortingTrack(new Scanner(), sortingTracks[1], this);
        robot = new Robot(zs, this);
        state = new Unlocked();
    }

    public InterimStorage getInterimStorage() {
        return interimStorage;
    }

    public void setInterimStorage(InterimStorage interimStorage) {
        this.interimStorage = interimStorage;
    }

    public Robot getRobot() {
        return robot;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }

    public StorageForEmptyBoxes getStorageForEmptyBoxes() {
        return storageForEmptyBoxes;
    }

    public void setStorageForEmptyBoxes(StorageForEmptyBoxes storageForEmptyBoxes) {
        this.storageForEmptyBoxes = storageForEmptyBoxes;
    }

    public StorageForEmptyPallets getStorageForEmptyPallets() {
        return storageForEmptyPallets;
    }

    public void setStorageForEmptyPallets(StorageForEmptyPallets storageForEmptyPallets) {
        this.storageForEmptyPallets = storageForEmptyPallets;
    }

    public WarehouseTrack[] getWarehouseTracks() {
        return warehouseTracks;
    }

    public SortingTrack[] getSortingTracks() {
        return sortingTracks;
    }

    public void setWarehouseTracks(WarehouseTrack[] warehouseTracks) {
        this.warehouseTracks = warehouseTracks;
    }

    public void setSortingTracks(SortingTrack[] sortingTracks) {
        this.sortingTracks = sortingTracks;
    }

    public void switchState() {
        state.switchState(this);
    }

    public void setState(IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }

    public ArrayList<Package> getPackagesWithExplosive() {
        return packagesWithExplosive;
    }


}
