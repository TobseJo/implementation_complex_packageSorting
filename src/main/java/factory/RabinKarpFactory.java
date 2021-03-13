package factory;

import configuration.Configuration;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class RabinKarpFactory {

    public static Object build() {
        Object RabinKarpPort = null;
        try {
            URL[] urls = {new File(Configuration.instance.pathToRabinKarpJavaArchive).toURI().toURL()};
            URLClassLoader urlClassLoader = new URLClassLoader(urls, BoyerMooreFactory.class.getClassLoader());
            Class RabinKarpClass = Class.forName("RabinKarp", true, urlClassLoader);
            Object RabinKarpInstance = RabinKarpClass.getMethod("getInstance").invoke(null);
            RabinKarpPort = RabinKarpClass.getDeclaredField("port").get(RabinKarpInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RabinKarpPort;
    }
}
