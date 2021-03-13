import command.*;
import configuration.Configuration;
import configuration.CsvFileGenerator;
import configuration.ObjectGenerator;
import org.junit.jupiter.api.*;
import items.Pallet;
import items.Truck;
import items.Type;
import sortingStation.AutonomousVehicle;
import sortingStation.SortingStation;
import sortingStation.ZoneForUnloadingTruck;
import sortingStation.sortingSysten.sortingTracks.SortingTrack;
import sortingStation.sortingSysten.state.Locked;
import sortingStation.sortingSysten.state.Unlocked;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplication {
    ObjectGenerator objectGenerator;
    SortingStation sortingStation;

    @BeforeEach
    public void init() {
        CsvFileGenerator csvFileGenerator = new CsvFileGenerator();
        csvFileGenerator.generateAllFiles();
        objectGenerator = new ObjectGenerator();
        sortingStation = objectGenerator.generateSortingStation();
    }

    @Test
    @Order(1)
    public void idCard() {
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
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 5; k++) {
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
            if (zone.getTruck() != null) {
                Assertions.fail();
            }
        }

    }

    @Test
    @Order(5)
    public void shutdownCommand() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new ShutdownCommand(), sortingStation.getEmployees().get(0));
        for (ZoneForUnloadingTruck zoneForUnloadingTruck : sortingStation.getZonesForUnloadingTrucks()) {
            Assertions.assertEquals(zoneForUnloadingTruck.getSensor().getState().getClass(), Locked.class);
        }
        for (SortingTrack sortingTrack : sortingStation.getSortingSystem().getSortingTracks()) {
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
        for (int i = 0; i < 5; i++) {
            sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));
        }
        sortingStation.getTerminal().getTouchPad().takeCommand(new ShowStatisticsCommand(), sortingStation.getEmployees().get(0));
        Assertions.assertEquals(5, sortingStation.getZs().getReport().getAmountOfTruck());
        Assertions.assertEquals(4, sortingStation.getZs().getReport().getDangerousPackages().size());
        Assertions.assertEquals(3600, sortingStation.getZs().getReport().getAmountOfScannedPackets().get(Type.EXPRESS));
        Assertions.assertEquals(19200, sortingStation.getZs().getReport().getAmountOfScannedPackets().get(Type.NORMAL));
        Assertions.assertEquals(1200, sortingStation.getZs().getReport().getAmountOfScannedPackets().get(Type.VALUE));

    }

    @Test
    @Order(9)
    public void palletsGetTransportedToInterimStorage() {
        int palletCtr = 0;
        sortingStation.getTerminal().getTouchPad().takeCommand(new InitCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new LockCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));

        int randomNumber = (int) (Math.random() * sortingStation.getZonesForUnloadingTrucks().length);
        ZoneForUnloadingTruck zone = sortingStation.getZonesForUnloadingTrucks()[randomNumber];
        zone.setTruck(sortingStation.getWaitingZone().getTrucks().pop());

        AutonomousVehicle autonomousVehicle = null;
        randomNumber = 0;
        while (autonomousVehicle == null) {
            randomNumber = (int) (Math.random() * 5);
            autonomousVehicle = sortingStation.getParkingPlaceForAutonomousVehicle().getAutonomousVehicles()[randomNumber];
        }
        sortingStation.getParkingPlaceForAutonomousVehicle().getAutonomousVehicles()[randomNumber] = null;
        for (var currentZone : sortingStation.getZonesForUnloadingTrucks()) {
            if (currentZone.getTruck() != null) {
                zone = currentZone;
                break;
            }
        }
        autonomousVehicle.setZoneForUnloadingTruck(zone);
        autonomousVehicle.unloadTruck();

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                Pallet pallet = autonomousVehicle.getZoneForUnloadingTruck().getTruck().getTrailer().getPallets()[i][j];
                if (pallet != null) {
                    palletCtr++;
                }

            }
        }
        Assertions.assertEquals(0, palletCtr);

        autonomousVehicle.getZoneForUnloadingTruck().setTruck(null);
        autonomousVehicle.loadInterimStorage();
        autonomousVehicle.searchForFreeParkingSpace();

        for (var pallets : sortingStation.getSortingSystem().getInterimStorage().getPallets()) {
            for (var pallet : pallets) {
                if (pallet != null) {
                    palletCtr++;
                }
            }
        }
        Assertions.assertEquals(10, palletCtr);
    }
}
