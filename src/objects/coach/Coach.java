package objects.coach;

import objects.SetConnection;
import objects.org.Sports;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Coach {
    private Connection con = null;
    private String firstName, lastName, coachID, sportID;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    private List<Coach> coaches = new ArrayList<>();
    private Sports sports = new Sports();

    public Coach(){}
    public Coach(String coachID, String firstName, String lastName, String sportID){
        this.coachID = coachID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sportID = sportID;

    }

    public void setCoachID(String coachID) {
        this.coachID = coachID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSportID(String sportID) {
        this.sportID = sportID;
    }

    public String getSportID() {
        return sportID;
    }

    public String getCoachID() {
        return coachID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLastCoachID(){
        String lastID;
        String query = "SELECT COACH_ID FROM COACHES ORDER BY COACH_ID DESC LIMIT 1";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            lastID = resultSet.getString("coach_ID");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastID;
    }

    public String generateCoachID(){
        String lastCoachID = getLastCoachID();
        StringBuffer getNumber = new StringBuffer();
        for(int i = 0; i < lastCoachID.length(); i++){
            if(Character.isDigit(lastCoachID.charAt(i)))
            getNumber.append(lastCoachID.charAt(i));
        }
        return "C" + String.format("%06d", Integer.parseInt(String.valueOf(getNumber)) + 1);
    }

    public void getCoachList() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String query = "SELECT * FROM COACHES";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                coaches.add(new Coach(resultSet.getString("coach_ID"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("sport_ID")));

            }
            System.out.printf("%-15s%-25s%-20s%-12s%n", "CoachID", "FirstName", "LASTNAME", "SporstID");
            coaches.forEach((co) -> System.out.print(co));
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sportsIsSupervised(String sportsID){
        String query = "SELECT * FROM COACHES;";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                String id = resultSet.getString("sport_ID");
                if(sportsID.equalsIgnoreCase(id))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }
    public boolean coachExists(String coachID){
        String query = "SELECT * FROM COACHES";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String  s = resultSet.getString("coach_ID");
                if (s.equals(coachID))
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
        else if(sports.sportsIDExists(coach.getSportID()) == false){
            System.out.println("These is no sports with ID number " + coach.getSportID() +  " in the database;");
        }
        else if(sportsIsSupervised(coach.getSportID()) == true){
            System.out.println("Sports is already supervised by another coach.");
        }
        else {
            String query = "INSERT INTO `coaches` (`coach_ID`, `first_name`, `last_name`, `sport_ID`) VALUES (?, ?, ?, ? );";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, coach.getCoachID());
                statement.setString(2, coach.getFirstName());
                statement.setString(3, coach.getLastName());
                statement.setString(4, coach.getSportID());
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
            String query = "UPDATE COACHES SET FIRST_NAME = ?, LAST_NAME = ?, SPORT_ID = ?  WHERE COACH_ID = ? ";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, coach.getFirstName());
                statement.setString(2, coach.getLastName());
                statement.setString(3, coach.getSportID());
                statement.setString(4, coach.getCoachID());
                statement.executeUpdate();
                System.out.println("Data for Coach " + coach.getCoachID() + " is successfully updated.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String searchCoach(String coachID) {
        String query = "SELECT * FROM COACHES WHERE COACH_ID = ?;";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, coachID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("coach_ID");
                String f = resultSet.getString("first_name");
                String l = resultSet.getString("last_name");
                String s = resultSet.getString("sport_ID");
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
        String query = "SELECT * FROM COACHES";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            System.out.printf("%-15s%-15s%-15s%-15s%n", "CoachID", "FirstName", "LastName", "SportsID");
            while(resultSet.next()){
                String cID = resultSet.getString(1);
                String f = resultSet.getString(2);
                String l = resultSet.getString(3);
                String sID = resultSet.getString(4);
                System.out.printf("%-15s%-15s%-15s%-15s%n", cID, f, l, sID);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void removeCoach(String coachID){
        if(coachExists(coachID) == false){
            System.out.println("There is no coach with an ID number " + coachID + " in the database" );
        }
        else {
            String query = "DELETE FROM COACHES WHERE COACH_ID = ?";
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
