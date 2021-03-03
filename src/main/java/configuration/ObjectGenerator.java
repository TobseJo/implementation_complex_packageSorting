package configuration;

import SortingStation.*;
import employee.*;
import employee.idCard.IDCard;
import packageSorting.Box;
import packageSorting.Package;
import packageSorting.Pallet;
import packageSorting.Truck;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ObjectGenerator {
    private ZS zs;
    private ParkingPlaceForAutonomousCars parkingPlaceForAutonomousCars;
    private ZoneForUnloadingTrucks[] zonesForUnloadingTrucks;
    private SortingSystem sortingSystem;
    private SortingStation sortingStation;
    private FileReader fileReader;
    private LinkedList<Package> packages;

    public void generateSortingStation() {
        zs = new ZS();
        fileReader = new FileReader();
        parkingPlaceForAutonomousCars = new ParkingPlaceForAutonomousCars();
        zonesForUnloadingTrucks = new ZoneForUnloadingTrucks[7];
        for (int i = 0; i < 7; i++) {
            zonesForUnloadingTrucks[i] = new ZoneForUnloadingTrucks();
        }
        sortingSystem = new SortingSystem();
        sortingStation = new SortingStation();
        LinkedList<Package> packages = fileReader.readPackages("base_package.csv");

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

    public LinkedList<Package>  generatePackages(){
        LinkedList<Package> packages = fileReader.readPackages("base_package.csv");

        return packages;
    }

    public LinkedList<Box> generateBoxes(){
        HashMap<String, Package> packageHashMap = new HashMap<>();

        for(Package paket : packages){
            packageHashMap.put(paket.getId(), paket);
        }

        LinkedList<Box> boxes = fileReader.readBoxes("base_box.csv", packageHashMap);

        return boxes;
    }

    public LinkedList<Pallet> generatePallets(){

    }

    public LinkedList<Truck> generateTrucks(){

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

}