package org.xflash.edd;

import java.io.File;
import java.net.URL;

public class FileUtils {
    public static File classpath(String name) {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        URL resource = classLoader.getResource(name);
        return new File(resource.getFile());
    }
}
