package command;

import event.Lock;
import sortingStation.ZS;

public class LockCommand implements ICommand {
    @Override
    public void execute(ZS zs) {
        zs.post(new Lock());
    }
}
