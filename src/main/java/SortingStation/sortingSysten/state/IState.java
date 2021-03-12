package SortingStation.sortingSysten.state;

import SortingStation.sortingSysten.SortingSystem;

public interface IState {
    void switchState(SortingSystem sortingSystem);
}
