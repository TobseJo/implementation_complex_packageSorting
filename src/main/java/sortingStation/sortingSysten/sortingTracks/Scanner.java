package sortingStation.sortingSysten.sortingTracks;

import configuration.Configuration;
import factory.BoyerMooreFactory;
import factory.RabinKarpFactory;

import java.lang.reflect.Method;

public class Scanner {

    private Object usedAlgorithm;

    public boolean scanForExplosive(String txt){
        try {
            int result = 0;
            String searchPattern = "exp!os:ve";

            if (usedAlgorithm instanceof RabinKarpFactory) {
                Method searchMethod = usedAlgorithm.getClass().getDeclaredMethod("search", String.class, String.class, int.class);

                result = (int) searchMethod.invoke(searchPattern, txt, 7);
            } else if (usedAlgorithm instanceof BoyerMooreFactory) {
                Method searchMethod = usedAlgorithm.getClass().getDeclaredMethod("search", String.class, String.class);

                result = (int) searchMethod.invoke(searchMethod, txt);
            } else {
                throw new RuntimeException("Invalid Algorithm");
            }

            if (result >= 0) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void setUsedAlgorithm(Object usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }
}
