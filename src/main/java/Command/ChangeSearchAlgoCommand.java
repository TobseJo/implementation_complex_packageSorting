package Command;

import SortingStation.ZS;
import event.ChangeSearchAlgorithm;

public class ChangeSearchAlgoCommand implements ICommand{

    @Override
    public void execute(ZS zs) {
        zs.post(new ChangeSearchAlgorithm());
    }
}
