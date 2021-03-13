package sortingStation.sortingSysten.state;

import sortingStation.sortingSysten.SortingSystem;

public class Locked implements IState {

    public void unlock(SortingSystem sortingSystem) {
        System.out.println("State set unlocked");
        sortingSystem.setState(new Unlocked());
    }

    public void lock(SortingSystem sortingSystem) {
        System.out.println("State is already locked");
    }
}
