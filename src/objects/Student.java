package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private int ID;
    private String firstName, lastName, course, gender;
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;


    public Student() {}
    public Student(int ID, String firstName, String lastName, String gender, String course){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.gender = gender;

    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCourse() {
        return course;
    }

    public String getGender() {
        return gender;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void getStudentsList() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM STUDENT";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student s = new Student(Integer.valueOf(resultSet.getString("studentID")), resultSet.getString("fFirstName"), resultSet.getString("LastName"), resultSet.getString("gender"), resultSet.getString("course"));
                students.add(s);
            }
            System.out.printf("%-15s%-25s%-20s%-10s%-15s%n", "StudentID", "FirstName", "LASTNAME", "Gender", "Course");
            students.forEach((sp) -> System.out.print(sp));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString() {
        return String.format("%-15s%-25s%-20s%-10s%-15s%n", ID, firstName, lastName.toUpperCase(), gender, course);

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Student s = new Student();
        s.getStudentsList();
    }
}
