package event;

import sortingStation.sortingSysten.SortingSystem;

public class SortEveryThing {
    private SortingSystem sortingSystem;

    public SortEveryThing(SortingSystem sortingSystem) {
        this.sortingSystem = sortingSystem;
    }

    public String toString() {
        return "Event: SortEveryThing";
    }

    public SortingSystem getSortingSystem() {
        return sortingSystem;
    }
}
