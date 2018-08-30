package utils;

import java.io.*;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyReader {

    private final static File PROPERTIES_FILE = new File(System.getProperty("user.dir") + "/src/test/config/config.properties");

    private static Properties initProperty()  {
        Properties prop = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(PROPERTIES_FILE);

            prop.load(inputStream);

            inputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return prop;
    }

    public static String readProperty(String propertyName) {
        Properties prop = initProperty();

        String property = prop.getProperty(propertyName);

        return property;
    }

    public static String setProperty(String propertyName, String propertyValue) {
        RandomAccessFile writer;
        try {
            writer = new RandomAccessFile(PROPERTIES_FILE, "rw");
            String newProperty = new StringBuilder().append("\n").append(propertyName).append("=").append(propertyValue).toString();

            writer.seek(PROPERTIES_FILE.length());
            writer.write(newProperty.getBytes());

            writer.close();
            return readProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Property was not set");
    }

    public static void setOrRewriteProperty(String propertyName, String propertyValue) {
        Map<String, String> lines = null;
        try {
            lines = Files.
                    lines(PROPERTIES_FILE.toPath()).
                    filter(l -> !l.isEmpty() && l.trim().split("=").length == 2).
                    collect(Collectors.toMap((l -> l.trim().split("=")[0]), (l -> l.trim().split("=")[1])));

            lines.put(propertyName, propertyValue);

            FileOutputStream outputStream = new FileOutputStream(PROPERTIES_FILE);
            outputStream.write(0);

            lines.forEach((k, v) -> setProperty(k, v));

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removePropertyFromFile(String propertyName) {

        Map<String, String> lines = readPropertiesFromFile();

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(PROPERTIES_FILE);

            outputStream.write(0);

            lines.forEach((k, v) -> {

                if (k.equals(propertyName)) {
                } else setProperty(k, v);

            });

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> readPropertiesFromFile() {
        try {
            return Files.
                    lines(PROPERTIES_FILE.toPath()).
                    filter(l -> !l.isEmpty() && l.trim().split("=").length == 2).
                    collect(Collectors.toMap((l -> l.trim().split("=")[0]), (l -> l.trim().split("=")[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("Could not get properties from the file");
    }
}
