package objects.coach;

import objects.SetConnection;
import objects.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Players {
    private String playerID, firstName, lastName;
    private int studentID, coachID, sportID;
    private Connection con;
    List<Players> players = new ArrayList<>();
    PreparedStatement statement;
    ResultSet resultSet;

    public Players() {}

    public Players(String playerID, int studentID, int coachID) {
        this.playerID = playerID;
        this.studentID = studentID;
        this.coachID = coachID;
    }

    public String getPlayerID() {
        return playerID;
    }
    public int getStudentID() {
        return studentID;
    }
    public int getCoachID() {
        return coachID;
    }
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    Student student = new Student();

    public void showListOfPlayers(String query) {
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Players player = new Players(resultSet.getString("playerID"), resultSet.getInt("studentID"), resultSet.getInt("coachID"));
                players.add(player);
            }

            System.out.printf("%-15s%-15s%-15s%n", "PlayerID", "StudentID", "CoachID"); // add firstname, last name, sport, remove coach ID
            players.forEach((p) -> System.out.print(p));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
