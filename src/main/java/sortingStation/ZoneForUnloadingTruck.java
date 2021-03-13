package sortingStation;

import items.Truck;

public class ZoneForUnloadingTruck {
    private int id;
    private ZS zs;
    private Truck truck;
    private Sensor sensor;

    public ZoneForUnloadingTruck(int id, ZS zs) {
        this.id = id;
        this.zs = zs;
        sensor = new Sensor(zs, id);
    }

    public void setTruckAndNotify(Truck truck) {
        this.truck = truck;
        sensor.informZsTruckArriving();
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Sensor getSensor() {
        return sensor;
    }
}
