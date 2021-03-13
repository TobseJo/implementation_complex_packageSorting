package factory;

import configuration.Configuration;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class BoyerMooreFactory {

    public static Object build() {
        Object BoyerMoorePort = null;
        try {
            URL[] urls = {new File(Configuration.instance.pathToBoyerMooreJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, BoyerMooreFactory.class.getClassLoader());
            Class BoyerMooreClass = Class.forName("BoyerMoore", true, urlClassLoader);
            Object BoyerMooreInstance = BoyerMooreClass.getMethod("getInstance").invoke(null);
            BoyerMoorePort = BoyerMooreClass.getDeclaredField("port").get(BoyerMooreInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BoyerMoorePort;
    }
}

