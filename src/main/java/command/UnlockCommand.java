package command;

import event.Unlock;
import sortingStation.ZS;

public class UnlockCommand implements ICommand {
    @Override
    public void execute(ZS zs) {
        zs.post(new Unlock());
    }
}
