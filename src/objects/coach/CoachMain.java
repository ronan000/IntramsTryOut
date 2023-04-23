package objects.coach;

import objects.SetConnection;
import objects.org.GamesFirstAssessment;
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

    public boolean login(String coachID) {
        String query = "SELECT * FROM COACHES WHERE COACH_ID=?";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1,coachID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String  s = resultSet.getString("coach_ID");
                if (s.equalsIgnoreCase(coachID))
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

    public void coachInfo(String coachID){
        String query = "SELECT COACHES.FIRST_NAME, COACHES.LAST_NAME, SPORTS.SPORT_NAME FROM COACHES, SPORTS WHERE COACHES.COACH_ID=? AND SPORTS.SPORT_ID=COACHES.SPORT_ID;";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, coachID);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String f = resultSet.getString("first_name");
                String l = resultSet.getString("last_name");
                String d = resultSet.getString("sport_name");
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
                    GamesFirstAssessment g = new GamesFirstAssessment();
                    g.firstGameAssessmentList();
                    break;
                case "3":
                    GamesFirstAssessment firstAssessment = new GamesFirstAssessment();
                    System.out.print("Enter the Player Number: ");
                    firstAssessment.setPlayerNum(scanner.nextLine());
                    System.out.print("Enter Game Result (win/lose/pend): ");
                    firstAssessment.setResult(scanner.nextLine());
                    firstAssessment.updateFirstGameRemark(firstAssessment);
                    break;
                case "4":
                    System.out.println("Thank you for using the system! :)");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please choose from the given choices ONLY.");
                    break;
            }
        } while (true);
    }

    public void viewPlayers() {
        Player p = new Player();
        p.showPlayerList();
    }

}
