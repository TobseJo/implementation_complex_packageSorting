package configuration;

import items.Package;
import items.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Report {
    private final Date date;
    private final int amountOfTruck;
    private final HashMap<Type, Integer> amountOfScannedPackets;
    private final ArrayList<Package> dangerousPackages;

    private Report(Builder builder) {
        date = builder.date;
        amountOfTruck = builder.amountOfTruck;
        amountOfScannedPackets = builder.amountOfScannedPackets;
        dangerousPackages = builder.dangerousPackages;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Package> getDangerousPackages() {
        return dangerousPackages;
    }

    public HashMap<Type, Integer> getAmountOfScannedPackets() {
        return amountOfScannedPackets;
    }

    public int getAmountOfTruck() {
        return amountOfTruck;
    }

    public static class Builder {
        private Date date;
        private int amountOfTruck;
        private HashMap<Type, Integer> amountOfScannedPackets;
        private ArrayList<Package> dangerousPackages;

        public Builder date(Date date) {
            this.date = date;
            return this;
        }

        public Builder amountOfTruck(int amountOfTruck) {
            this.amountOfTruck = amountOfTruck;
            return this;
        }

        public Builder amountOfScannedPackets(HashMap<Type, Integer> amountOfScannedPackets) {
            this.amountOfScannedPackets = amountOfScannedPackets;
            return this;
        }

        public Builder dangerousPackages(ArrayList<Package> dangerousPackages) {
            this.dangerousPackages = dangerousPackages;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }

}
