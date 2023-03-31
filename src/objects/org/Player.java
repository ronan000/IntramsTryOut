package objects.org;

import objects.SetConnection;
import objects.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int coachID , sportID, studentID;
    private String playerID;
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    List<Player> players = new ArrayList<>();

    public Player(){}

    public Player(String  playerID, int studentID, int sportID, int coachID){
        this.playerID = playerID;
        this.studentID = studentID;
        this.sportID = sportID;
        this.coachID = coachID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public int getSportID() {
        return sportID;
    }

    public int getCoachID() {
        return coachID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void showStudentApplicant(){
        Student student = new Student();
        try {
            student.getStudentsList();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String generatePlayerID(){
         int ID = countPlayers() + 1;
        return "PL" + String.format("%04d",ID);
    }
    public int countPlayers(){
        int counter= 0;
        String query = "SELECT COUNT(playerID) FROM PLAYERLIST";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            counter = resultSet.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counter;
    }

    //ON-HOLD
    public void acceptPlayers(String studentID){
        String pID = generatePlayerID();
        String query = "INSERT INTO `playerlist` (`playerID`, `studentID`, `coachID`, `sportID`) VALUES (?, ?, ?, ?);";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, pID);
            statement.setString(2, studentID);
            resultSet = statement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String showPlayerList() {
        String query = "SELECT * FROM PLAYERLIST";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Player p = new Player(resultSet.getString("playerID"), Integer.valueOf(resultSet.getString("studentID")), Integer.valueOf(resultSet.getString("coachID")), Integer.valueOf(resultSet.getString("sportID")));
                players.add(p);
            }
            System.out.printf("%-15s%-15s%-15s%-15s%n", "PlayerID", "StudentID", "CoachID", "SportID");
            players.forEach((p) -> System.out.print(p));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public void removePlayer(String playerID){
        String query = "DELETE FROM PLAYERLIST WHERE PLAYERID = ?";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, playerID);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

    }

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%n", playerID, studentID, coachID, sportID);
    }

    public static void main(String[] args) {
        Player p = new Player();
        //System.out.println(p.generatePlayerID());
        //System.out.println(p.countPlayers());
        System.out.println(p.showPlayerList());
    }
}
