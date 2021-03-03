package Proxy;

import Command.ICommand;
import Command.NextCommand;
import Command.ShowStatisticsCommand;
import Command.ShutdownCommand;
import SortingStation.ZS;
import employee.*;

import javax.xml.crypto.Data;

public class ProxyAccess implements IAccess{
    private Employee employee;
    private RealAccess realAccess;
    private ZS zs;

    public ProxyAccess(Employee employee, ZS zs){
        this.employee = employee;
        this.zs =zs;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void grant() {
        if(employee instanceof Supervisor){
            realAccess = new RealAccess(employee, zs);
            realAccess.grant();
        }else if(employee instanceof Administrator && (zs.getiCommand() instanceof ShutdownCommand || zs.getiCommand() instanceof ShowStatisticsCommand)){
            realAccess = new RealAccess(employee, zs);
            realAccess.grant();
        }else if(employee instanceof Operator && (zs.getiCommand() instanceof NextCommand || zs.getiCommand() instanceof ShowStatisticsCommand)){
            realAccess = new RealAccess(employee, zs);
            realAccess.grant();
        }else if(employee instanceof DataAnalyst && zs.getiCommand() instanceof ShowStatisticsCommand){
            realAccess = new RealAccess(employee, zs);
            realAccess.grant();
        }else{
            System.out.println("Access denied!");
        }
    }

}
