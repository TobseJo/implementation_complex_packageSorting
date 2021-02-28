package Proxy;

import Command.ICommand;
import employee.Employee;

public class ProxyAccess implements IAccess{
    private Employee employee;
    private RealAccess realAccess;

    @Override
    public void grant() {

    }

}
