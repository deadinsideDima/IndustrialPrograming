import java.io.*;
import java.util.*;

class Student implements Comparable<Student> {
    private Date birthDate;
    private String id;
    private String lastName;
    private double averageScore;
    private List<Integer> subjects;

    public Student(Date birthDate, String id, String lastName, double averageScore, List<Integer> subjects) {
        this.birthDate = birthDate;
        this.id = id;
        this.lastName = lastName;
        this.averageScore = averageScore;
        this.subjects = subjects;
    }

    // Геттеры и сеттеры для Student
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Integer> subjects) {
        this.subjects = subjects;
    }

    @Override
    public int compareTo(Student other) {
        return this.lastName.compareTo(other.lastName);
    }

    @Override
    public String toString() {
        return String.format("Birth Date: %s, ID: %s, Last Name: %s, Average Score: %.2f, Subjects: %s",
                birthDate.toString(), id, lastName, averageScore, subjects.toString());
    }
}