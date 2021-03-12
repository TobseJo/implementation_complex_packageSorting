package SortingStation.sortingSysten.state;

import SortingStation.sortingSysten.SortingSystem;

public class Locked implements IState{

    public void switchState(SortingSystem sortingSystem) {
        System.out.println("State switched unlocked -> locked");
        sortingSystem.setState(new Locked());
    }
}
