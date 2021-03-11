package SortingStation;

import SortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;
import configuration.FileReader;
import employee.Employee;

import java.util.ArrayList;

public class SortingStation {
    private ArrayList<Employee> employees;
    private ZS zs;
    private ParkingPlaceForAutonomousCars parkingPlaceForAutonomousCars;
    private ZoneForUnloadingTruck[] zonesForUnloadingTrucks;
    private SortingSystem sortingSystem;
    private EventBus eventBus;

    public SortingStation(ZS zs, ParkingPlaceForAutonomousCars parkingPlaceForAutonomousCars, ZoneForUnloadingTruck[] zonesForUnloadingTrucks, SortingSystem sortingSystem, EventBus eventBus){
        employees = new ArrayList<>();
        this.zs = zs;
        this.parkingPlaceForAutonomousCars = parkingPlaceForAutonomousCars;
        this.zonesForUnloadingTrucks = zonesForUnloadingTrucks;
        this.sortingSystem = sortingSystem;
        this.eventBus = eventBus;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
