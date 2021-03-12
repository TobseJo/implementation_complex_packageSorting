package sortingStation.sortingSysten;

import sortingStation.ZS;
import event.TrackIsFull;

public class SensorForFilling implements ITrackObserver{
    private ZS zs;
    private int id;

    public SensorForFilling(ZS zs, int id){
        this.zs = zs;
        this.id = id;
    }

    private void sendEvent(){
        zs.post(new TrackIsFull(id));
    }

    @Override
    public void trackIsFull() {
        sendEvent();
    }
}
