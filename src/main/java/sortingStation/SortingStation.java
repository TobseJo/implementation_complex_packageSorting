package sortingStation;

import com.google.common.eventbus.EventBus;
import employee.Employee;
import sortingStation.sortingSysten.SortingSystem;

import java.util.ArrayList;

public class SortingStation {
    private ArrayList<Employee> employees;
    private ZS zs;
    private ParkingPlaceForAutonomousVehicle parkingPlaceForAutonomousVehicle;
    private ZoneForUnloadingTruck[] zonesForUnloadingTrucks;
    private int amountOfTrucks;
    private SortingSystem sortingSystem;
    private EventBus eventBus;
    private Terminal terminal;
    private WaitingZone waitingZone = new WaitingZone();

    public SortingStation(ZS zs, ParkingPlaceForAutonomousVehicle parkingPlaceForAutonomousVehicle, ZoneForUnloadingTruck[] zonesForUnloadingTrucks, SortingSystem sortingSystem, EventBus eventBus, Terminal terminal, ArrayList<Employee> employees) {
        this.zs = zs;
        this.parkingPlaceForAutonomousVehicle = parkingPlaceForAutonomousVehicle;
        this.zonesForUnloadingTrucks = zonesForUnloadingTrucks;
        this.sortingSystem = sortingSystem;
        this.eventBus = eventBus;
        this.terminal = terminal;
        this.employees = employees;
        zs.setSortingStation(this);
        amountOfTrucks = 0;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public SortingSystem getSortingSystem() {
        return sortingSystem;
    }

    public ParkingPlaceForAutonomousVehicle getParkingPlaceForAutonomousVehicle() {
        return parkingPlaceForAutonomousVehicle;
    }

    public ZoneForUnloadingTruck[] getZonesForUnloadingTrucks() {
        return zonesForUnloadingTrucks;
    }

    public int getAmountOfTrucks() {
        return amountOfTrucks;
    }

    public void increaseAmountOfTrucks() {
        amountOfTrucks++;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public ZS getZs() {
        return zs;
    }

    public WaitingZone getWaitingZone() {
        return waitingZone;
    }
}
