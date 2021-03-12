package sortingStation.sortingSysten.state;

import sortingStation.sortingSysten.SortingSystem;

public class Locked implements IState{

    public void unlocked(SortingSystem sortingSystem) {
        throw new RuntimeException("Not allowed");
    }

    public void locked(SortingSystem sortingSystem){
        System.out.println("State set locked");
        sortingSystem.setState(new Locked());
    }
}
