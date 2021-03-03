package SortingStation;

import Command.ICommand;
import com.google.common.eventbus.EventBus;

public class ZS {

    private EventBus eventBus;
    private ICommand iCommand;

    public ZS(EventBus eventBus){
        this.eventBus = eventBus;
    }

    public ICommand getiCommand() {
        return iCommand;
    }

    public void setiCommand(ICommand iCommand) {
        this.iCommand = iCommand;
    }

    public void doCommand() {
        iCommand.execute();
    }
}
