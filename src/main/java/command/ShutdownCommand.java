package command;

import event.Shutdown;
import sortingStation.ZS;

public class ShutdownCommand implements ICommand {
    @Override
    public void execute(ZS zs) {
        zs.post(new Shutdown());
    }
}
