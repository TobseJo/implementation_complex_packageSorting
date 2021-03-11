package Proxy;

import Command.ICommand;
import Command.NextCommand;
import Command.ShowStatisticsCommand;
import Command.ShutdownCommand;
import SortingStation.Terminal;
import employee.*;

public class ProxyAccess implements IAccess {
    private Employee employee;
    private RealAccess realAccess;
    private Terminal terminal;
    private ICommand command;

    public ProxyAccess(Employee employee, ICommand command, Terminal terminal) {
        this.employee = employee;
        this.command = command;
        this.terminal = terminal;
    }

    @Override
    public void grant() {
        if (employee instanceof Supervisor) {
            giveRealAccess();
        } else if (employee instanceof Administrator && (command instanceof ShutdownCommand || command instanceof ShowStatisticsCommand)) {
            giveRealAccess();
        } else if (employee instanceof Operator && (command instanceof NextCommand || command instanceof ShowStatisticsCommand)) {
            giveRealAccess();
        } else if (employee instanceof DataAnalyst && command instanceof ShowStatisticsCommand) {
            giveRealAccess();
        } else {
            System.out.println("Access denied!");
        }
    }

    private void giveRealAccess() {
        realAccess = new RealAccess(employee, terminal, command);
        realAccess.grant();
    }
}
