package objects;

import objects.org.Sports;

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
    private Sports sports = new Sports();

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
                coaches.add(new Coach(resultSet.getInt("coachID"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("sportID")));

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
                int  s = resultSet.getInt("coachID");
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
        else if(sports.sportsExists(coach.getSportID()) == false){
            System.out.println("These is no sports with ID number " + coach.getSportID() +  " in the database;");
        } else if (sports.sportsExists(coach.getSportID()) == true) {
            System.out.println("The sports is already supervised by a coach.");
        } else {
            String query = "INSERT INTO `coach` (`coachID`, `firstName`, `lastName`, `sportID`) VALUES (?, ?, ?, ? );";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setInt(1, coach.getCoachID());
                statement.setString(2, coach.getFirstName());
                statement.setString(3, coach.getLastName());
                statement.setInt(4, coach.getSportID());
                statement.execute();
                System.out.println("Coach " + coach.getFirstName() + " " + coach.getLastName() + " is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void updateCoach(Coach coach){
        if(coachExists(coach.getCoachID()) == false){
            System.out.println("There is no coach with ID number " + coach.getCoachID() + " in the database.");
        }
        else if (sports.sportsExists(coach.getSportID()) == false){
            System.out.println("There is no sports with ID number " + coach.getSportID() + " in the database.");

        }
        else{
            String query = "UPDATE COACH SET FIRSTNAME = ?, LASTNAME = ?, SPORTID = ?  WHERE COACHID = ? ";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, coach.getFirstName());
                statement.setString(2, coach.getLastName());
                statement.setInt(3, coach.getSportID());
                statement.setInt(4, coach.getCoachID());
                statement.executeUpdate();
                System.out.println("Data for Coach " + coach.getCoachID() + " is successfully updated.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String searchCoach(int coachID) {
        String query = "SELECT * FROM COACH WHERE COACHID = ?;";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1, coachID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("coachID");
                String f = resultSet.getString("firstName");
                String l = resultSet.getString("lastName");
                int s = resultSet.getInt("sportID");
                System.out.println("Search result for coach ID " + coachID + ":");
                System.out.printf("%-15s%-25s%-20s%-12s%n", "CoachID", "FirstName", "LASTNAME", "SportID");
                return "" + new Coach(id, f, l, s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "There is no data for coach " +  coachID + " in the database";

    }

    public void showCoachList(){
        String query = "SELECT COACHID, FIRSTNAME, LASTNAME, COACH.SPORTID, SPORT.SPORTDESCRIPTION FROM COACH, SPORT WHERE COACH.SPORTID=SPORT.SPORTID";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "CoachID", "FirstName", "LastName", "SportsID", "SportDescription");
            while(resultSet.next()){
                int cID = resultSet.getInt(1);
                String f = resultSet.getString(2);
                String l = resultSet.getString(3);
                int sID = resultSet.getInt(4);
                String d = resultSet.getString(5);
                System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", cID, f, l, sID, d);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /*public void removeCoach(int coachID){
        if(coachExists(coachID) == false){
            System.out.println("There is no coach with an ID number " + coachID + " in the database" );
        }
        else {
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
    }*/

    @Override
    public String toString() {
        return String.format("%-15s%-25s%-20s%-12s%n", coachID, firstName, lastName.toUpperCase(), sportID);
    }
}
