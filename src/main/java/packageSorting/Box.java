package packageSorting;

import java.util.ArrayList;
import java.util.LinkedList;

public class Box {

    private String id;
    private Package[][][] packages;

    public Box(String id, LinkedList<Package> packageList) {
        this.id = id;
        this.packages = getContentArray(packageList);
    }

    private Package[][][] getContentArray(LinkedList<Package> packageList) {
        Package[][][] packages = new Package[5][2][4];

        for (int k = 0; k < 5; k++) {
            for (int j = 0; j < 2; j++) {
                for (int i = 0; i < 4; i++) {
                    packages[k][j][i] = packageList.poll();
                }
            }
        }

        return packages;
    }

    public String getPackagesAsString(){
        StringBuilder stringBuilder = new StringBuilder();

        for (Package[][] packages : getPackages()) {
            for (Package[] packages1 : packages) {
                for (Package packages2 : packages1) {
                    stringBuilder.append(packages2.getId());
                    stringBuilder.append(",");
                }
            }
        }

        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    public Package[][][] getPackages() {
        return packages;
    }

    public String getId() {
        return id;
    }
}
