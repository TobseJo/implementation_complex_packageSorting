import SortingStation.SortingStation;
import command.InitCommand;
import configuration.ObjectGenerator;
import org.junit.jupiter.api.*;
import sortingStation.SortingStation;
import sortingStation.sortingSysten.sortingTracks.SortingTrack;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplication {
    ObjectGenerator objectGenerator;

    @BeforeEach
    public void init() {
        objectGenerator = new ObjectGenerator();
    }

    @Test
    @Order(1)
    public void setup() {
        SortingStation sortingStation = objectGenerator.generateSortingStation();

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
        Assertions.assertNotNull(sortingStation.getSortingSystem().getPackagesWithExplosive());
        Assertions.assertNotNull(sortingStation.getSortingSystem().getStorageForEmptyBoxes());
        Assertions.assertNotNull(sortingStation.getSortingSystem().getStorageForEmptyPallets());
        Assertions.assertNotNull(sortingStation.getSortingSystem().getWarehouseTracks());
        Assertions.assertEquals(sortingStation.getSortingSystem().getWarehouseTracks().length, 8);
    }

    @Test
    @Order(2)
    public void initCommand() {
        SortingStation sortingStation = objectGenerator.generateSortingStation();
//        sortingStation.getTerminal().getTouchPad().takeCommand(new InitCommand());
    }

    @Test
    @Order(3)
    public void on() {
    }

    @Test
    @Order(4)
    public void off() {
    }

    @Test
    @Order(5)
    public void startup() {
    }

    @Test
    @Order(6)
    public void landing() {
    }

    @Test
    @Order(7)
    public void shutdown() {
    }

    @AfterEach
    public void close() {
    }

}
