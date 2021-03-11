package SortingStation;

import Command.ICommand;

import java.util.List;

public class TouchPad {

    private List<ICommand> commandList;

    public void takeCommand(ICommand command){
        commandList.add(command);
    }

    public void setCommandList(List<ICommand> commandList) {
        this.commandList = commandList;
    }
}
