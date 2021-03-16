package sortingStation.sortingSysten.sortingTracks;

import base.Configuration;
import factory.BoyerMooreFactory;
import factory.RabinKarpFactory;

import java.lang.reflect.Method;

public class Scanner {
    private String usedAlgorithm = Configuration.instance.usedSearchAlgorithm;

    public boolean scanForExplosive(String txt) {
        try {
            int result = -2;
            String searchPattern = "exp!os:ve";
            Object searchAlgorithm;
            if (usedAlgorithm.equals("rk")) {
                searchAlgorithm = RabinKarpFactory.build();
                Method searchMethod = searchAlgorithm.getClass().getDeclaredMethod("search", String.class, String.class, int.class);
                result = (int) searchMethod.invoke(searchAlgorithm, searchPattern, txt, 101);
            } else if (usedAlgorithm.equals("bm")) {
                searchAlgorithm = BoyerMooreFactory.build();
                Method searchMethod = searchAlgorithm.getClass().getDeclaredMethod("search", String.class, String.class);
                result = (int) searchMethod.invoke(searchAlgorithm, txt, searchPattern);
            } else {
                throw new RuntimeException("Invalid Algorithm");
            }
            if (result != -1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object getUsedAlgorithm() {
        return usedAlgorithm;
    }

    public void setUsedAlgorithm(String usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }
}
