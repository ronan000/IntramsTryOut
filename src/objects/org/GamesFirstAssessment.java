package objects.org;

import objects.SetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GamesFirstAssessment {
    private String gameID, playerNum, result;
    private Date gameDate;
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    private List<GamesFirstAssessment> gamesFirstAssessmentsList = new ArrayList<>();



    public GamesFirstAssessment(){}
    public GamesFirstAssessment(String gameID, String playerNum, Date gameDate, String result){
        this.gameID = gameID;
        this.playerNum = playerNum;
        this.gameDate = gameDate;
        this.result = result;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public String getPlayerNum() {
        return playerNum;
    }

    public String getResult() {
        return result;
    }

    public void setPlayerNum(String playerNum) {
        this.playerNum = playerNum;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isValidResult(String result){
        return result.equalsIgnoreCase("lose") || result.equalsIgnoreCase("win") || result.equalsIgnoreCase("pend");
    }

    public boolean gameIDExists(String gameID){
        String query = "SELECT * FROM FIRSTGAMEASSESS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String s = resultSet.getString("game_ID");
                if (s.equalsIgnoreCase(gameID))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public void firstGameAssessmentList(){
        String query = "SELECT * FROM FIRSTGAMEASSESS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                gamesFirstAssessmentsList.add(new GamesFirstAssessment(resultSet.getString("game_ID"), resultSet.getString("player_num"), resultSet.getDate("game_date"), resultSet.getString("results")));
            }
            System.out.printf("%-15s%-15s%-15s%-15s%n", "GameID", "PlayerID", "GameDate", "Result");
            gamesFirstAssessmentsList.forEach((g) -> System.out.print(g));
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addGamesFirstAssessment(GamesFirstAssessment gamesFirstAssessment){
        Player p  = new Player();
        if(p.playerExists(gamesFirstAssessment.getPlayerNum()) == false){
            System.out.println("There is no player with that ID number in database.");
        }
        else if (isValidResult(gamesFirstAssessment.getResult()) == false){
            System.out.println("Invalid input remark.");
        }
        else{
            String query = "INSERT INTO `firstgameassess` (`game_ID`, `player_num`, `game_date`, `results`) VALUES (?, ?, ?, ? );";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, gamesFirstAssessment.getGameID());
                statement.setString(2, gamesFirstAssessment.getPlayerNum());
                statement.setDate(3, (java.sql.Date) gamesFirstAssessment.getGameDate());
                statement.setString(4, gamesFirstAssessment.getResult());
                statement.execute();
                System.out.println("A first game assessment is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%n", gameID, playerNum, gameDate, result);
    }
}
