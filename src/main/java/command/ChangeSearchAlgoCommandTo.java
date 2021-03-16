package command;

import event.ChangeSearchAlgorithm;
import sortingStation.ZS;

public class ChangeSearchAlgoCommandTo implements ICommand {
    private String algorithm;

    public ChangeSearchAlgoCommandTo(String algorithm){
        this.algorithm = algorithm;
    }

    @Override
    public void execute(ZS zs) {
        zs.post(new ChangeSearchAlgorithm(algorithm));
    }
}
