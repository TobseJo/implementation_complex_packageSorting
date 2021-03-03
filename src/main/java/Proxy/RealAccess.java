package Proxy;

import SortingStation.ZS;
import employee.Employee;

public class RealAccess implements IAccess {
    private Employee employee;
    private ZS zs;

    public RealAccess(Employee employee, ZS zs) {
        this.employee = employee;
        this.zs = zs;
    }

    @Override
    public void grant() {
        zs.doCommand();
    }

    public void doCommand(){

    }
}
