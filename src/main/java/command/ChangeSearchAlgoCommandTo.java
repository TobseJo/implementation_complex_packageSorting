package command;

import event.ChangeSearchAlgorithm;
import sortingStation.ZS;

public class ChangeSearchAlgoCommandTo implements ICommand {
    public void execute(ZS zs, String algorithm) {
        zs.post(new ChangeSearchAlgorithm(algorithm));
    }

    @Override
    public void execute(ZS zs) {
    }
}
