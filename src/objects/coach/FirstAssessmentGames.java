package objects.coach;

import objects.SetConnection;
import objects.org.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FirstAssessmentGames {
    private String gameID, playerID, remark;
    private int teamID, sportID;
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    private List<FirstAssessmentGames> gamesList = new ArrayList<>();


    public FirstAssessmentGames(){}
    public FirstAssessmentGames(String gameID, int teamID, String playerID, int sportID, String remark){
        this.gameID  = gameID;
        this.playerID = playerID;
        this.remark = remark;
        this.teamID = teamID;
        this.sportID = sportID;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getSportID() {
        return sportID;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getRemark() {
        return remark;
    }


    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%-15s%n", gameID, teamID, playerID, sportID, remark);
    }

    public void showFirstAssessmentGames(){
        String query = "SELECT * FROM GAMEFIRSTASSESMENT;";
        try{
           con = SetConnection.getConnection();
           statement = con.prepareStatement(query);
           resultSet = statement.executeQuery();
           while (resultSet.next()){
               FirstAssessmentGames f = new FirstAssessmentGames(resultSet.getString("gameID"), resultSet.getInt("teamID"), resultSet.getString("playerID"), resultSet.getInt("sportID"), resultSet.getString("remark"));
               gamesList.add(f);
           }
            System.out.println("\t\tLIST OF GAMES FOR FIRST ASSESSMENT:");
            System.out.printf("%-15s%-15s%-15s%-15s%-15s%n", "GameID", "TeamID", "PlayerID", "SportID", "Remark");
            gamesList.forEach((g) -> System.out.print(g));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPending(int teamID){
        Team t = new Team();
        if(t.teamExists(teamID) == true){
            String query = "SELECT * FROM GAMEFIRSTASSESMENT WHERE TEAMID = ?;";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setInt(1, teamID);
                resultSet = statement.executeQuery();
                while(resultSet.next()){
                    String r = resultSet.getString("remark");
                    return r.equals("pending");
                }
                statement.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;

    }

    public void updateRemark(int teamID, String remark){
        if(isPending(teamID) == false){
            System.out.println("Team #"+ teamID + " already received a remark.");
        }
        else if(validRemark(remark) == false){
            System.out.println("Invalid remark.");
        }
        else{
            String query = "UPDATE GAMEFIRSTASSESMENT SET REMARK = ? WHERE TEAMID = ?;";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, remark);
                statement.setInt(2, teamID);
                statement.executeUpdate();
                System.out.println("Remark for Team #" + teamID + " is successfully updated.\n");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean validRemark(String remark){
        return remark.equalsIgnoreCase("Fail") || remark.equalsIgnoreCase("Pass");
    }

    public static void main(String[] args) {
        FirstAssessmentGames d = new FirstAssessmentGames();
        //d.showFirstAssessmentGames();
        System.out.println(d.isPending(78));
        d.updateRemark(89, "win");
    }
}
