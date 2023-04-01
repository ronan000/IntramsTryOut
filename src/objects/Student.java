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
    List<Student> students = new ArrayList<>();

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

    public boolean studIDVerifier(int studentID){
        int count = 0;
        String toInt = Integer.toString(studentID);
        char fNum = toInt.charAt(0);
        int firstNumber = Integer.parseInt(String.valueOf(fNum));
        while(studentID != 0){
            studentID /= 10;
            ++count;
        }
        if(count == 7 && firstNumber == 2) {
            return true;
        }
        return false;
    }
    public void registerStudent(Student student){
        if (studentExist(student.getID()) == true){
            System.out.println("Student " + student.getID() +  " is already registered.");
        }
        else if (studIDVerifier(student.getID()) == false){
            System.out.println("Invalid ID number.");
        }
        else {
            String query = "INSERT INTO `student` (`studentID`, `fFirstName`, `LastName`, `gender`, `course`) VALUES (?, ?, ?, ?, ?);";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, String.valueOf(student.getID()));
                statement.setString(2, String.valueOf(student.getFirstName()));
                statement.setString(3, String.valueOf(student.getLastName()));
                statement.setString(4, String.valueOf(student.getGender()));
                statement.setString(5, String.valueOf(student.getCourse()));
                statement.execute();
                System.out.println(student.getFirstName()  + " " + student.getLastName()  + " is successfully registered as applicant for tryout.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void getStudentsList() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
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

    public boolean studentExist(int studentID){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM STUDENT";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int s = Integer.parseInt(resultSet.getString("studentID"));
                if (s == studentID)
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void removeRegistration(int studentID){
        if(studentExist(studentID) == false){
            System.out.println("There is no student with ID number " + studentID + " registered in the database" );
        }
        else {
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = "DELETE FROM STUDENT WHERE STUDENTID = ?";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, String.valueOf(studentID));
                statement.execute();
                System.out.println("A student is successfully deleted from database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public String toString() {
        return String.format("%-15s%-25s%-20s%-10s%-15s%n", ID, firstName, lastName.toUpperCase(), gender, course);

    }

    public static void main(String[] args) {
        Student s = new Student();
        /*s.setFirstName("Jieben Kayla");
        s.setCourse("BSIT");
        s.setGender("Female");
        s.setLastName("Abaya");
        s.setID(2200465);
        s.registerStudent(s);*/
        s.removeRegistration(2200465);
        //System.out.println(s.studIDVerifier(2200465));
    }

}
