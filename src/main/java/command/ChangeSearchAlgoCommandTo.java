package command;

import sortingStation.ZS;
import event.ChangeSearchAlgorithm;

public class ChangeSearchAlgoCommandTo implements ICommand{

    public void execute(ZS zs, String algorithm) {
        zs.post(new ChangeSearchAlgorithm(algorithm));
    }

    @Override
    public void execute(ZS zs) {
    }
}
