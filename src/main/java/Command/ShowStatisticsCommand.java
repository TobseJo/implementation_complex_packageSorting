package Command;

import SortingStation.ZS;
import event.ShowStatistics;

public class ShowStatisticsCommand implements ICommand {

    @Override
    public void execute(ZS zs) {
        zs.post(new ShowStatistics());
    }
}
