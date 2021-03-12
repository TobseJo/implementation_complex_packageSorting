package sortingStation.sortingSysten.state;

import sortingStation.sortingSysten.SortingSystem;

public interface IState {
    void lock (SortingSystem sortingSystem);
    void unlock (SortingSystem sortingSystem);
}
