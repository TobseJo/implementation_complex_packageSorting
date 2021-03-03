package Command;

import Reporter.Report;
import configuration.Configuration;
import configuration.LogEngine;
import packageSorting.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShowStatisticsCommand implements ICommand {

    @Override
    public void execute() {
        //TODO where to get data
        HashMap<Type, Integer> amountOfScannedPaketsPerPakets;
        amountOfScannedPaketsPerPakets = new HashMap<>();

        int amountOfTrucks = 0;

        ArrayList<Package> dangerousPackages = new ArrayList<>();
//        dangerousPackages.addAll();
        Report report = new Report.Builder().amountOfScannedPakets(amountOfScannedPaketsPerPakets).amountOfTruck(amountOfTrucks).dangerousPackages(dangerousPackages).date(new Date()).build();
        writeReportToData(report);
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
