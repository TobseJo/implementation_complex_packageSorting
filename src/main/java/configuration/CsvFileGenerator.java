package configuration;

import packageSorting.Pallet;
import packageSorting.Truck;
import packageSorting.Box;
import packageSorting.Package;

import java.util.*;

public class CsvFileGenerator {

    private List<Package> packages;
    private List<Box> boxes;
    private List<Pallet> pallets;
    private List<Truck> trucks;

    public CsvFileGenerator() {
        this.packages = new ArrayList<>();
        this.boxes = new ArrayList<>();
        this.pallets = new ArrayList<>();
        this.trucks = new ArrayList<>();
    }

    public void generateAllFiles(){
        generateBasePackageFile();
        generateBaseBoxFile();
        generateBasePalletFile();
        generateBaseTruckFile();
    }

    private void generateBasePackageFile() {
        LogEngine.instance.init(Configuration.instance.pathToDataDirectory + "base_package.csv");

        LinkedList<String> types = getTypes();

        HashSet<String> ids = getIds(Configuration.instance.numberOfCharsPerIdPackage, Configuration.instance.numberOfPackages, Configuration.instance.allLettersAndNumbersLowerCase);
        Iterator<String> iterator = ids.iterator();

        for (int i = 1; i <= Configuration.instance.numberOfPackages; i++) {
            StringBuilder stringBuilder = new StringBuilder();

            Package paket = new Package(iterator.next(), getContent(i), getZipCode(), types.poll(), getWeight());

            stringBuilder.append(paket.getId());
            stringBuilder.append(",");
            stringBuilder.append(paket.getContentAsString());
            stringBuilder.append(",");
            stringBuilder.append(paket.getZipCode());
            stringBuilder.append(",");
            stringBuilder.append(paket.getType());
            stringBuilder.append(",");
            stringBuilder.append(paket.getWeight());

            packages.add(paket);
            LogEngine.instance.writeLn(stringBuilder.toString());
        }
        LogEngine.instance.close();
    }

    private void generateBaseBoxFile() {
        LogEngine.instance.init(Configuration.instance.pathToDataDirectory + "base_box.csv");

        Collections.shuffle(packages);
        Iterator<Package> iteratorPackages = packages.iterator();

        HashSet<String> ids = getIds(Configuration.instance.numberOfCharsPerIdPackage, Configuration.instance.numberOfBoxes, Configuration.instance.allLettersAndNumbersLowerCase);
        Iterator<String> iteratorID = ids.iterator();

        for (int i = 0; i < Configuration.instance.numberOfBoxes; i++) {
            StringBuilder stringBuilder = new StringBuilder();

            Box box = new Box(iteratorID.next(), getPackagesPerBox(iteratorPackages));

            stringBuilder.append(box.getId());
            stringBuilder.append(",");
            stringBuilder.append(box.getPackagesAsString());

            boxes.add(box);
            LogEngine.instance.writeLn(stringBuilder.toString());
        }

        LogEngine.instance.close();
    }

    private void generateBasePalletFile(){
        LogEngine.instance.init(Configuration.instance.pathToDataDirectory + "base_pallet.csv");

        Iterator<Box> iteratorBox = boxes.iterator();

        for (int i = 1; i <= Configuration.instance.numberOfPallets; i++) {
            StringBuilder stringBuilder = new StringBuilder();

            Pallet pallet = new Pallet(i, getBoxesPerPallet(iteratorBox));

            stringBuilder.append(pallet.getBoxesAsString());

            pallets.add(pallet);
            LogEngine.instance.write(stringBuilder.toString());
        }

        LogEngine.instance.close();
    }

    private void generateBaseTruckFile() {
        LogEngine.instance.init(Configuration.instance.pathToDataDirectory + "base_truck.csv");

        Iterator<Pallet> iteratorPallets = pallets.iterator();

        HashSet<String> ids = getIds(Configuration.instance.numberOfCharsPerIdTruck, Configuration.instance.numberOfTrucks, Configuration.instance.allLettersAndNumbersUpperCase);
        Iterator<String> iteratorID = ids.iterator();

        for (int i = 0; i < Configuration.instance.numberOfTrucks; i++) {
            StringBuilder stringBuilder = new StringBuilder();

            Truck truck = new Truck(iteratorID.next(), getPalletPerTrailer(iteratorPallets));

            stringBuilder.append(truck.getTrailer().getPalletsPerTrailer(truck));

            trucks.add(truck);
            LogEngine.instance.write(stringBuilder.toString());
        }

        LogEngine.instance.close();
    }

    private HashSet<String> getIds(int numberOfChars, int amount, String lettersAndNumbers) {
        HashSet<String> ids = new HashSet<>();

        while (ids.size() < amount) {

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < numberOfChars; i++) {
                stringBuilder.append(lettersAndNumbers.charAt((int) (Math.random() * lettersAndNumbers.length())));
            }
            ids.add(stringBuilder.toString());
        }

        return ids;
    }

    private String getContent(int packageNumber) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < Configuration.instance.numberOfCharsPerContent; i++) {
            stringBuilder.append(Configuration.instance.allCharsForContent.charAt((int) (Math.random() * Configuration.instance.allCharsForContent.length())));
        }

        if (packageNumber % 6000 == 0) {
            String exp = Configuration.instance.explosiveString;
            int position = (int) ((Configuration.instance.numberOfCharsPerContent - exp.length()) * Math.random());

            stringBuilder.replace(position, position + exp.length(), exp);
            System.out.println("Added " + exp + " at package: " + packageNumber + " at position: " + position);
        }
        return stringBuilder.toString();
    }

    public String getZipCode() {

        String zipCode = String.valueOf((int) (Configuration.instance.rangeOfZipCodeMin + (Configuration.instance.rangeOfZipCodeMax - Configuration.instance.rangeOfZipCodeMin) * Math.random()));

        while (zipCode.length() != Configuration.instance.numberOfCharsPerZipCode) {
            zipCode = "0" + zipCode;
        }

        return zipCode;
    }

    private LinkedList<String> getTypes() {
        LinkedList<String> types = new LinkedList<>();

        for (Map.Entry<String, Double> entry : Configuration.instance.spreadOfType.entrySet()) {

            int length = (int) (Configuration.instance.numberOfPackages * entry.getValue());

            for (int j = 0; j < length; j++) {
                types.add(entry.getKey());
            }
        }

        return types;
    }

    private String getWeight() {
        Double weight = Configuration.instance.rangeOfWeightMin + (Configuration.instance.rangeOfWeightMax - Configuration.instance.rangeOfWeightMin) * Math.random();

        return String.valueOf(weight);
    }

    private LinkedList<Package> getPackagesPerBox(Iterator<Package> iterator) {
        LinkedList<Package> packagesPerBox = new LinkedList<>();

        for (int j = 0; j < Configuration.instance.numberOfPackagesPerBox; j++) {
            packagesPerBox.add(iterator.next());
        }

        return packagesPerBox;
    }

    private LinkedList<Box> getBoxesPerPallet(Iterator<Box> iterator){
        LinkedList<Box> boxesPerPallet = new LinkedList<>();

        for (int j = 0; j < Configuration.instance.numberOfBoxesPerPallet; j++) {
            boxesPerPallet.add(iterator.next());
        }

        return boxesPerPallet;
    }

    private LinkedList<Pallet> getPalletPerTrailer(Iterator<Pallet> iterator){
        LinkedList<Pallet> palletsPerTrailer = new LinkedList<>();

        for (int j = 0; j < Configuration.instance.numberOfPalletsPerTruck; j++) {
            palletsPerTrailer.add(iterator.next());
        }

        return palletsPerTrailer;
    }

}
