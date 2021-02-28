package Reporter;

import packageSorting.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Report {
    private final Date date;
    private final int amountOfTruck;
    private final HashMap<Type, Integer> amountOfScannedPakets;
    private final ArrayList<Package> dangerousPackages;

    private Report(Builder builder){
        date = builder.date;
        amountOfTruck = builder.amountOfTruck;
        amountOfScannedPakets = builder.amountOfScannedPakets;
        dangerousPackages = builder.dangerousPackages;
    }

    public static class Builder{
        private Date date;
        private int amountOfTruck;
        private HashMap<Type, Integer> amountOfScannedPakets;
        private ArrayList<Package> dangerousPackages;

        public Builder date(Date date){
            this.date = date;
            return this;
        }

        public Builder amountOfTruck(int amountOfTruck){
            this.amountOfTruck = amountOfTruck;
            return this;
        }

        public Builder amountOfScannedPakets(HashMap<Type, Integer> amountOfScannedPakets){
            this.amountOfScannedPakets = amountOfScannedPakets;
            return this;
        }

        public Builder dangerousPackages(ArrayList<Package> dangerousPackages){
            this.dangerousPackages = dangerousPackages;
            return this;
        }

        public Report build(){
            return new Report(this);
        }
    }

}
