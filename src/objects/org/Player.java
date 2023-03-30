package objects.org;

import objects.SetConnection;
import objects.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Player {
    private int coachID, playerID, sportID, studentID;
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public Player(){}

    public Player(int playerID, int studentID, int sportID, int coachID){
        this.playerID = playerID;
        this.studentID = studentID;
        this.sportID = sportID;
        this.coachID = coachID;
    }

    public int getPlayerID() {
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

    public void setPlayerID(int playerID) {
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

    @Override
    public String toString() {
        return playerID + ", " + studentID + ", " + sportID + ", " + coachID;
    }

    public static void main(String[] args) {
        Player p = new Player();
        System.out.println(p.generatePlayerID());
        //System.out.println(p.countPlayers());
    }
}
