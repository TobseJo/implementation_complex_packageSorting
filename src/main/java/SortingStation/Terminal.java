package SortingStation;

import Command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
    private TouchPad touchPad;
    private CardReader cardReader;
    private ZS zs;

    private List<ICommand> commandList = new ArrayList<>();

    public Terminal(TouchPad touchPad, CardReader cardReader, ZS zs) {
        this.touchPad = touchPad;
        this.cardReader = cardReader;
        this.zs = zs;

        touchPad.setCommandList(commandList);
    }

    public void placeCommands() {
        for (ICommand command : commandList) {
            command.execute(zs);
        }
        commandList.clear();
    }

    public List<ICommand> getCommandList() {
        return commandList;
    }

    public TouchPad getTouchPad() {
        return touchPad;
    }
}
