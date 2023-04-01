package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Coach {
    private int coachID, sportID;
    private Connection con = null;
    private String firstName, lastName;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    private List<Coach> coaches = new ArrayList<>();

    public Coach(){}
    public Coach(int coachID, String firstName, String lastName, int sportID){
        this.coachID = coachID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sportID = sportID;

    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }

    public int getSportID() {
        return sportID;
    }

    public int getCoachID() {
        return coachID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int generateCoachID(){
        int counter = 0;
        String query = "SELECT COACHID FROM COACH ORDER BY COACHID DESC LIMIT 1";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            counter = resultSet.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counter + 1;

    }

    public void getCoachList() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String query = "SELECT * FROM COACH";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Coach c = new Coach(resultSet.getInt("coachID"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt(sportID));
                coaches.add(c);
            }
            System.out.printf("%-15s%-25s%-20s%-12s%n", "CoachID", "FirstName", "LASTNAME", "SporstID");
            coaches.forEach((co) -> System.out.print(co));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean coachExists(int coachID){
        String query = "SELECT * FROM COACH";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int  s = Integer.parseInt(resultSet.getString("coachID"));
                if (s == coachID)
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void addCoach(Coach coach){
        if(coachExists(coach.getCoachID()) == true){
            System.out.println(coach.getFirstName() + " " + coach.getLastName() + " is already registered as a coach.");
        }
        else {
            String query = "INSERT INTO `coach` (`coachID`, `firstName`, `lastName`, `sportID`) VALUES (?, ?, ?, ? );";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setInt(1, coach.generateCoachID());
                statement.setString(2, coach.getFirstName());
                statement.setString(3, coach.getLastName());
                statement.setInt(4, coach.getSportID());
                statement.execute();
                System.out.println("Coach " + coach.getFirstName() + " " + coach.getLastName()  + " is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void updateCoach(Coach coach){
        if(coachExists(coach.getCoachID()) == false){
            System.out.println("There is no student with ID number " + coach.getCoachID() + " in the registration database.");
        }
        else{
            String query = "UPDATE STUDENT SET FIRSTNAME = ?, LASTNAME = ?, SPORTID = ?  WHERE COACHID = ? ";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, coach.getFirstName());
                statement.setString(2, coach.getLastName());
                statement.setInt(3, coach.getSportID());
                statement.setInt(4, coach.getCoachID());
                statement.executeUpdate();
                System.out.println("Data for Student " + coach.getCoachID() + " is successfully updated.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeCoach(int coachID){
        if(coachExists(coachID) == false){
            System.out.println("There is no coach with an ID number " + coachID + " in the database" );
        }
        else {
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = "DELETE FROM COACH WHERE COACHID = ?";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, String.valueOf(coachID));
                statement.execute();
                System.out.println("A coach is successfully deleted from database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%-15s%-25s%-20s%-12s%n", coachID, firstName, lastName.toUpperCase(), sportID);
    }

}
