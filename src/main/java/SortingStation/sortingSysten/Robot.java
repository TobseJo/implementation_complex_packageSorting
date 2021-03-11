package SortingStation.sortingSysten;

import SortingStation.ZS;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import event.UnloadBoxOfPallet;
import packageSorting.Box;
import packageSorting.Package;
import packageSorting.Pallet;

import java.util.ArrayList;

public class Robot {
    private EventBus eventBus;
    private ZS zs;
    private SortingSystem sortingSystem;

    public Robot(ZS zs, SortingSystem sortingSystem) {
        this.sortingSystem = sortingSystem;
        this.zs = zs;
        this.eventBus = new EventBus();
   }

    public void post(Object object) {
        eventBus.post(object);
    }

    @Subscribe
    public void receive(UnloadBoxOfPallet event) {
        int trackCtr = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                Pallet currentPallet = sortingSystem.getInterimStorage().getPallets()[i][j];
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 3; l++) {
                        Box currentBox = currentPallet.getBoxes()[k][l];
                        for (int m = 0; m < 5; m++) {
                            for (int n = 0; n < 2; n++) {
                                for (int o = 0; o < 4; o++) {
                                    Package currentPackage = currentBox.getPackages()[m][n][o];
                                    sortingSystem.getWarehouseTracks()[trackCtr].packageTrack.add(currentPackage);

                                    currentBox.getPackages()[m][n][o] = null;
                                    if(trackCtr < 7){
                                        trackCtr++;
                                    }else {
                                        trackCtr = 0;
                                    }
                                }
                            }
                        }
                        currentPallet.getBoxes()[k][l] = null;
                    }
                }
                sortingSystem.getInterimStorage().getPallets()[i][j] = null;
            }
        }
    }
}
