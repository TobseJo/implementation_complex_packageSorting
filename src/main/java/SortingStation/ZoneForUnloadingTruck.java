package SortingStation;

import event.TruckArrivedSendVehicle;
import packageSorting.Truck;

public class ZoneForUnloadingTruck implements ITruckArriving{
    private int id;
    private ZS zs;
    private Truck truck;

    public ZoneForUnloadingTruck(int id, ZS zs){
        this.id = id;
        this.zs = zs;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public void informZsTruckArriving() {
        zs.post(new TruckArrivedSendVehicle(id));
    }

    public Truck getTruck() {
        return truck;
    }
}
