package SortingStation;

import event.TruckArrivedSendVehicle;

public class Sensor implements ITruckArriving {
    private ZS zs;
    private int id;

    public Sensor(ZS zs, int id){
        this.zs = zs;
        this.id = id;
    }

    @Override
    public void informZsTruckArriving() {
        System.out.println("Truck is arriving!");
        zs.post(new TruckArrivedSendVehicle(id));
    }
}
