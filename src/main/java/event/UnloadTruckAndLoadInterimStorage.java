package event;

import SortingStation.ZoneForUnloadingTruck;

public class UnloadTruckAndLoadInterimStorage {
    private ZoneForUnloadingTruck zoneForUnloadingTruck;

    public UnloadTruckAndLoadInterimStorage(ZoneForUnloadingTruck zoneForUnloadingTruck){
        this.zoneForUnloadingTruck = zoneForUnloadingTruck;
    }

    public String toString(){
        return "Event: UnloadTruck";
    }
}
