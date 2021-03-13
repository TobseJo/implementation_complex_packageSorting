package sortingStation.sortingSysten;

import items.Pallet;

public class InterimStorage {
    private Pallet[][] pallets;

    public InterimStorage() {
        pallets = new Pallet[5][2];
    }

    public Pallet[][] getPallets() {
        return pallets;
    }
}
