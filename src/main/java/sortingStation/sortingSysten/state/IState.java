package sortingStation.sortingSysten.state;

import sortingStation.sortingSysten.SortingSystem;

public interface IState {
    void switchState(SortingSystem sortingSystem);
}
