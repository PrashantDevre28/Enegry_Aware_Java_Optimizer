package Optimizer.analyzer;

import java.io.File;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarAnalyzer {

    public void analyzeJar(File jarFile) {

        try {

            JarFile jar =
                    new JarFile(jarFile);

            jar.stream().forEach(entry -> {

                if(entry.getName().endsWith(".class")) {

                    System.out.println(
                            "Analyzing: "
                            + entry.getName()
                    );
                }
            });

            jar.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}