package packageSorting;

import configuration.Configuration;

import java.util.LinkedList;

public class Trailer {

    private Pallet[][] pallets;

    public Trailer(LinkedList<Pallet> palletList){
        this.pallets = getPallets(palletList);
    }


    private Pallet[][] getPallets(LinkedList<Pallet> palletList){
        Pallet[][] pallets = new Pallet[2][5];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                pallets[i][j] = palletList.poll();
            }
        }

        return pallets;
    }


    public String getPalletsPerTrailer(Truck truck){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            String position = "right";

            if(i == 0){
                position = "left";
            }

            for (int j = 0; j < 5; j++) {
                stringBuilder.append(truck.getId() + "," + position + "," + j + "," + getPallets()[i][j].getId());
                stringBuilder.append(Configuration.instance.lineSeparator);
            }
        }

        return stringBuilder.toString();
    }

    public Pallet[][] getPallets() {
        return pallets;
    }
}
