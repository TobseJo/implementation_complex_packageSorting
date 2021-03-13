package sortingStation;

import command.ICommand;
import employee.Employee;

public class TouchPad {

    private Terminal terminal;

    public void takeCommand(ICommand command, Employee employee) {
        terminal.tryToTakeCommand(command, employee);
    }


    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
