package Command;

import SortingStation.ZS;
import event.ChangeSearchAlgorithm;

public class ChangeSearchAlgoCommandTo implements ICommand{

    public void execute(ZS zs, Object algorithm) {
        zs.post(new ChangeSearchAlgorithm(algorithm));
    }

    @Override
    public void execute(ZS zs) {
    }
}
