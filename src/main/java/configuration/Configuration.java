package configuration;

import factory.RabinKarpFactory;

import java.util.Map;

public enum Configuration {
    instance;

    public final String userDirectory = System.getProperty("user.dir");
    public final String lineSeparator = System.getProperty("line.separator");
    public final String fileSeparator = System.getProperty("file.separator");

    public String commonPathToJavaArchive = userDirectory + fileSeparator + "components" + fileSeparator;
    public String pathToDataDirectory = userDirectory + fileSeparator + "data" + fileSeparator;

    public final int numberOfPackages = 24000;
    public final int numberOfBoxes = 600;
    public final int numberOfPallets = 50;
    public final int numberOfTrucks = 5;

    public final int numberOfCharsPerContent = 2500;
    public final int numberOfCharsPerZipCode= 6;

    public final int numberOfCharsPerIdPackage = 6;
    public final int numberOfCharsPerIdBox = 5;
    public final int numberOfCharsPerIdTruck = 4;

    public final int numberOfPackagesPerBox = 40;
    public final int numberOfBoxesPerPallet = 12;
    public final int numberOfPalletsPerTruck = 10;

    public final Double rangeOfWeightMin = 1.00;
    public final Double rangeOfWeightMax = 5.00;

    public final int rangeOfZipCodeMin = 1067;
    public final int rangeOfZipCodeMax = 99998;

    public final Map<String, Double> spreadOfType = Map.of("NORMAL", 0.8, "EXPRESS", 0.15, "VALUE", 0.05);

    public String pathToBoyerMooreJavaArchive = commonPathToJavaArchive + "BoyerMoore" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "boyerMoore.jar";
    public String pathToRabinKarpJavaArchive = commonPathToJavaArchive + "RabinKarp" + fileSeparator + "build" + fileSeparator + "libs" + fileSeparator + "rabinKarp.jar";

    public final String allCharsForContent = "abcdefghijklmnopqrstuvwxyz:-!";
    public final String allLettersAndNumbersLowerCase = "abcdefghijklmnopqrstuvwxyz0123456789";
    public final String allLettersAndNumbersUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public final String key = "dhbw";
    public final String explosiveString = "exp!os:ve";
    public final IStrategy usedAlgorithm = new DES();
    public String usedSearchAlgorithm = "br";
    public final int numberOfLengthForMagnetStripe = 10;
}
