package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyReader {

    private static Properties initProperty() throws IOException {
        Properties prop = new Properties();
        FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/config/config.properties");

        prop.load(inputStream);

        inputStream.close();

        return prop;
    }

    public static String readProperty(String propertyName) throws IOException {
        Properties prop = initProperty();

        String property = prop.getProperty(propertyName);

        return property;
    }

//    public static void setProperty(String propertyName, String propertyValue) throws IOException {
//        Properties prop = initProperty();
//
//        OutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + "/src/test/java/config/config.properties");
//
//
//        prop.store(outputStream, "mine=enim");//setProperty(propertyName, propertyValue);
//    }
//
//    public static void main(String[] args) throws IOException {
//        setProperty("mine", "enim");
//        System.out.println(readProperty("mine"));
//    }
}
