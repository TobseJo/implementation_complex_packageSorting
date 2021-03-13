package sortingStation.sortingSysten;

import packageSorting.Pallet;

import java.util.ArrayList;

public class StorageForEmptyPallets {
    private ArrayList<Pallet> pallets;

    public StorageForEmptyPallets() {
        pallets = new ArrayList<>();
    }

    public ArrayList<Pallet> getPallets() {
        return pallets;
    }
}
