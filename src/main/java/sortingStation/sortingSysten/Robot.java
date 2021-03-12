package sortingStation.sortingSysten;

import sortingStation.ZS;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import event.UnloadBoxOfPallets;
import packageSorting.Box;
import packageSorting.Pallet;
import packageSorting.Package;

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
    public void receive(UnloadBoxOfPallets event) {
        System.out.println(event);
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
                                    putCurrentPackageToWarehouseTrack(currentBox.getPackages()[m][n][o], trackCtr);
                                    currentBox.getPackages()[m][n][o] = null;
                                }
                            }
                        }
                        sortingSystem.getStorageForEmptyBoxes().getBoxes().add(currentPallet.getBoxes()[k][l]);
                        currentPallet.getBoxes()[k][l] = null;
                    }
                }
                sortingSystem.getStorageForEmptyPallets().getPallets().add(currentPallet);
                sortingSystem.getInterimStorage().getPallets()[i][j] = null;
            }
        }
    }

    private void putCurrentPackageToWarehouseTrack(Package currentPackage, int trackCtr) {
        int breakCtr = 0;
        while(sortingSystem.getWarehouseTracks()[trackCtr].addToPackageTrack(currentPackage)){
            if(breakCtr < 8){
                setTrackCtr(trackCtr);
            }else{
                return;
            }
        }
        setTrackCtr(trackCtr);
    }

    private int setTrackCtr(int trackCtr){
        if(trackCtr < 8){
            trackCtr++;
        }else {
            trackCtr = 0;
        }
        return trackCtr;
    }
}
