package sortingStation.sortingSysten.state;

import sortingStation.sortingSysten.SortingSystem;

public interface IState {
    void locked (SortingSystem sortingSystem);
    void unlocked (SortingSystem sortingSystem);
}
