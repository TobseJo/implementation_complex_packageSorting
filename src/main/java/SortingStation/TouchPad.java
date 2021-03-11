package SortingStation;

import Command.*;
import event.ChangeSearchAlgorithm;

import java.util.List;

public class TouchPad {

    private Terminal terminal;

    public void init(InitCommand initCommand){
        terminal.takeCommand(initCommand);
    }

    public void showStatistics(ShowStatisticsCommand showStatisticsCommand){
        terminal.takeCommand(showStatisticsCommand);
    }

    public void unlockSortingSystem(UnlockCommand unlockCommand){
        terminal.takeCommand(unlockCommand);
    }

    public void lockSortingSystem(LockCommand lockCommand){
        terminal.takeCommand(lockCommand);
    }

    public void changeSearchAlgorithm(ChangeSearchAlgoCommandTo searchAlgorithmCommand){
        terminal.takeCommand(searchAlgorithmCommand);
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
