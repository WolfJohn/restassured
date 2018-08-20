package utils;

import org.junit.Test;
import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyReader {

    private final static File PROPERTIES_FILE = new File(System.getProperty("user.dir") + "/src/test/config/config.properties");

    private static Properties initProperty() throws IOException {
        Properties prop = new Properties();
        FileInputStream inputStream = new FileInputStream(PROPERTIES_FILE);

        prop.load(inputStream);

        inputStream.close();

        return prop;
    }

    public static String readProperty(String propertyName) throws IOException {
        Properties prop = initProperty();

        String property = prop.getProperty(propertyName);

        return property;
    }

    public static String setProperty(String propertyName, String propertyValue) throws IOException {
        RandomAccessFile writer = new RandomAccessFile(PROPERTIES_FILE, "rw");
        String newProperty = new StringBuilder().append("\n").append(propertyName).append("=").append(propertyValue).toString();

        writer.seek(PROPERTIES_FILE.length());
        writer.write(newProperty.getBytes());

        writer.close();
        return readProperty(propertyName);
    }

    public void setOrRewriteProperty(String propertyName, String propertyValue) throws IOException {
        List<String> lines = Files.lines(PROPERTIES_FILE.toPath()).filter(l ->
                !(l.trim().contains(propertyName + "=") || l.trim().contains(propertyName))
        ).collect(Collectors.toList());

        FileOutputStream outputStream = new FileOutputStream(PROPERTIES_FILE);
        outputStream.write(("").getBytes());

        lines.forEach(l -> {
            String[] keyValue = l.trim().split("=");

            try {
                setProperty(keyValue[0], keyValue[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setProperty(propertyName, propertyValue);
        outputStream.close();
    }

    @Deprecated
    private static void removePropertyFromFile(String propertyName) throws IOException {
        File temporaryFile = new File("temporary.properties");
        temporaryFile.createNewFile();

        BufferedReader reader = new BufferedReader(new FileReader(PROPERTIES_FILE));
        BufferedWriter writer = new BufferedWriter(new FileWriter(temporaryFile));

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            System.out.println(line);

            if (line.trim().contains(propertyName + "=") || line.trim().contains(propertyName)) continue;

            writer.write(line + System.getProperty("line.separator"));
        }

        reader.close();
        writer.close();

        temporaryFile.delete();
    }
}
