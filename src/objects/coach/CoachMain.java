package objects.coach;

import objects.SetConnection;
import objects.org.Player;

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
        String query = "SELECT * FROM COACHES WHERE COACH_ID=?";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setInt(1,coachID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int  s = resultSet.getInt("coach_ID");
                if (s == coachID)
                    return true;
                else{
                    System.out.println("Invalid coach ID.");
                }
            }
            statement.close();
            con.close();
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
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void coachMenu() {
        do {
            System.out.print("\t\t\tCOACH MENU\n" +
                    "[1] View Players\n" +
                    "[2] View Game Results\n" +
                    "[3] Update Remarks for First Assessment\n" +
                    "[4] Exit\n" +
                    "Enter number of choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewPlayers();
                    break;
                case "2":
                    gameResults();
                    break;
                case "3":
                    FirstAssessmentGames f = new FirstAssessmentGames();
                    System.out.print("Enter Team ID to update: ");
                    String t = scanner.nextLine();
                    System.out.print("Enter Remark: ");
                    String r = scanner.nextLine();
                    f.updateRemark(Integer.parseInt(t), r);
                    break;
                case "4":
                    System.out.println("Thank you for using the system! :)");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose from the given choices ONLY.");
            }
        } while (true);
    }

    public void viewPlayers() {
        Player p = new Player();
        p.showPlayerList();
    }

    public void gameResults() {
        String query = "SELECT * FROM GAMERESULTS;";

        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "GameID", "TeamID", "Wins", "Losses", "SportID");
            while(resultSet.next()){
                String gID = resultSet.getString(1);
                int tID = resultSet.getInt(2);
                int w = resultSet.getInt(3);
                int l = resultSet.getInt(4);
                int sID = resultSet.getInt(5);
                System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", gID, tID, w, l, sID);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewGames() {

    }
}
