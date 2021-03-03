package configuration;

import packageSorting.Box;
import packageSorting.Package;
import packageSorting.Pallet;
import packageSorting.Truck;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class FileReader {

    public LinkedList<Package> readPackages(String FILEPATH) {
        LinkedList<Package> packages = new LinkedList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new java.io.FileReader(new File(Configuration.instance.pathToDataDirectory + FILEPATH)));
            String line = null;

            while ((line = br.readLine()) != null) {

                String[] content = line.split(",");

                Package paket = new Package(content[0], content[1], content[2], content[3], content[4]);

                packages.add(paket);

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
                    for (HashMap.Entry<String, Package> entry : packageHashMap.entrySet()) {

                        String packageID = entry.getKey();

                        if (packageID.equals(content[i + 1])) {
                            packagesPerBox.add(entry.getValue());
                            packageHashMap.remove(packageID);
                            continue;
                        }
                    }
                }

                Box box = new Box(boxId, packagesPerBox);

                boxes.add(box);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return boxes;
    }


    public LinkedList<Pallet> readPalettes(String FILEPATH, HashMap<String, Box> boxHashMap) {
        LinkedList<Pallet> pallets = new LinkedList<>();
        LinkedList<Box> boxesPerPallet;
        BufferedReader br = null;

        try {
            br = new BufferedReader(new java.io.FileReader(new File(Configuration.instance.pathToDataDirectory + FILEPATH)));
            String line = null;

            for (int i = 0; i < Configuration.instance.numberOfPallets; i++) {
                int palletID = 0;
                boxesPerPallet = new LinkedList<>();
                
                for (int j = 0; j < Configuration.instance.numberOfBoxesPerPallet; j++) {
                    line = br.readLine();
                    String[] content = line.split(",");

                    palletID = Integer.valueOf(content[1]);
                    for (HashMap.Entry<String, Box> entry : boxHashMap.entrySet()) {

                        String boxID = entry.getKey();

                        if (boxID.equals(content[i + 1])) {
                            boxesPerPallet.add(entry.getValue());
                            boxHashMap.remove(boxID);
                            continue;
                        }
                    }
                }
                Pallet pallet = new Pallet(palletID, boxesPerPallet);
                pallets.add(pallet);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return pallets;
    }


    public LinkedList<Truck> readTruck(String FILEPATH, HashMap<String, Pallet> palletHashMap) {
        LinkedList<Truck> trucks = new LinkedList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new java.io.FileReader(new File(Configuration.instance.pathToDataDirectory + FILEPATH)));
            String line = null;

            for (int i = 0; i < Configuration.instance.numberOfTrucks; i++) {
                int truckID = 0;

                for (int j = 0; j < Configuration.instance.numberOfPalletsPerTruck; j++) {
                    line = br.readLine();
                    String content[] = line.split(",");

                    truckID =

                }

                Truck truck = new Truck();
                trucks.add(truck);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trucks;
    }


}
