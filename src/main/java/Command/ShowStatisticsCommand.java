package Command;

import Reporter.Report;
import packageSorting.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    }
}
