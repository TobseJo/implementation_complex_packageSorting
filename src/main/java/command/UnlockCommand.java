package command;

import sortingStation.ZS;
import event.Unlock;

public class UnlockCommand implements ICommand{
    @Override
    public void execute(ZS zs) {
        zs.post(new Unlock());
    }
}
