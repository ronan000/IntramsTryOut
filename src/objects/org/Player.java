package objects.org;

import objects.SetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int studentID;
    private String playerID, sportID;
    private Connection con = null;

    List<Player> players = new ArrayList<>();
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public Player(){}

    public Player(String  playerID, int studentID, String sportID){
        this.playerID = playerID;
        this.studentID = studentID;
        this.sportID = sportID;

    }

    public String getPlayerID() {
        return playerID;
    }

    public String getSportID() {
        return sportID;
    }


    public int getStudentID() {
        return studentID;
    }


    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setSportID(String sportID) {
        this.sportID = sportID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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
        String query = "SELECT * FROM REGISTEREDPLAYERS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int s = resultSet.getInt("stud_ID");
                if (s == studentID)
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean playerExists(String playerID){
        String query = "SELECT * FROM REGISTEREDPLAYERS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String s = resultSet.getString("player_num");
                if (s.equalsIgnoreCase(playerID))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public String insertNew(){
        String q = "SELECT COUNT(*) FROM REGISTEREDPLAYERS";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(q);
            resultSet = statement.executeQuery();
            resultSet.next();
            int total = resultSet.getInt(1);
            return String.valueOf(total);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getLastPlayerID(){
        insertNew();
        if(Integer.parseInt(insertNew()) == 0){
            return insertNew();
        }
        else {
            String lastValue;
            String query = "SELECT PLAYER_NUM FROM REGISTEREDPLAYERS ORDER BY PLAYER_NUM DESC LIMIT 1";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                resultSet = statement.executeQuery();
                resultSet.next();
                lastValue = resultSet.getString("player_num");

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return lastValue;
        }
    }

    public String generatePlayerID(){
        String lastPlayer = getLastPlayerID();
        StringBuffer number = new StringBuffer();
        for (int i = 0 ; i < lastPlayer.length(); i ++ ){
            if(Character.isDigit(lastPlayer.charAt(i)))
                number.append(lastPlayer.charAt(i));
        }
        return "PL" + String.format("%03d",Integer.parseInt(String.valueOf(number)) + 1);
    }


    public void acceptPlayers(Player player){
        if(playerExists(player.getStudentID()) == true){
            System.out.println("Player is already accepted.");
        }
        else if (student.studentExist(player.getStudentID()) == false){
            System.out.println("There is no applicant student with that given ID number.");
        }
        else{
            String query = "INSERT INTO `REGISTEREDPLAYERS` (`player_num`, `stud_ID`, `sport_ID`) VALUES (?, ?, ?);";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, player.getPlayerID());
                statement.setInt(2, player.getStudentID());
                statement.setString(3, player.getSportID());
                statement.execute();
                System.out.println("Player " + player.getPlayerID()  + " is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String searchPlayer(String playerID) {
        String query = "SELECT * FROM REGISTEREDPLAYERS WHERE PLAYER_NUM = ?;";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, playerID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("player_num");
                int studID = resultSet.getInt("stud_ID");
                String sID = resultSet.getString("sport_ID");

                System.out.println("Search result for team name " + playerID + ":");
                System.out.printf("%-15s%-15s%-15s%n", "PlayerID", "StudentID", "SportID");
                return "" + new Player(id, studID, sID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "There is no data for player " +  playerID + " in the database";

    }

    public String showPlayerList() {
        String query = "SELECT * FROM REGISTEREDPLAYERS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Player p = new Player(resultSet.getString("player_num"), resultSet.getInt("stud_ID"), resultSet.getString("sport_ID"));
                players.add(p);
            }

            System.out.printf("%-15s%-15s%-15s%n", "PlayerID", "StudentID",  "SportID");
            players.forEach((p) -> System.out.print(p));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }


    public void showListPlayers(){
        String query = "SELECT PLAYER_NUM, STUDENTS.STUD_ID, STUDENTS.FIRST_NAME, STUDENTS.LAST_NAME, SPORTS.SPORT_NAME FROM REGISTEREDPLAYERS, STUDENTS, SPORTS WHERE STUDENTS.STUD_ID=REGISTEREDPLAYERS.STUD_ID AND REGISTEREDPLAYERS.SPORT_ID = SPORTS.SPORT_ID";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "PlayerID"," StudentID","FirstName", "LastName", "Sports");
            while (resultSet.next()){
                String pID = resultSet.getString(1);
                int sID = resultSet.getInt(2);
                String fName = resultSet.getString(3);
                String lName = resultSet.getString(4);
                String spoDesc = resultSet.getString(5);
                System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", pID, sID, fName, lName, spoDesc);
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updatePlayerData (Player player){
        if(playerExists(player.getPlayerID()) == false) {
            System.out.println("There is no player with ID number " + player.getPlayerID() + ".");
        }
        else {
            String query = "UPDATE REGISTEREDPLAYERS SET PLAYER_NUM = ?, SPORT_ID = ? WHERE PLAYER_NUM = ?;";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, player.getPlayerID());
                statement.setString(2, player.getSportID());
                statement.setString(3, player.getPlayerID());
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
            String query = "DELETE FROM REGISTEREDPLAYERS WHERE PLAYER_NUM = ?";
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
        return String.format("%-15s%-15s%-15s%n", playerID, studentID, sportID);
    }

}
