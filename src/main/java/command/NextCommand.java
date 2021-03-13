package command;

import event.Next;
import sortingStation.ZS;

public class NextCommand implements ICommand {

    @Override
    public void execute(ZS zs) {
        zs.post(new Next());
    }
}
