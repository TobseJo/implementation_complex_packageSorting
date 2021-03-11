package SortingStation.sortingSysten.SortingTracks;

import factory.BoyerMooreFactory;
import factory.RabinKarpFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Scanner {

    private BoyerMooreFactory boyerMoore = (BoyerMooreFactory) BoyerMooreFactory.build();
    private RabinKarpFactory rabinKarp = (RabinKarpFactory) RabinKarpFactory.build();

    private Object usedAlgorithm = rabinKarp;

    public boolean scanForExplosive(String txt) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int result = 0;
        String searchPattern = "exp!os:ve";

        if(usedAlgorithm instanceof RabinKarpFactory){
            Method searchMethod = rabinKarp.getClass().getDeclaredMethod("search", String.class, String.class, int.class);

            result = (int) searchMethod.invoke(searchPattern, txt, 7);
        }else if(usedAlgorithm instanceof BoyerMooreFactory){
            Method searchMethod = boyerMoore.getClass().getDeclaredMethod("search", String.class, String.class);

            result = (int) searchMethod.invoke(searchMethod, txt);
        }else {
            throw new RuntimeException("Invalid Algorithm");
        }

        if(result >= 0){
            return true;
        }else {
            return false;
        }
    }

    public void setUsedAlgorithm(Object usedAlgorithm) {
        this.usedAlgorithm = usedAlgorithm;
    }
}
