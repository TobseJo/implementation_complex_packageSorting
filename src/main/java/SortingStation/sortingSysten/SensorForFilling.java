package SortingStation.sortingSysten;

public class SensorForFilling implements ITrackObserver{
    private void sendEvent(){

    }

    @Override
    public void trackIsFull() {
        sendEvent();
    }
}
