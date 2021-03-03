package Command;

import SortingStation.WaitingZone;
import configuration.ObjectGenerator;

public class InitCommand implements ICommand{

    private ObjectGenerator objectGenerator = new ObjectGenerator();
    private WaitingZone waitingZone;

    public InitCommand (WaitingZone waitingZone){
        this.waitingZone = waitingZone;
    }

    public void execute() {
        waitingZone.setTrucks(objectGenerator.generateTrucks());
    }
}
