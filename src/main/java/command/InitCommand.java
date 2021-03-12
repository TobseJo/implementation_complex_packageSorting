package command;

import event.Init;
import sortingStation.ZS;

public class InitCommand implements ICommand {
    @Override
    public void execute(ZS zs) {
        zs.post(new Init());
    }
}
