package SortingStation;

import Command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
    private TouchPad touchPad;
    private CardReader cardReader;

    private List<ICommand> commandList = new ArrayList<>();

    public Terminal(TouchPad touchPad, CardReader cardReader) {
        this.touchPad = touchPad;
        this.cardReader = cardReader;
    }

    public void takeCommand(ICommand command){
        commandList.add(command);
    }

    public void placeCommands() {
        for (ICommand command : commandList) {
            command.execute();
        }
        commandList.clear();
    }
}
