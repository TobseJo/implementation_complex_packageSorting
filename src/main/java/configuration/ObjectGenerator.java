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

    public IDCard generateIDCardForEmployee(Employee employee) {
        IDCard idCard = new IDCard();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(employee.getId()+";"+employee.getName()+";"+getRoleOfEmployee(employee)+";"+employee.getPin()+";"+employee.getSuperPin());
        String magnetStripe = stringBuilder.toString();
        if(magnetStripe.length() < 100){
            for (int i = 0; i < magnetStripe.length(); i++) {
                idCard.getMagnetStripe()[i][1] = magnetStripe.charAt(i);
            }
        }else{
            new RuntimeException("Error magnetStripe too long!");
        }
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