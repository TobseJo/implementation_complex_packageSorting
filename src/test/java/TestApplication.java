import command.*;
import configuration.ObjectGenerator;
import event.Lock;
import org.junit.jupiter.api.*;
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
        objectGenerator = new ObjectGenerator();
        sortingStation = objectGenerator.generateSortingStation();
    }

    @Test
    @Order(1)
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
    @Order(2)
    public void initCommand() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new InitCommand(), sortingStation.getEmployees().get(0));
    }

    @Test
    @Order(3)
    public void nextCommand() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new InitCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new NextCommand(), sortingStation.getEmployees().get(0));
    }

    @Test
    @Order(4)
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
    @Order(5)
    public void lockCommand() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new LockCommand(), sortingStation.getEmployees().get(0));
        Assertions.assertEquals(sortingStation.getSortingSystem().getState(), Locked.class);
    }

    @Test
    @Order(6)
    public void unlockCommand() {
        Assertions.assertEquals(sortingStation.getSortingSystem().getState(), Unlocked.class);
        sortingStation.getTerminal().getTouchPad().takeCommand(new LockCommand(), sortingStation.getEmployees().get(0));
        sortingStation.getTerminal().getTouchPad().takeCommand(new UnlockCommand(), sortingStation.getEmployees().get(0));
        Assertions.assertEquals(sortingStation.getSortingSystem().getState(), Unlocked.class);
    }

    @Test
    @Order(7)
    public void showStatistics() {
        sortingStation.getTerminal().getTouchPad().takeCommand(new ShutdownCommand(), sortingStation.getEmployees().get(0));
    }

}
