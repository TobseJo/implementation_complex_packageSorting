import command.*;
import configuration.Configuration;
import configuration.ObjectGenerator;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import packageSorting.*;
import packageSorting.Package;
import sortingStation.SortingStation;
import sortingStation.ZoneForUnloadingTruck;
import sortingStation.sortingSysten.sortingTracks.NormalSortingTrack;
import sortingStation.sortingSysten.sortingTracks.SortingTrack;
import sortingStation.sortingSysten.state.Locked;
import sortingStation.sortingSysten.state.Unlocked;

import java.util.ArrayList;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplication {
    ObjectGenerator objectGenerator;
    SortingStation sortingStation;

    @BeforeEach
    public void init() {
        objectGenerator = new ObjectGenerator();
        sortingStation = objectGenerator.generateSortingStation();
    }

    @Test
    @Order(1)
    public void idCard(){
        sortingStation.getTerminal().getCardReader().readCardFromEmployee(sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getCardReader().readCardFromEmployee(sortingStation.getEmployees().get(1));
        sortingStation.getTerminal().getCardReader().readCardFromEmployee(sortingStation.getEmployees().get(2));
        sortingStation.getTerminal().getCardReader().readCardFromEmployee(sortingStation.getEmployees().get(3));
    }


    @Test
    @Order(2)
    public void setup() {
        Assertions.assertNotNull(sortingStation);
        Assertions.assertNotNull(sortingStation.getZs());
        Assertions.assertEquals(sortingStation.getZonesForUnloadingTrucks().length, 7);
        for (int i = 0; i < sortingStation.getZonesForUnloadingTrucks().length; i++) {
            Assertions.assertNotNull(sortingStation.getZonesForUnloadingTrucks()[i]);
        }
        Assertions.assertNotNull(sortingStation.getParkingPlaceForAutonomousVehicle());
        Assertions.assertEquals(sortingStation.getParkingPlaceForAutonomousVehicle().getAutonomousVehicles().length, 5);

        Assertions.assertNotNull(sortingStation.getSortingSystem());
        Assertions.assertNotNull(sortingStation.getSortingSystem().getSortingTracks());
        Assertions.assertEquals(sortingStation.getSortingSystem().getSortingTracks().length, 3);
        Assertions.assertNotNull(sortingStation.getSortingSystem().getInterimStorage());
        Assertions.assertNotNull(sortingStation.getSortingSystem().getRobot());
        Assertions.assertNotNull(sortingStation.getSortingSystem().getStorageForEmptyBoxes());
        Assertions.assertNotNull(sortingStation.getSortingSystem().getStorageForEmptyPallets());
        Assertions.assertNotNull(sortingStation.getSortingSystem().getWarehouseTracks());
        Assertions.assertEquals(sortingStation.getSortingSystem().getWarehouseTracks().length, 8);
    }

    @Test
    @Order(3)
    public void initCommand() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new InitCommand(), sortingStation.getEmployees().get(0));
        for (int i = 0; i < Configuration.instance.numberOfTrucks; i++) {
            Truck truck = sortingStation.getWaitingZone().getTrucks().peek();

            Assertions.assertNotNull(truck);
            for (int j = 0; j < 2; j++){
                for (int k = 0; k < 5; k++){
                    Assertions.assertNotNull(truck.getTrailer().getPallets()[j][k]);
                    for (int l = 0; l < 4; l++) {
                        for (int m = 0; m < 3; m++) {
                            Assertions.assertNotNull(truck.getTrailer().getPallets()[j][k].getBoxes()[l][m]);
                            Assertions.assertNotNull(truck.getTrailer().getPallets()[j][k].getBoxes()[l][m].getPackages());
                        }
                    }
                }
            }
        }
    }

    @Test
    @Order(4)
    public void nextCommand() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new InitCommand(), sortingStation.getEmployees().get(0));

        for (int i = 0; i < 5; i++) {
            sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));
        }
        for (var zone : sortingStation.getZonesForUnloadingTrucks()) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 5; j++) {
                    if(zone.getTruck() != null){
                        Assertions.assertNull(zone.getTruck().getTrailer().getPallets()[i][j]);
                    }
                }
            }
        }
    }

    @Test
    @Order(5)
    public void shutdownCommand() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new ShutdownCommand(), sortingStation.getEmployees().get(0));
        for(ZoneForUnloadingTruck zoneForUnloadingTruck : sortingStation.getZonesForUnloadingTrucks()){
            Assertions.assertEquals(zoneForUnloadingTruck.getSensor().getState().getClass(), Locked.class);
        }
        for (SortingTrack sortingTrack : sortingStation.getSortingSystem().getSortingTracks()){
            Assertions.assertNull(sortingTrack.getScanner().getUsedAlgorithm());
        }
    }

    @Test
    @Order(6)
    public void lockCommand() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new LockCommand(), sortingStation.getEmployees().get(0));
        Assertions.assertEquals(sortingStation.getSortingSystem().getState().getClass(), Locked.class);
    }

    @Test
    @Order(7)
    public void unlockCommand() {
        Assertions.assertEquals(sortingStation.getSortingSystem().getState().getClass(), Unlocked.class);
        sortingStation.getTerminal().getTouchPad().takeCommand(new LockCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new UnlockCommand(), sortingStation.getEmployees().get(0));
        Assertions.assertEquals(sortingStation.getSortingSystem().getState().getClass(), Unlocked.class);
    }

    @Test
    @Order(8)
    public void showStatistics() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new InitCommand(), sortingStation.getEmployees().get(0));
//        ArrayList<Package> packages = new ArrayList<>();
//        for(Truck truck : sortingStation.getWaitingZone().getTrucks()){
//            Pallet pallet;
//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < 5; j++) {
//                    pallet = truck.getTrailer().getPallets()[i][j];
//                        for (int k = 0; k < 4; k++) {
//                            for (int l = 0; l < 3; l++) {
//                                Box currentBox = pallet.getBoxes()[k][l];
//                                if(currentBox != null){
//
//                                    for (int m = 0; m < 5; m++) {
//                                        for (int n = 0; n < 2; n++) {
//                                            for (int o = 0; o < 4; o++) {
//                                                Package currentPackage = currentBox.getPackages()[m][n][o];
//                                                packages.add(currentPackage);
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//
//                    }
//                }
//            }
//        }
//        System.out.println(packages.size());
//        int value = 0;
//        int normal = 0;
//        int express = 0;
//
//        for (Package pack : packages) {
//            if(pack.getType() == Type.NORMAL){
//                normal++;
//            }else if(pack.getType() == Type.EXPRESS){
//                express++;
//            }else if (pack.getType() == Type.VALUE){
//                value ++;
//            }
//        }
//        System.out.println("Value: " + value);
//        System.out.println("e: " + normal);
//        System.out.println("n: " + express);
//        System.out.println();
        sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new ShowStatisticsCommand(), sortingStation.getEmployees().get(0));
//        int value1 = 0;
//        int normal1 = 0;
//        int express1 = 0;
//
//        NormalSortingTrack normalSortingTrack = (NormalSortingTrack) sortingStation.getSortingSystem().getSortingTracks()[2];
//
//        System.out.println(normalSortingTrack.getPackages2().size());
//
//        ArrayList<Package> expressList = new ArrayList<>();
//        ArrayList<Package> valueList = new ArrayList<>();
//        for (Package pack : normalSortingTrack.getPackages2()) {
//            if(pack.getType() == Type.NORMAL){
//                normal1++;
//            }else if(pack.getType() == Type.EXPRESS){
//                express1++;
//            }else if (pack.getType() == Type.VALUE){
//                valueList.add(pack);
//                value1++;
//            }
//        }
//        System.out.println(normalSortingTrack.getClass());
//        System.out.println("Value: " + value1);
//        System.out.println("e: " + express1);
//        System.out.println("n: " + normal1);
//
//
//        System.out.println("---------------------------------------------------------------");
//
//        int value2 = 0;
//        int normal2 = 0;
//        int express2 = 0;
//
//        for (Package pack : sortingStation.getSortingSystem().getRobot().getPackages()) {
//            if(pack.getType() == Type.NORMAL){
//                normal2++;
//            }else if(pack.getType() == Type.EXPRESS){
////                System.out.println(pack);
//                express2++;
//            }else if (pack.getType() == Type.VALUE){
//                value2++;
//            }
//        }
//        System.out.println(normalSortingTrack.getClass());
//        System.out.println("Robot Value: " + value2);
//        System.out.println("Robot e: " + express2);
//        System.out.println("Robot n: " + normal2);
    }

}
