package configuration;

import SortingStation.*;
import SortingStation.sortingSysten.SortingSystem;
import com.google.common.eventbus.EventBus;
import employee.*;
import employee.idCard.IDCard;
import packageSorting.Box;
import packageSorting.Package;
import packageSorting.Pallet;
import packageSorting.Truck;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ObjectGenerator {
    private ZS zs;
    private ParkingPlaceForAutonomousCars parkingPlaceForAutonomousCars;
    private ZoneForUnloadingTruck[] zonesForUnloadingTrucks;
    private SortingSystem sortingSystem;
    private SortingStation sortingStation;
    private FileReader fileReader;
    private EventBus eventBus;
    private Terminal terminal;

    private LinkedList<Package> packages;
    private LinkedList<Box> boxes;
    private LinkedList<Pallet> pallets;


    public ObjectGenerator() {
        fileReader = new FileReader();
        generatePackages();
        System.out.println("Packages generated");
        generateBoxes();
        System.out.println("Boxes generated");
        generatePallets();
        System.out.println("Pallets generated");
        generateTrucks();
        System.out.println("Trucks generated");
        generateSortingStation();
        System.out.println("SortingStation generated");
        generateAllEmployees();
        System.out.println("Employees generated");
    }

    public void generateSortingStation() {
        zs = new ZS(eventBus);
        eventBus = new EventBus();
        zs = new ZS(eventBus);
        parkingPlaceForAutonomousCars = new ParkingPlaceForAutonomousCars();
        zonesForUnloadingTrucks = new ZoneForUnloadingTruck[7];
        for (int i = 1; i <= 7; i++) {
            TruckDetector truckDetector = new TruckDetector();
            zonesForUnloadingTrucks[i] = new ZoneForUnloadingTruck(i, zs);
            truckDetector.addListener(zonesForUnloadingTrucks[i]);
        }
        sortingSystem = new SortingSystem();
        sortingStation = new SortingStation();
        terminal = new Terminal(new TouchPad(), new CardReader(), zs);

    }

    public void generatePackages() {
        packages = fileReader.readPackages("base_package.csv");
    }

    public void generateBoxes() {
        HashMap<String, Package> packageHashMap = new HashMap<>();

        for (Package packet : packages) {
            packageHashMap.put(packet.getId(), packet);
        }

        boxes = fileReader.readBoxes("base_box.csv", packageHashMap);

    }

    public void generatePallets() {
        HashMap<String, Box> boxHashMap = new HashMap<>();

        for (Box box : boxes) {
            boxHashMap.put(box.getId(), box);
        }

        pallets = fileReader.readPalettes("base_pallet.csv", boxHashMap);
    }

    public List<Truck> generateTrucks() {
        HashMap<Integer, Pallet> truckHashMap = new HashMap<>();

        for (Pallet pallet : pallets) {
            truckHashMap.put(pallet.getId(), pallet);
        }

        List<Truck> trucks = fileReader.readTruck("base_truck.csv", truckHashMap);

        return trucks;
    }

    public void generateAllEmployees() {
        Employee employee1 = new Administrator(1, "Johannes Hinkler", Profile.a, 1234, 123456);
        employee1.setIdCard(generateIDCardForEmployee(employee1));
        sortingStation.getEmployees().add(employee1);

        Employee employee2 = new DataAnalyst(2, "Peter Lustig", 1212, 112233);
        employee2.setIdCard(generateIDCardForEmployee(employee2));
        sortingStation.getEmployees().add(employee2);

        Employee employee3 = new Operator(3, "Daniel Atschi", 5895, 365821);
        employee3.setIdCard(generateIDCardForEmployee(employee3));
        sortingStation.getEmployees().add(employee3);

        Employee employee4 = new DataAnalyst(4, "Robin Drippy", 9452, 645789);
        employee4.setIdCard(generateIDCardForEmployee(employee4));
        sortingStation.getEmployees().add(employee4);

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

    public ParkingPlaceForAutonomousCars getParkingPlaceForAutonomousCars() {
        return parkingPlaceForAutonomousCars;
    }
}