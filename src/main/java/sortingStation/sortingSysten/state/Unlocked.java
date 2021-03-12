package sortingStation.sortingSysten.state;

import sortingStation.sortingSysten.SortingSystem;

public class Unlocked implements IState{

    public void lock(SortingSystem sortingSystem) {
        System.out.println("State set locked");
        sortingSystem.setState(new Locked());
    }

    public void unlock(SortingSystem sortingSystem){
        System.out.println("State is already unlocked");
    }
}
