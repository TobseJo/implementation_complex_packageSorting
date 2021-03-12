package sortingStation.sortingSysten.sortingTracks;

import configuration.Configuration;
import factory.BoyerMooreFactory;
import factory.RabinKarpFactory;

import java.lang.reflect.Method;

public class Scanner {

    private String usedAlgorithm = Configuration.instance.usedSearchAlgorithm;

    public boolean scanForExplosive(String txt){
        try {
            int result = 0;
            String searchPattern = "exp!os:ve";
            Object searchAlgorithm;

            if (usedAlgorithm == "rk") {
                searchAlgorithm = RabinKarpFactory.build();
                Method searchMethod = searchAlgorithm.getClass().getDeclaredMethod("search", String.class, String.class, int.class);

                result = (int) searchMethod.invoke(usedAlgorithm, searchPattern, txt, 101);
            } else if (usedAlgorithm == "br") {
                searchAlgorithm = BoyerMooreFactory.build();
                Method searchMethod = searchAlgorithm.getClass().getDeclaredMethod("search", String.class, String.class);

                result = (int) searchMethod.invoke(searchAlgorithm, searchPattern, txt);
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

    public void setUsedAlgorithm(String usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }

    public Object getUsedAlgorithm() {
        return usedAlgorithm;
    }
}
