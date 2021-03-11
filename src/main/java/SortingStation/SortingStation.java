package SortingStation;

import SortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;
import employee.Employee;

import java.util.ArrayList;

public class SortingStation {
    private ArrayList<Employee> employees;
    private ZS zs;
    private ParkingPlaceForAutonomousVehicle parkingPlaceForAutonomousVehicle;
    private ZoneForUnloadingTruck[] zonesForUnloadingTrucks;
    private SortingSystem sortingSystem;
    private EventBus eventBus;
    private Terminal terminal;

    public SortingStation(ZS zs, ParkingPlaceForAutonomousVehicle parkingPlaceForAutonomousVehicle, ZoneForUnloadingTruck[] zonesForUnloadingTrucks, SortingSystem sortingSystem, EventBus eventBus, Terminal terminal){
        employees = new ArrayList<>();
        this.zs = zs;
        this.parkingPlaceForAutonomousVehicle = parkingPlaceForAutonomousVehicle;
        this.zonesForUnloadingTrucks = zonesForUnloadingTrucks;
        this.sortingSystem = sortingSystem;
        this.eventBus = eventBus;
        this.terminal = terminal;

        zs.setSortingStation(this);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public SortingSystem getSortingSystem() {
        return sortingSystem;
    }
}
