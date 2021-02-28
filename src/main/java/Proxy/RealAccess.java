package Proxy;

import Command.ICommand;
import Reporter.Report;
import employee.Employee;

public class RealAccess implements IAccess {
    private Employee employee;
    private ICommand iCommand;

    @Override
    public void grant() {

    }

    public void doCommand(){

    }
}
