package objects.coach;

import objects.SetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CoachMain {
    private int coachID, sportID;
    private String firstName, lastName;
    private Scanner scanner = new Scanner(System.in);
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;


    public CoachMain() {}

    public CoachMain(int coachID, String firstName, String lastName, int sportID) {
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
    public String toString() {
        return coachID + ": " + firstName + lastName + " | Sport: " + sportID;
    }

    public boolean login(int coachID) {
        String query = "SELECT * FROM COACH WHERE COACHID=?";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1,coachID);
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

    public void coachInfo(int coachID){
        String query = "SELECT COACH.FIRSTNAME, COACH.LASTNAME, SPORT.SPORTDESCRIPTION FROM COACH, SPORT WHERE COACH.COACHID=? AND SPORT.SPORTID=COACH.SPORTID;";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1, coachID);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String f = resultSet.getString("firstName");
                String l = resultSet.getString("lastName");
                String d = resultSet.getString("sportDescription");
                System.out.println("Welcome, Coach " + f + " " + l + "!");
                System.out.println("Sport: " + d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void coachMenu() {
        do {
            System.out.print("FIRST ASSESSMENT MENU\n" +
                    "[1] View Players\n" +
                    "[2] Modify Game Results\n" +
                    "[3] View Game Results\n" +
                    "[4] Exit\n" +
                    "Enter number of choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewPlayers();
                    break;
                case 2:
                    gameResults();
                    break;
                case 3:
                    viewGames();
                    break;
                case 4:
                    System.out.println("Thank you for using the system! :)");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose from the given choices ONLY.");
            }
        } while (true);
    }

    public void viewPlayers() {
        String query = ("SELECT * FROM PLAYERLIST WHERE COACHID = " + getCoachID());
        Players players = new Players();
        players.showListOfPlayers(query);
    }

    public void gameResults() {

    }

    public void viewGames() {

    }
}
