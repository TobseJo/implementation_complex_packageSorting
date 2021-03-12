package sortingStation;

import java.util.ArrayList;

public class TruckDetector {

    private ArrayList<ITruckArriving> listeners;

    public TruckDetector() {
        listeners = new ArrayList<>();
    }

//    public void notifyViaEventbus() {
//        for (var listener : listeners) {
//            listener.informZsTruckArriving();
//        }
//    }

    public void addListener(ITruckArriving listener) {
        listeners.add(listener);
    }


    public void removeListener(ITruckArriving listener) {
        listeners.remove(listener);
    }
}
