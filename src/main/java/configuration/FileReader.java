package configuration;

import packageSorting.Box;
import packageSorting.Pallet;
import packageSorting.Truck;
import packageSorting.Package;

import java.io.BufferedReader;
import java.io.File;
import java.util.*;


public class FileReader {

    public LinkedList<Package> readPackages(String FILEPATH) {
        LinkedList<Package> packages = new LinkedList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new java.io.FileReader(new File(Configuration.instance.pathToDataDirectory + FILEPATH)));
            String line = null;

            while ((line = br.readLine()) != null) {

                String[] content = line.split(",");

                packages.add(new Package(content[0], content[1], content[2], content[3], content[4]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packages;
    }


    public LinkedList<Box> readBoxes(String FILEPATH, HashMap<String, Package> packageHashMap) {
        LinkedList<Box> boxes = new LinkedList<>();
        LinkedList<Package> packagesPerBox;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new java.io.FileReader(new File(Configuration.instance.pathToDataDirectory + FILEPATH)));
            String line = null;

            while ((line = br.readLine()) != null) {

                packagesPerBox = new LinkedList<>();
                String[] content = line.split(",");

                String boxId = content[0];

                for (int i = 1; i <= Configuration.instance.numberOfPackagesPerBox; i++) {
                    packagesPerBox.add(packageHashMap.get(content[i]));
                }

                boxes.add(new Box(boxId, packagesPerBox));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return boxes;
    }


    public LinkedList<Pallet> readPalettes(String FILEPATH, HashMap<String, Box> boxHashMap) {
        LinkedList<Pallet> pallets = new LinkedList<>();
        Box[][] boxesPerPallet;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new java.io.FileReader(new File(Configuration.instance.pathToDataDirectory + FILEPATH)));
            String line = null;

            for (int i = 0; i < Configuration.instance.numberOfPallets; i++) {
                int palletID = 0;
                boxesPerPallet = new Box[4][3];
                
                for (int j = 0; j < Configuration.instance.numberOfBoxesPerPallet; j++) {
                    line = br.readLine();
                    String[] content = line.split(",");

                    palletID = Integer.valueOf(content[0]);
                    int position = Integer.valueOf(content[1]);
                    int level = Integer.valueOf(content[2]);

                    boxesPerPallet[position][level] = boxHashMap.get(content[3]);

                }
                pallets.add(new Pallet(palletID, boxesPerPallet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pallets;
    }


    public Stack<Truck> readTruck(String FILEPATH, HashMap<Integer, Pallet> palletHashMap) {
        Stack<Truck> trucks = new Stack<Truck>();
        Pallet[][] pallets;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new java.io.FileReader(new File(Configuration.instance.pathToDataDirectory + FILEPATH)));
            String line = null;

            for (int i = 0; i < Configuration.instance.numberOfTrucks; i++) {
                String truckID = "";
                pallets = new Pallet[2][5];

                for (int j = 0; j < Configuration.instance.numberOfPalletsPerTruck; j++) {
                    line = br.readLine();
                    String content[] = line.split(",");

                    truckID = content[0];
                    int side = 0;

                    switch (content[1]){
                        case "left":
                            side = 0;
                            break;
                        case "right":
                            side = 1;
                    }

                    int position = Integer.valueOf(content[2]);

                    pallets[side][position] = palletHashMap.get(content[3]);

                }
                trucks.add(new Truck(truckID, pallets));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trucks;
    }


}
