package configuration;

import com.google.common.eventbus.EventBus;
import employee.*;
import employee.idCard.IDCard;
import packageSorting.Box;
import packageSorting.Package;
import packageSorting.Pallet;
import packageSorting.Truck;
import sortingStation.*;
import sortingStation.sortingSysten.SortingSystem;

import java.util.*;

public class ObjectGenerator {
    private ZS zs;
    private ParkingPlaceForAutonomousVehicle parkingPlaceForAutonomousVehicle;
    private ZoneForUnloadingTruck[] zonesForUnloadingTrucks;
    private TruckDetector[] truckDetectors;
    private SortingSystem sortingSystem;
    private SortingStation sortingStation;
    private FileReader fileReader;
    private EventBus eventBus;
    private Terminal terminal;

    public ObjectGenerator() {
        fileReader = new FileReader();
    }

    public SortingStation generateSortingStation() {
        //TODO einen eventBus oder jeder einen eigenen
        eventBus = new EventBus();
        zs = new ZS(eventBus, this);
        parkingPlaceForAutonomousVehicle = new ParkingPlaceForAutonomousVehicle(eventBus, zs, sortingSystem);
        zonesForUnloadingTrucks = new ZoneForUnloadingTruck[7];
        truckDetectors = new TruckDetector[7];
        for (int i = 0; i <= 6; i++) {
            truckDetectors[i] = new TruckDetector();
            zonesForUnloadingTrucks[i] = new ZoneForUnloadingTruck(i+1, zs);
            truckDetectors[i].addListener(zonesForUnloadingTrucks[i].getSensor());
        }
        sortingSystem = new SortingSystem(zs);
        terminal = new Terminal(new TouchPad(), new CardReader(), zs);
        sortingStation = new SortingStation(zs, parkingPlaceForAutonomousVehicle, zonesForUnloadingTrucks, sortingSystem, eventBus, terminal, generateAllEmployees());

        System.out.println("SortingStation generated");
        return sortingStation;
    }

    private LinkedList<Package> generatePackages() {
        LinkedList<Package> packages = fileReader.readPackages("base_package.csv");
        System.out.println("Packages generated");

        return packages;
    }

    private LinkedList<Box> generateBoxes() {
        LinkedList<packageSorting.Package> packages = generatePackages();

        HashMap<String, Package> packageHashMap = new HashMap<>();

        for (var pack : packages) {
            packageHashMap.put(pack.getId(), pack);
        }

        LinkedList<Box> boxes = fileReader.readBoxes("base_box.csv", packageHashMap);
        System.out.println("Boxes generated");
        return boxes;
    }

    private LinkedList<Pallet> generatePallets() {
        LinkedList<Box> boxes = generateBoxes();
        HashMap<String, Box> boxHashMap = new HashMap<>();

        for (Box box : boxes) {
            boxHashMap.put(box.getId(), box);
        }

        LinkedList<Pallet> pallets = fileReader.readPalettes("base_pallet.csv", boxHashMap);
        System.out.println("Pallets generated");
        return pallets;
    }

    public Stack<Truck> generateTrucks() {
        LinkedList<Pallet> pallets = generatePallets();
        HashMap<Integer, Pallet> truckHashMap = new HashMap<>();

        for (Pallet pallet : pallets) {
            truckHashMap.put(pallet.getId(), pallet);
        }

        Stack<Truck> trucks = fileReader.readTruck("base_truck.csv", truckHashMap);

        System.out.println("Trucks generated");
        return trucks;
    }

    private ArrayList<Employee> generateAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        Employee employee1 = new Administrator(1, "Johannes Hinkler", Profile.A, 1234, 123456);
        employee1.setIdCard(generateIDCardForEmployee(employee1));
        employees.add(employee1);

        Employee employee2 = new DataAnalyst(2, "Peter Lustig", 1212, 112233);
        employee2.setIdCard(generateIDCardForEmployee(employee2));
        employees.add(employee2);

        Employee employee3 = new Operator(3, "Daniel Atschi", 5895, 365821);
        employee3.setIdCard(generateIDCardForEmployee(employee3));
        employees.add(employee3);

        Employee employee4 = new DataAnalyst(4, "Robin Drippy", 9452, 645789);
        employee4.setIdCard(generateIDCardForEmployee(employee4));
        employees.add(employee4);

        System.out.println("Employees generated");
        return employees;
    }

    private IDCard generateIDCardForEmployee(Employee employee) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(employee.getId() + ";" + employee.getName() + ";" + getRoleOfEmployee(employee) + ";" + employee.getPin() + ";" + employee.getSuperPin());

        IDCard idCard = new IDCard(Configuration.instance.usedAlgorithm.encrypt(stringBuilder.toString()));
        return idCard;
    }

    private String getRoleOfEmployee(Employee employee) {
        String role;
        if (employee instanceof Administrator) {
            role = "Administrator";
        } else if (employee instanceof DataAnalyst) {
            role = "DataAnalyst";
        } else if (employee instanceof Operator) {
            role = "Operator";
        } else if (employee instanceof Supervisor) {
            role = "Supervisor";
        } else {
            role = "Error";
        }
        return role;
    }

    public ParkingPlaceForAutonomousVehicle getParkingPlaceForAutonomousVehicle() {
        return parkingPlaceForAutonomousVehicle;
    }

    public ZoneForUnloadingTruck[] getZonesForUnloadingTrucks() {
        return zonesForUnloadingTrucks;
    }
}