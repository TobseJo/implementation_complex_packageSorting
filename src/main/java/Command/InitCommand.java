package Command;

import SortingStation.WaitingZone;
import SortingStation.ZS;
import configuration.ObjectGenerator;
import event.Init;

public class InitCommand implements ICommand{

    private ObjectGenerator objectGenerator = new ObjectGenerator();
    private WaitingZone waitingZone;

    public InitCommand (WaitingZone waitingZone){
        this.waitingZone = waitingZone;
    }

    public void execute(ZS zs) {
        zs.post(new Init());
    }
}
