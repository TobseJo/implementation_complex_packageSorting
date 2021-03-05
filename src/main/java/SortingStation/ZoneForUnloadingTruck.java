package SortingStation;

public class ZoneForUnloadingTruck implements ITruckArriving{
    private int id;
    private ZS zs;


    public ZoneForUnloadingTruck(int id, ZS zs){
        this.id = id;
        this.zs = zs;
    }


    @Override
    public void informZsTruckArriving() {
        zs.sendEventToRandomVehicle(id);
    }
}
