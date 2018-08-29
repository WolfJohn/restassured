package xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;
import pojos.xmlparse.Students;
import java.io.*;

public class ParseXML {

    @Test
    public void test() {
        ObjectMapper om = new XmlMapper();
        File xmlFile = new File(System.getProperty("user.dir") + "/files/student.xml");
        try {
            Students students = om.readValue(xmlFile, Students.class);

            students.getStudentList().forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

