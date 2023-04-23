package objects.org;

import objects.SetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public String getPlayersSports(String playerNum){
        String query = "SELECT * FROM REGISTEREDPLAYERS WHERE PLAYER_NUM = ?";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, playerNum);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String sID = resultSet.getString("sport_ID");
                return sID;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public int countSchedule(){
        String query = "SELECT COUNT(*) FROM FIRSTGAMEASSESS";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            int total = resultSet.getInt(1);
            return total;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateGameID(String playerNum){
        String pNum = getPlayersSports(playerNum);
        StringBuilder f = new StringBuilder("a");
        String number = String.valueOf(countSchedule());
        f.append(pNum.charAt(0));
        f.append(pNum.charAt(1));
        if("0".equalsIgnoreCase(String.valueOf(pNum.charAt(5)))){
            f.append(pNum.charAt(6));
        }
        else{
            f.append(pNum.charAt(5));
            f.append(pNum.charAt(6));
        }
        return f + "" + String.format("%03d", Integer.parseInt(number));
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

    public boolean firstGameExists(String playerNum){
        String query = "SELECT * FROM FIRSTGAMEASSESS";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                String id = resultSet.getString("player_num");
                if(id.equalsIgnoreCase(playerNum))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  false;
    }

    public boolean isValidDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        format.setLenient(false);
        try{
            Date d = format.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public java.sql.Date convertFromJAVADateToSQLDate(java.util.Date javaDate) {
        java.sql.Date sqlDate = null;
        if (javaDate != null) {
            sqlDate = new java.sql.Date(javaDate.getTime());
        }
        return sqlDate;
    }

    public void addGamesFirstAssessment(GamesFirstAssessment gamesFirstAssessment) throws ParseException {
        Player p  = new Player();
        Date current = new Date();
        if(p.playerExists(gamesFirstAssessment.getPlayerNum()) == false){
            System.out.println("There is no player with that ID number in database.");
        }
        else if(firstGameExists(gamesFirstAssessment.getPlayerNum()) == true){
            System.out.println("Schedule for Player " + gamesFirstAssessment.getPlayerNum() + " is already set. Please do update.");
        }
        else if (isValidResult(gamesFirstAssessment.getResult()) == false){
            System.out.println("Invalid input remark.");
        }
        else if (gamesFirstAssessment.getGameDate().before(current)){
            System.out.println("The input date is older than current day.");
        }
        else{
            String query = "INSERT INTO `firstgameassess` (`game_ID`, `player_num`, `game_date`, `results`) VALUES (?, ?, ?, ? );";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, gamesFirstAssessment.getGameID());
                statement.setString(2, gamesFirstAssessment.getPlayerNum());
                statement.setDate(3, convertFromJAVADateToSQLDate(gamesFirstAssessment.getGameDate()));
                statement.setString(4, gamesFirstAssessment.getResult());
                statement.execute();
                System.out.println("A first game assessment is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String searchFirstGameAssess(String playerNum){
        String query = "SELECT * FROM FIRSTGAMEASSESS WHERE PLAYER_NUM = ? ";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, playerNum);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                String gID = resultSet.getString("game_ID");
                String pID = resultSet.getString("player_num");
                Date d = resultSet.getDate("game_date");
                String r = resultSet.getString("results");
                System.out.println("Search result: ");
                System.out.printf("%-15s%-15s%-15s%-15s%n", "GameID", "PlayerNum", "GameDate", "Result");
                return "" + new GamesFirstAssessment(gID, pID, d, r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "There is no schedule for player number: " + playerNum + " in the database.";
    }

    public void updateFirstGameDate(GamesFirstAssessment gamesFirstAssessment){
        Date current = new Date();
        if(firstGameExists(gamesFirstAssessment.getPlayerNum()) == false){
            System.out.println("Schedule for Player " + gamesFirstAssessment.getPlayerNum() + " is not yet created. Please create.");
        }
        else if (gamesFirstAssessment.getGameDate().before(current)){
            System.out.println("The input date is older than current day.");
        }
        else{
            String query = "UPDATE FIRSTGAMEASSESS SET GAME_DATE = ?  WHERE PLAYER_NUM = ? ";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setDate(1, convertFromJAVADateToSQLDate(gamesFirstAssessment.getGameDate()));
                statement.setString(2, gamesFirstAssessment.getPlayerNum());
                statement.executeUpdate();
                System.out.println("The date schedule for Player " + gamesFirstAssessment.getPlayerNum() + " is successfully updated.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateFirstGameRemark(GamesFirstAssessment gamesFirstAssessment){
        if(firstGameExists(gamesFirstAssessment.getPlayerNum()) == false){
            System.out.println("Schedule for Player " + gamesFirstAssessment.getPlayerNum() + " is not yet created. Please create.");
        }
        else{
            String query = "UPDATE FIRSTGAMEASSESS SET RESULTS = ?  WHERE PLAYER_NUM = ? ";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, gamesFirstAssessment.getResult());
                statement.setString(2, gamesFirstAssessment.getPlayerNum());
                statement.executeUpdate();
                System.out.println("The game result for Player " + gamesFirstAssessment.getPlayerNum() + " is successfully updated.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeFirstGameSchedule(GamesFirstAssessment firstAssessment){
        if(firstGameExists(firstAssessment.getPlayerNum()) == false){
            System.out.println("There is no data for Player " + firstAssessment.getPlayerNum() + " in the database.");
        }
        else{
            String query = "DELETE FROM FIRSTGAMEASSESS WHERE PLAYER_NUM = ?";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, firstAssessment.getPlayerNum());
                statement.execute();
                System.out.println("A schedule is successfully deleted from database.");
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
