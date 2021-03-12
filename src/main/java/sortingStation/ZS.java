package sortingStation;

import sortingStation.sortingSysten.SortingTracks.ExpressSortingTrack;
import sortingStation.sortingSysten.state.Locked;
import reporter.Report;
import sortingStation.sortingSysten.SortingTracks.SortingTrack;
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

    private ObjectGenerator objectGenerator;
    private EventBus eventBus;
    private WaitingZone waitingZone;
    private int amountOfFullTracks;

    private SortingStation sortingStation;

    public ZS(EventBus eventBus, ObjectGenerator objectGenerator) {
        this.objectGenerator = objectGenerator;
        this.eventBus = eventBus;
        this.waitingZone = new WaitingZone();
        amountOfFullTracks = 0;
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
        int randomNumber = (int) Math.random() * sortingStation.getZonesForUnloadingTrucks().length;

        sortingStation.getZonesForUnloadingTrucks()[randomNumber].setTruck(waitingZone.getTrucks().pop());
    }

    @Subscribe
    public void receive(Shutdown event) {
        for(ZoneForUnloadingTruck zoneForUnloadingTruck : sortingStation.getZonesForUnloadingTrucks()){
            zoneForUnloadingTruck.getSensor().setState(new Locked());
        }
        for (SortingTrack sortingTrack : sortingStation.getSortingSystem().getSortingTracks()){
            sortingTrack.getScanner().setUsedAlgorithm(null);
        }
    }

    @Subscribe
    public void receive(Lock event) {
        sortingStation.getSortingSystem().switchState();
    }

    @Subscribe
    public void receive(Unlock event) {
        sortingStation.getSortingSystem().switchState();
    }

    @Subscribe
    public void receive(ShowStatistics event) {
        //TODO where to get data | not tested
        HashMap<Type, Integer> amountOfScannedPakets;
        amountOfScannedPakets = new HashMap<>();

        int amountOfTrucks = 0;

        ArrayList<Package> dangerousPackages = new ArrayList<>();
        Report report = new Report.Builder().amountOfScannedPakets(amountOfScannedPakets).amountOfTruck(amountOfTrucks).dangerousPackages(dangerousPackages).date(new Date()).build();
        writeReportToData(report);
    }

    @Subscribe
    public void receive(ChangeSearchAlgorithm event) {
        for (SortingTrack sortingTrack : sortingStation.getSortingSystem().getSortingTracks()){
            sortingTrack.getScanner().setUsedAlgorithm(event.getAlgorithm());
        }
    }

    @Subscribe
    public void receive(TruckArrivedSendVehicle event) {
        AutonomousVehicle autonomousVehicle = null;
        int randomNumber = 0;
        while (autonomousVehicle == null) {
            randomNumber = (int) Math.random() * 5;
            autonomousVehicle = sortingStation.getParkingPlaceForAutonomousVehicle().getAutonomousVehicles()[randomNumber];
        }
        sortingStation.getParkingPlaceForAutonomousVehicle().getAutonomousVehicles()[randomNumber] = null;
        ZoneForUnloadingTruck zone = null;
        for (var currentZone : sortingStation.getZonesForUnloadingTrucks()) {
            if(currentZone.getTruck() != null){
                zone = currentZone;
            }
        }
        autonomousVehicle.post(new UnloadTruckAndLoadInterimStorage(zone));
    }

    //TODO what to do after Unloading?
    @Subscribe
    public void receive(FinishedTruckUnload event) {

    }

    @Subscribe
    public void receive(TrackIsFull event){
        if(amountOfFullTracks == 7){
            ((ExpressSortingTrack)sortingStation.getSortingSystem().getSortingTracks()[2]).post(new SortEveryThing(sortingStation.getSortingSystem()));
        }else{
            amountOfFullTracks++;
        }
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

    public void setSortingStation(SortingStation sortingStation) {
        this.sortingStation = sortingStation;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
