package packageSorting;

import configuration.Configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Pallet {

    private int id;
    private Box[][] boxes;

    public Pallet(int id, LinkedList<Box> boxList){
        this.id = id;
        this.boxes = getBoxes(boxList);
    }

    private Box[][] getBoxes(LinkedList<Box> boxList){
        Box[][] boxes = new Box[4][3];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                boxes[i][j] = boxList.poll();
            }
        }

        return boxes;
    }

    public String getBoxesAsString(){
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                stringBuilder.append(getId() + "," + i + "," + j + "," + getBoxes()[i][j].getId());
                stringBuilder.append(Configuration.instance.lineSeparator);
            }
        }

        return stringBuilder.toString();
    }


    public Box[][] getBoxes() {
        return boxes;
    }

    public int getId() {
        return id;
    }
}
