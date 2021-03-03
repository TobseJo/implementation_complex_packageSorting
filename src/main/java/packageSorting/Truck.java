package packageSorting;

import java.util.LinkedList;

public class Truck {

    private String id;
    private Trailer trailer;

    public Truck(String id, LinkedList<Pallet> pallets){
        this.id = id;
        this.trailer = new Trailer(pallets);
    }

    public Truck (String id, Pallet[][] pallets){
        this.id = id;
        this.trailer = new Trailer(pallets);
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public String getId() {
        return id;
    }
}
