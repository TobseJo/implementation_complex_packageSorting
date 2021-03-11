package SortingStation;

import Reporter.Report;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import configuration.Configuration;
import configuration.LogEngine;
import configuration.ObjectGenerator;
import event.Shutdown;
import event.*;
import packageSorting.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ZS {

    private ObjectGenerator objectGenerator = new ObjectGenerator();
    private EventBus eventBus;
    private WaitingZone waitingZone;

    public ZS(EventBus eventBus) {
        this.eventBus = eventBus;
        this.waitingZone = new WaitingZone();
    }

    public void post(Object object) {
        eventBus.post(object);
    }

    @Subscribe
    public void receive(Init event) {
        waitingZone.setTrucks(objectGenerator.generateTrucks());
    }

    @Subscribe
    public void receive(Next event) {

    }

    @Subscribe
    public void receive(Shutdown event) {

    }

    @Subscribe
    public void receive(Lock event) {

    }

    @Subscribe
    public void receive(Unlock event) {

    }

    @Subscribe
    public void receive(ShowStatistics event) {
        //TODO where to get data | not tested
        HashMap<Type, Integer> amountOfScannedPaketsPerPakets;
        amountOfScannedPaketsPerPakets = new HashMap<>();

        int amountOfTrucks = 0;

        ArrayList<Package> dangerousPackages = new ArrayList<>();
        Report report = new Report.Builder().amountOfScannedPakets(amountOfScannedPaketsPerPakets).amountOfTruck(amountOfTrucks).dangerousPackages(dangerousPackages).date(new Date()).build();
        writeReportToData(report);
    }

    @Subscribe
    public void receive(ChangeSearchAlgorithm event) {

    }

    @Subscribe
    public void receive(TruckArrivedSendVehicle event) {
        AutonomousVehicle autonomousVehicle = null;
        int randomNumber = 0;
        while (autonomousVehicle == null) {
            randomNumber = (int) Math.random() * 5;
            autonomousVehicle = objectGenerator.getParkingPlaceForAutonomousVehicle().getAutonomousVehicles()[randomNumber];
        }
        objectGenerator.getParkingPlaceForAutonomousVehicle().getAutonomousVehicles()[randomNumber] = null;

        ZoneForUnloadingTruck zoneForUnloadingTruck = objectGenerator.getZonesForUnloadingTrucks()[event.getId()];
        autonomousVehicle.post(new UnloadTruckAndLoadInterimStorage(zoneForUnloadingTruck));
    }

    //TODO what to do after Unloading?
    @Subscribe
    public void receive(FinishedTruckUnload event) {

    }

    private void writeReportToData(Report report) {
        LogEngine.instance.init(Configuration.instance.pathToDataDirectory + "report.txt");

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(report.getDate()).append(";").append(report.getAmountOfTruck()).append(";");
        for(Map.Entry<Type, Integer> entry : report.getAmountOfScannedPakets().entrySet()) {
            Type type = entry.getKey();
            Integer value = entry.getValue();
            stringBuilder.append(type.toString() + " : " + value);
        }
        stringBuilder.append(";");
        for (Package packageEntity : report.getDangerousPackages()) {
            stringBuilder.append(packageEntity.getName() + ",");
        }
        LogEngine.instance.write(stringBuilder.toString());


        LogEngine.instance.close();
    }

}
