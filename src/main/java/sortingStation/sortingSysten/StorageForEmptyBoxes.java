package sortingStation.sortingSysten;

import packageSorting.Box;

import java.util.ArrayList;

public class StorageForEmptyBoxes {
    private ArrayList<Box> boxes;

    public StorageForEmptyBoxes() {
        boxes = new ArrayList<>();
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }
}
