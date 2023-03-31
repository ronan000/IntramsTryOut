package objects.org;

import objects.SetConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
            System.out.printf("%-15s%-15s%-15s%-15s%n", "sportsID", "sportsDesc", "sportsCat", "sportType");
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

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%n", sportsID, sportsDesc, sportsCat, sportType);
    }
    /*public static void main(String[] args) {
        Sports s = new Sports();
        try {
            s.getSportsList();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static void main(String[] args) {
        Sports s = new Sports();
        try {
            System.out.println(s.getSportsDesc(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
