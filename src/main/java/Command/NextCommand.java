package Command;

import SortingStation.ZS;
import event.Next;

public class NextCommand implements ICommand{

    @Override
    public void execute(ZS zs) {
        zs.post(new Next());
    }
}
