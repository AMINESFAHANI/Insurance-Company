package dev.adventure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    static private Properties prop = new Properties();
    static private String fileName = "src/test/resources/settings.config";
    static public String server = load("server");

    static public String load(String property) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty(property);
    };
}
