package event;

public class TruckArrivedSendVehicle {
    private int id;

    public TruckArrivedSendVehicle(int id){
        this.id = id;
    }

    public String toString(){
        return "Event: TruckArrivedSendVehicle";
    }
}
