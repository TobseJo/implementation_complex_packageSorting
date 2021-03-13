package command;

import event.ShowStatistics;
import sortingStation.ZS;

public class ShowStatisticsCommand implements ICommand {
    @Override
    public void execute(ZS zs) {
        zs.post(new ShowStatistics());
    }
}
