package pojos.xmlparse;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;

public class Student {
    @JacksonXmlProperty(isAttribute = true)
    private String course;
    @JacksonXmlProperty(localName = "first-name")
    private String firstName;
    @JacksonXmlProperty(localName = "last-name")
    private String lastName;
    private String university;

    public Student(){}

    public Student(String firstName, String lastName, String university) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.university = university;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) &&
                Objects.equals(lastName, student.lastName) &&
                Objects.equals(university, student.university);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, university);
    }

    @Override
    public String toString() {
        return "Student{" +
                "course='" + course + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", university='" + university + '\'' +
                '}';
    }
}
