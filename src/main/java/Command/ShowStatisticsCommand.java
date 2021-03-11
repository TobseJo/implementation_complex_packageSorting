package Command;

import Reporter.Report;
import SortingStation.ZS;
import configuration.Configuration;
import configuration.LogEngine;
import event.ShowStatistics;
import packageSorting.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShowStatisticsCommand implements ICommand {

    @Override
    public void execute(ZS zs) {
        zs.post(new ShowStatistics());

    }


}
