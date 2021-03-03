package Command;

import SortingStation.ZS;
import event.Shutdown;

public class ShutdownCommand implements ICommand{
    @Override
    public void execute(ZS zs) {
        zs.post(new Shutdown());
    }
}
