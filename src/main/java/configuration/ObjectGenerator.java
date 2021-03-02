package configuration;

import SortingStation.*;
import employee.*;
import employee.idCard.IDCard;

public class ObjectGenerator {
    private ZS zs;
    private ParkingPlaceForAutonomousCars parkingPlaceForAutonomousCars;
    private ZoneForUnloadingTrucks[] zonesForUnloadingTrucks;
    private SortingSystem sortingSystem;
    private SortingStation sortingStation;

    public void generateSortingStation() {
        zs = new ZS();
        parkingPlaceForAutonomousCars = new ParkingPlaceForAutonomousCars();
        zonesForUnloadingTrucks = new ZoneForUnloadingTrucks[7];
        for (int i = 0; i < 7; i++) {
            zonesForUnloadingTrucks[i] = new ZoneForUnloadingTrucks();
        }
        sortingSystem = new SortingSystem();
        sortingStation = new SortingStation();
    }

    public void generateAllEmployees(){
        Employee employee1 = new Administrator(1, "Johannes Winkler", Profile.a, 1234, 123456);
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

    public IDCard generateIDCardForEmployee(Employee employee) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(employee.getId()+";"+employee.getName()+";"+getRoleOfEmployee(employee)+";"+employee.getPin()+";"+employee.getSuperPin());
        String employeeDataString = stringBuilder.toString();
        String encryptedString = Configuration.instance.usedAlgorithm.encrypt(employeeDataString);
        IDCard idCard = new IDCard(encryptedString);

        return idCard;
    }

    private String getRoleOfEmployee(Employee employee) {
        String role;
        if (employee instanceof Administrator) {
            role = "Administator";
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