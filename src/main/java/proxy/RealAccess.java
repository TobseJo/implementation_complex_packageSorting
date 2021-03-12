package proxy;

import command.ICommand;
import sortingStation.Terminal;
import employee.Employee;

public class RealAccess implements IAccess {
    private Employee employee;
    private Terminal terminal;
    private ICommand command;

    public RealAccess(Employee employee, Terminal terminal, ICommand command) {
        this.employee = employee;
        this.terminal = terminal;
        this.command = command;
    }

    @Override
    public void grant() {
        System.out.println("Access granted for Employee");
        terminal.getCommandList().add(command);
        terminal.placeCommands();
    }
}
