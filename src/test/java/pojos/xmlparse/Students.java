package pojos.xmlparse;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;
import java.util.Objects;

public class Students {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "student")
    private List<Student> studentList;

    public Students() {}

    public Students(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students1 = (Students) o;
        return Objects.equals(studentList, students1.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentList);
    }

    @Override
    public String toString() {
        return "Students{" +
                "studentList=" + studentList +
                '}';
    }
}
