package objects.org;

import objects.SetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private int teamID;
    private String teamName;
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    private List<Team> teamList = new ArrayList<>();

    public Team(){}

    public Team(int teamID, String teamName){
        this.teamID = teamID;
        this.teamName = teamName;
    }

    public int getTeamID() {
        return teamID;
    }

    public String getTeamName() {
        return teamName.toUpperCase();
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int generateTeamID(){
        int counter = 0;
        String query = "SELECT TEAMID FROM TEAMS ORDER BY TEAMID DESC LIMIT 1";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            counter = resultSet.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counter + 1;
    }

    public boolean teamNameTaken(String teamName){
        String query = "SELECT * FROM TEAMS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String  t = resultSet.getString("teamName");
                if (t.equalsIgnoreCase(teamName))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean teamExists(int teamID){
        String query = "SELECT * FROM TEAMS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int  t = Integer.parseInt(resultSet.getString("teamID"));
                if (t == teamID)
                    return true;
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void showTeamList(){
        String query = "SELECT * FROM TEAMS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Team t = new Team(Integer.parseInt(resultSet.getString("teamID")), resultSet.getString("teamName"));
                teamList.add(t);
            }
            System.out.println("\t\tLIST OF TEAMS:");
            System.out.printf("%-10s%-20s%n", "TeamID", "TEAMNAME");
            teamList.forEach((t) -> System.out.print(t));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String searchTeam(String teamName) {
        String query = "SELECT * FROM TEAMS WHERE TEAMNAME = ?;";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            statement.setString(1, teamName);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("teamID");
                String name = resultSet.getString("teamName");
                System.out.println("Search result for team name " + teamName + ":");
                System.out.printf("%-10s%-20s%n", "TeamID", "TeamName");
                return "" + new Team(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
    }
        return "There is no data for team " +  teamName.toUpperCase() + " in the database";

    }

    public void addTeam(Team team){
        if(teamExists(team.getTeamID()) == true){
            System.out.println("Team with ID number " + team.getTeamID() + " already exists in database.");
        }
        if(teamNameTaken(team.getTeamName()) == true){
            System.out.println("The team name \"" + team.getTeamName() + "\" is already taken." );
        }
        else {
            String query = "INSERT INTO `teams` (`teamID`, `teamName`) VALUES (?, ?);";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setInt(1, team.getTeamID());
                statement.setString(2, team.getTeamName());

                statement.execute();
                System.out.println("Team " + team.getTeamName()  + " is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeTeam(String teamName){
        if(teamNameTaken(teamName) == false){
            System.out.println("There is no team \"" + teamName + "\" in the database" );
        }
        else {
            String query = "DELETE FROM TEAMS WHERE TEAMNAME = ?";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, teamName);
                statement.execute();
                System.out.println("A team is successfully deleted from database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public String toString() {
        return String.format("%-10s%-20s%n", teamID, teamName);
    }

}
