package objects.org;

import gui.commons.Sports;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Organizer {
    private String orgName, password;

    public Organizer(){
    }

    public Organizer(String orgName, String password){
        this.orgName = orgName;
        this.password = password;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getPassword() {
        return password;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void organizerMenu(){
        System.out.println("\t\t\t\tORGANIZER MENU\n" +
                "[1] Schedule Games\n" +
                "[2] Update Game\n" +
                "[3] Remove Game\n" +
                "[4] Set First Assessment Schedule\n" +
                "[5] Set Second Assessment Schedule\n" +
                "[6] Add Sports\n" +
                "[7] View List of Sports\n");
    }

    public void listOfSports() {
        List<Sports> sportsList = new ArrayList<>();
        String DB_URL = "jdbc:mysql://localhost:3306/tryouts";
        String USER = "root";
        String PASS = "";
        String QUERY = "SELECT * FROM sport";
        PreparedStatement ps;
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            ps = conn.prepareStatement(QUERY);
            ResultSet rs = ps.executeQuery();
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
               // System.out.print("sportID: " + rs.getInt("sportID"));
                int ID = Integer.valueOf(rs.getInt("sportID")) ;
                System.out.println(ID);
               // System.out.print(", sportDescription: " + rs.getString("sportDescription"));
                String desc =  String.valueOf(rs.getString("sportDescription"));
               /// System.out.print(", sCategory: " + rs.getString("sCategory"));
                String cat =  String.valueOf(rs.getString("sCategory"));
               // System.out.println(", sType: " + rs.getString("sType"));
                String type = String.valueOf(rs.getString("sType"));
                sportsList.add(new Sports(ID, desc, cat, type));
            }
            sportsList.forEach((s) -> System.out.println(s.toString()));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
