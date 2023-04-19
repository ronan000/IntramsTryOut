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
    private String sportsDesc, sportsCat, sportType, sportsID;
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;
    List<Sports> sports = new ArrayList<>();

    public Sports(){}
    public Sports(String sportsID, String sportsDesc, String sportType, String sportsCat){
        this.sportsID = sportsID;
        this.sportsDesc = sportsDesc;
        this.sportsCat = sportsCat;
        this.sportType = sportType;
    }

    public String getSportsID() {
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

    public void setSportsID(String sportsID) {
        this.sportsID = sportsID;
    }

    public void setSportsCat(String sportsCat) {
        this.sportsCat = sportsCat.substring(0,1).toUpperCase() + sportsCat.substring(1);
    }

    public void setSportsDesc(String sportsDesc) {
        this.sportsDesc = sportsDesc.substring(0,1).toUpperCase() + sportsDesc.substring(1);
    }

    public void setSportType(String sportType) {
        this.sportType = sportType.substring(0,1).toUpperCase() + sportType.substring(1);
    }

    public void getSportsList() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String query = "SELECT * FROM SPORTS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Sports s = new Sports(resultSet.getString("sport_ID"), resultSet.getString("sport_name"), resultSet.getString("sport_type"), resultSet.getString("category"));
                sports.add(s);
            }
            System.out.printf("%-15s%-15s%-15s%-15s%n", "SportsID", "SportsDesc", "SportsCat", "SportType");
            sports.forEach((sp) -> System.out.print(sp));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String getSportsDesc(String sportsID) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String name = null;
        getSportsList();
        Iterator<Sports> iterator = sports.iterator();
        while(iterator.hasNext()){
            Sports sp = iterator.next();
            if(sportsID.equalsIgnoreCase(sp.getSportsID())){
                name = sp.getSportsDesc();
            }
        }
        return name;
    }
    public String generateSportsID(Sports sports){
        if(sports.getSportType().equalsIgnoreCase("team's")){
            if(sports.getSportsCat().equalsIgnoreCase("women's")){
                String letter = "B3" + String.format("%05d", countSports() + 1);
                return letter;
            }
            else if(sports.getSportsCat().equalsIgnoreCase("men's")){
                String letter = "A3" + String.format("%05d", countSports() + 1);
                return letter;
            }
            else if(sports.getSportsCat().equalsIgnoreCase("mixed")){
                String letter = "C3" + String.format("%05d", countSports() + 1);
                return letter;
            }
        }
        else if(sports.getSportType().equalsIgnoreCase("double's")){
            if(sports.getSportsCat().equalsIgnoreCase("women's")){
                String letter = "B2" + String.format("%05d", countSports() + 1);
                return letter;
            }
            else if(sports.getSportsCat().equalsIgnoreCase("men's")){
                String letter = "A2" + String.format("%05d", countSports() + 1);
                return letter;
            }
            else if (sports.getSportsCat().equalsIgnoreCase("mixed")){
                String letter = "C2" + String.format("%05d", countSports() + 1);
                return letter;
            }
        }
        else if(sports.getSportType().equalsIgnoreCase("single's")){
            if(sports.getSportsCat().equalsIgnoreCase("women's")){
                String letter = "B1" + String.format("%05d", countSports() + 1);
                return letter;
            }
            else if(sports.getSportsCat().equalsIgnoreCase("men's")){
                String letter = "A1" + String.format("%05d", countSports() + 1);
                return letter;
            }
            else if (sports.getSportsCat().equalsIgnoreCase("mixed")){
                String letter = "C1" + String.format("%05d", countSports() + 1);
                return letter;
            }
        }
        return "";
    }

    public boolean sportsNameExists(String desc) {
        String query = "SELECT * FROM SPORTS";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String d = resultSet.getString("sport_name");
                if (d.equalsIgnoreCase(desc))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean sportsExists(String sportsID, String desc, String cat, String type){
        String query = "SELECT * FROM SPORTS";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("sport_ID");
                String d = resultSet.getString("sport_name");
                String c = resultSet.getString("category");
                String t = resultSet.getString("sport_type");
                if (id.equalsIgnoreCase(sportsID) && d.equalsIgnoreCase(desc) && c.equalsIgnoreCase(cat) && t.equalsIgnoreCase(type))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean sportsExists(String sportsID){
        String query = "SELECT * FROM SPORTS";
        try{
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String d = resultSet.getString("sport_ID");
                if (d.equalsIgnoreCase(sportsID))
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean isValidCategory(String sportsCat){
        return sportsCat.equalsIgnoreCase("men's") || sportsCat.equalsIgnoreCase("women's") || sportsCat.equalsIgnoreCase("mixed");
    }


    public boolean isValidSportType(String type){
        return type.equalsIgnoreCase("team's") || type.equalsIgnoreCase("double's") || type.equalsIgnoreCase("single's");
    }



    public void searchSports(String sportsDesc) {
        if(sportsNameExists(sportsDesc) == true){
            String query = "SELECT * FROM SPORTS WHERE SPORT_NAME = ?;";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, sportsDesc);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("sport_ID");
                    String desc = resultSet.getString("sport_name");
                    String cat = resultSet.getString("category");
                    String t = resultSet.getString("sport_type");
                    sports.add(new Sports(id, desc, t, cat));
                }
                System.out.println("Search result for sports name " + sportsDesc + ":");
                System.out.printf("%-15s%-15s%-15s%-15s%n", "SportsID", "SportsDesc", "SportType", "SportsCat");
                sports.forEach((s) -> System.out.print(s));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("There is no sports \"" + sportsDesc + " \" in the database." );
        }
    }

    public int countSports(){
        String query = "SELECT COUNT(*) FROM SPORTS";
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


    public void addSports(Sports sports){
        if(sportsExists(sports.getSportsID(), sports.getSportsDesc(), sports.getSportsCat(), sports.getSportType()) == true){
            System.out.println("Sports already exist in the database.");
        }
        else if (isValidCategory(sports.getSportsCat()) == false && isValidSportType(sports.getSportType()) == false){
            System.out.println("Invalid sports category or type input.");
        }

        else{
            String query = "INSERT INTO `sports` (`sport_ID`, `sport_name`, `sport_type`, `category`) VALUES (?, ?, ?, ?);";
            try{
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, String.valueOf(sports.getSportsID()));
                statement.setString(2, String.valueOf(sports.getSportsDesc()));
                statement.setString(3, String.valueOf(sports.getSportType()));
                statement.setString(4, String.valueOf(sports.getSportsCat()));
                statement.execute();
                System.out.println("Sports " + sports.getSportsDesc() + " for " + sports.getSportsCat() + " is successfully added to the database.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void removeSports(String sportsID){
        if(sportsExists(sportsID) == false){
            System.out.println("Sports with ID number " + sportsID + " does not exist in the database.");
        }
        else{
            String query = "DELETE FROM SPORTS WHERE SPORT_ID = ?";
            try {
                con = SetConnection.getConnection();
                statement = con.prepareStatement(query);
                statement.setString(1, sportsID);
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

    public static void main(String[] args) {
        Sports s = new Sports();
        s.removeSports("B300005");
    }
}
