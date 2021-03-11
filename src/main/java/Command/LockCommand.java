package Command;

import SortingStation.ZS;
import event.Lock;

public class LockCommand implements ICommand{
    @Override
    public void execute(ZS zs) {
        zs.post(new Lock());
    }
}
