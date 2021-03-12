package SortingStation.sortingSysten.state;

import SortingStation.sortingSysten.SortingSystem;

public class Unlocked implements IState{

    public void switchState(SortingSystem sortingSystem) {
        System.out.println("State switched locked -> unlocked");
        sortingSystem.setState(new Unlocked());
    }
}
