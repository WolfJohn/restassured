package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Properties PROP = new Properties();

    public static String readProperty(String propertyName) throws IOException {
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/config/config.properties");

        PROP.load(inputStream);

        String property = PROP.getProperty(propertyName);

        inputStream.close();

        return property;
    }
}
