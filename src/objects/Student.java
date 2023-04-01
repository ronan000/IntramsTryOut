package objects;

import java.sql.*;
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
        else if(genderVerifier(student.getGender()) == false){
            System.out.println("Invalid gender input.");
        }
        else if (studIDVerifier(student.getID()) == false){
            System.out.println("Invalid ID number.");
        }
        else {
            String query = "INSERT INTO `student` (`studentID`, `fFirstName`, `LastName`, `gender`, `course`) VALUES (?, ?, ?, ?, ?);";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setInt(1, student.getID());
                statement.setString(2, student.getFirstName());
                statement.setString(3, student.getLastName());
                statement.setString(4, student.getGender());
                statement.setString(5, student.getCourse());
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

    public boolean genderVerifier(String gender){
        return gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("male");
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

    public void updateStudent(Student student, int newID){
        if(studentExist(student.getID()) == false){
            System.out.println("There is no student with ID number " + student.getID() + " in the registration database.");
        }
        else if(genderVerifier(student.getGender()) == false){
            System.out.println("Invalid gender input.");
        }
        else {
            String query = "UPDATE STUDENT SET STUDENTID = ?, FFIRSTNAME = ?, LASTNAME = ?, GENDER = ?, COURSE = ?  WHERE STUDENTID = ? ";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setInt(6, student.getID());
                statement.setString(2, student.getFirstName());
                statement.setString(3, student.getLastName());
                statement.setString(4, student.getGender());
                statement.setString(5, student.getCourse());
                statement.setInt(1, newID);
                statement.executeUpdate();
                System.out.println("Data for Student " + student.getID() + " is successfully updated.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public String toString() {
        return String.format("%-15s%-25s%-20s%-10s%-15s%n", ID, firstName, lastName.toUpperCase(), gender, course);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Student s = new Student();
        /*s.updateID(2200126, 2200126);
        s.getStudentsList();*/
        s.getStudentsList();
    }
}
