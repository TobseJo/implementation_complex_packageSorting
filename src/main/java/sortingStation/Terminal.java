package sortingStation;

import command.ICommand;
import proxy.ProxyAccess;
import employee.Employee;

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
        touchPad.setTerminal(this);
    }

    public void tryToTakeCommand(ICommand command, Employee employee){
        ProxyAccess proxyAccess = new ProxyAccess(employee, command, this);
        proxyAccess.grant();
    }

    public void placeCommands() {
        for (ICommand command : commandList) {
            System.out.println(command.toString() + " is executed!");
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

    public ZS getZs() {
        return zs;
    }
}
