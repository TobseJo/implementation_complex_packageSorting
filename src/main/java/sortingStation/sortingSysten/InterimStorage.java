package sortingStation.sortingSysten;

import packageSorting.Pallet;

public class InterimStorage {
    private Pallet[][] pallets;

    public InterimStorage() {
        pallets = new Pallet[5][2];
    }

    public Pallet[][] getPallets() {
        return pallets;
    }
}
