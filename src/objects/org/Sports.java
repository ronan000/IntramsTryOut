package objects.org;

import objects.SetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sports {
    private int sportsID;
    private String sportsDesc, sportsCat, sportType;
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    List<Sports> sports = new ArrayList<>();

    public Sports(){}
    public Sports(int sportsID, String sportsDesc, String sportsCat, String sportType){
        this.sportsID = sportsID;
        this.sportsDesc = sportsDesc;
        this.sportsCat = sportsCat;
        this.sportType = sportType;
    }

    public int getSportsID() {
        return sportsID;
    }

    public String getSportsCat() {
        return sportsCat;
    }

    public String getSportsDesc() {
        return sportsDesc;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportsID(int sportsID) {
        this.sportsID = sportsID;
    }

    public void setSportsCat(String sportsCat) {
        this.sportsCat = sportsCat;
    }

    public void setSportsDesc(String sportsDesc) {
        this.sportsDesc = sportsDesc;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public void getSportsList() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String query = "SELECT * FROM SPORT";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sports s = new Sports(Integer.valueOf(resultSet.getString("sportID")), resultSet.getString("sportDescription"), resultSet.getString("sCategory"), resultSet.getString("sType"));
                sports.add(s);
            }
            System.out.printf("%-15s%-15s%-15s%-15s%n", "SportsID", "SportsDesc", "SportsCat", "SportType");
            sports.forEach((sp) -> System.out.print(sp));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getSportsDesc(int sportsID) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String name = null;
        getSportsList();
        Iterator<Sports> iterator = sports.iterator();
        while(iterator.hasNext()){
            Sports sp = iterator.next();
            if(sportsID == sp.getSportsID()){
                name = sp.getSportsDesc();
            }
        }
        return name;
    }
    public int generateSportsID(){
        int ID = countSports() + 1;
        return ID;
    }
    public int countSports(){
        int counter= 0;
        String query = "SELECT SPORTID FROM SPORT ORDER BY SPORTID DESC LIMIT 1 ";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            counter = resultSet.getInt("sportID");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return counter;
    }

    public boolean sportsExists(int sporstID){
        String query = "SELECT * FROM SPORT";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int ID = Integer.parseInt(resultSet.getString("sportID"));
                if (ID == sporstID)
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean sportsExists(int sportsID, String desc, String cat, String type){
        String query = "SELECT * FROM SPORT";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String d = resultSet.getString("sportDescription");
                String c = resultSet.getString("sCategory");
                String t = resultSet.getString("sType");
                if (d.equalsIgnoreCase(desc) && c.equalsIgnoreCase(cat) && t.equalsIgnoreCase(type))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean sportsExists(String desc){
        String query = "SELECT * FROM SPORT";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String d = resultSet.getString("sportDescription");
                if (d.equalsIgnoreCase(desc))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public void searchSports(String sportsDesc) {
        if(sportsExists(sportsDesc) == true){
            String query = "SELECT * FROM SPORT WHERE SPORTDESCRIPTION = ?;";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, sportsDesc);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("sportID");
                    String desc = resultSet.getString("sportDescription");
                    String cat = resultSet.getString("sCategory");
                    String t = resultSet.getString("sType");
                    System.out.println("Search result for sports name " + sportsDesc + ":");
                    sports.add(new Sports(id, desc, cat, t));
                    System.out.printf("%-15s%-15s%-15s%-15s%n", "SportsID", "SportsDesc", "SportsCat", "SportType");
                    sports.forEach((s) -> System.out.print(s));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("There is no sports \"" + sportsDesc + " \" in the database." );
        }
    }


    public void addSports(Sports sports){
        if(sportsExists(sports.getSportsID(), sports.getSportsDesc(), sports.getSportsCat(), sports.getSportType()) == true){
            System.out.println("Sports already exist in the database.");
        }
        else if (isTeamSports(sports.getSportsDesc(),sports.getSportType()) == false && sports.getSportType().equalsIgnoreCase("teams")){
            System.out.println(sports.getSportsDesc() + " was assigned to invalid sports type.");
        }

        else{
            String query = "INSERT INTO `sport` (`sportID`, `sportDescription`, `sCategory`, `sType`) VALUES (?, ?, ?, ?);";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, String.valueOf(sports.getSportsID()));
                statement.setString(2, String.valueOf(sports.getSportsDesc()));
                statement.setString(3, String.valueOf(sports.getSportsCat()));
                statement.setString(4, String.valueOf(sports.getSportType()));
                statement.execute();
                System.out.println("Sports " + sports.getSportsDesc() + " for " + sports.getSportsCat() + " is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isTeamSports(String sportsDesc, String sportType){
       if(sportsDesc.equalsIgnoreCase("basketball") && sportsDesc.equalsIgnoreCase("volleyball") && sportsDesc.equalsIgnoreCase("sepak takraw")){
           return true;
       }
        return false;
    }

    public void removeSports(int sportsID){
        if(sportsExists(sportsID) == false){
            System.out.println("Sports with that ID number " + sportsID + " does not exist in the database.");
        }
        else{
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = "DELETE FROM SPORT WHERE SPORTID = ?";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, String.valueOf(sportsID));
                statement.execute();
                System.out.println("A sports is successfully deleted from database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%n", sportsID, sportsDesc, sportsCat, sportType);
    }

}
