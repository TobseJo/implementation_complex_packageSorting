package sortingStation.sortingSysten.state;

import sortingStation.sortingSysten.SortingSystem;

public class Unlocked implements IState{

    public void locked(SortingSystem sortingSystem) {
        throw new RuntimeException("Not allowed");
    }

    public void unlocked(SortingSystem sortingSystem){
        System.out.println("State set unlocked");
        sortingSystem.setState(new Unlocked());
    }
}
