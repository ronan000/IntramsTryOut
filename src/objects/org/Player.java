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
    private int coachID , sportID, studentID, teamID;
    private String playerID;
    private Connection con = null;

    List<Player> players = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public Player(){}

    public Player(String  playerID, int studentID, int sportID, int coachID, int teamID){
        this.playerID = playerID;
        this.studentID = studentID;
        this.sportID = sportID;
        this.coachID = coachID;
        this.teamID = teamID;
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

    public int getTeamID() {
        return teamID;
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

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    Student student = new Student();
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

    public boolean playerExists(int studentID){
        String query = "SELECT * FROM PLAYERLIST";
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
    public boolean playerExists(String playerID){
        String query = "SELECT * FROM PLAYERLIST";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String s = resultSet.getString("playerID");
                if (s.equalsIgnoreCase(playerID))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }
    public String getLastPlayerID(){
        String lastValue;
        String query = "SELECT PLAYERID FROM PLAYERLIST ORDER BY PLAYERID DESC LIMIT 1";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            lastValue = resultSet.getString("playerID");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lastValue;

    }

    public String generatePlayerID(){
        String lastPlayer = getLastPlayerID();
        StringBuffer number = new StringBuffer();
        for (int i = 0 ; i < lastPlayer.length(); i ++ ){
            if(Character.isDigit(lastPlayer.charAt(i)))
                number.append(lastPlayer.charAt(i));
        };
        return "PL" + String.format("%04d",Integer.parseInt(String.valueOf(number)) + 1);
    }


    public void acceptPlayers(Player player){
        if(playerExists(player.getStudentID()) == true){
            System.out.println("Player is already accepted.");
        }
        else if (student.studentExist(player.getStudentID()) == false){
            System.out.println("There is no applicant student with that given ID number.");
        }
        else{
            String query = "INSERT INTO `playerlist` (`playerID`, `studentID`, `coachID`, `sportID`, `teamID`) VALUES (?, ?, ?, ?, ?);";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, player.getPlayerID());
                statement.setInt(2, player.getStudentID());
                statement.setInt(3, player.getCoachID());
                statement.setInt(4, player.getSportID());
                statement.setInt(5, player.getTeamID());
                statement.execute();
                System.out.println("Player " + player.getPlayerID()  + " is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String searchPlayer(String playerID) {
        String query = "SELECT * FROM PLAYERLIST WHERE PLAYERID = ?;";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, playerID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("playerID");
                int studID = resultSet.getInt("studentID");
                int cID = resultSet.getInt("coachID");
                int sID = resultSet.getInt("sportID");
                int tID = resultSet.getInt("teamID");

                System.out.println("Search result for team name " + playerID + ":");
                System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "PlayerID", "StudentID", "CoachID", "SportID", "TeamID");
                return "" + new Player(id, studID, cID, sID, tID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "There is no data for player " +  playerID + " in the database";

    }

    public String showPlayerList() {
        String query = "SELECT * FROM PLAYERLIST";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Player p = new Player(resultSet.getString("playerID"), Integer.valueOf(resultSet.getString("studentID")), Integer.valueOf(resultSet.getString("coachID")), Integer.valueOf(resultSet.getString("sportID")), Integer.parseInt(resultSet.getString("teamID")));
                players.add(p);
            }

            System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "PlayerID", "StudentID", "CoachID", "SportID", "TeamID");
            players.forEach((p) -> System.out.print(p));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public void updatePlayerData (Player player){
        if(playerExists(player.getPlayerID()) == false) {
            System.out.println("There is no player with ID number " + player.getPlayerID() + ".");
        }
        else {
            String query = "UPDATE PLAYERLIST SET COACHID = ?, SPORTID = ?, TEAMID = ? WHERE PLAYERID = ?;";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setInt(1, player.getCoachID());
                statement.setInt(2, player.getSportID());
                statement.setInt(3, player.getTeamID());
                statement.setString(4, player.getPlayerID());
                statement.executeUpdate();
                System.out.println("The data for " + player.getPlayerID() + " is successfully updated." );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void removePlayer(String playerID){
        if(playerExists(playerID) == false){
            System.out.println("There is no player " + playerID + " in the database" );
        }
        else {
            String query = "DELETE FROM PLAYERLIST WHERE PLAYERID = ?";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, playerID);
                statement.execute();
                System.out.println("A player is successfully deleted from database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }



    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%-15s%n", playerID, studentID, sportID, coachID, teamID);
    }

}
