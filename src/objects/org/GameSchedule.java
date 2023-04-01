package objects.org;

/*import java.sql.Time;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GameSchedule {
    private int gameID, sportID;
    private String playerID;
    private Date gDate;
    private Time gTime;

    public GameSchedule(){}
    public GameSchedule(int gameID, String playerID, int sportID, Date gDate, Time gTime){
        this.gameID = gameID;
        this.playerID = playerID;
        this.sportID = sportID;
        this.gDate = gDate;
        this.gTime = gTime;
    }

    public GameSchedule(Date gDate, Time time){

    }

    public int getGameID() {
        return gameID;
    }

    public int getSportID() {
        return sportID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public Date getgDate() {
        return gDate;
    }

    public Time getgTime() {
        return gTime;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setgDate(Date gDate) {
        this.gDate = gDate;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }

    public void setgTime(Time gTime) {
        this.gTime = gTime;
    }

    public void updateGameSchedule(int gameID, List<GameSchedule> scheduleList, Date date, Time time){
        Iterator<GameSchedule> scheduleIterator = scheduleList.iterator();
        while (scheduleIterator.hasNext()){
            GameSchedule gameSchedule = scheduleIterator.next();
            if(gameID == gameSchedule.getGameID()){
                gameSchedule.setgDate(date);
                gameSchedule.setgTime(time);
            }
        }
    }

    public String toString(){
        return gameID  + ", " + playerID  + ", " + sportID + ", " + gDate + ", " + gTime;
    }

}*/
import java.sql.*;
import java.util.Scanner;

public class GameSchedule {
    public static void main(String[] args) throws SQLException {
        Scanner st = new Scanner(System.in);
        Scanner num = new Scanner(System.in);
        int choices;
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tryouts", "root", "");
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("select * from gameschedule");

        do {
            System.out.println("CRUD Operations using java");
            System.out.println("1.Create");
            System.out.println("2.Retrieve");
            System.out.println("3.Delete");
            System.out.println("4.Update");
            System.out.println("5.EXIT");
            System.out.println("Select your desired operation: ");
            choices = num.nextInt();

            switch (choices) {
                case 1:
                    System.out.println("Enter Game ID:");
                    int gameID = num.nextInt();
                    System.out.println("Enter Player ID");
                    String playerID = st.nextLine();
                    System.out.println("Enter Date (yyyy-MM-dd)");
                    String gDate  = st.nextLine();
                    //SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                    System.out.println("Enter Time (hh:mm)");
                    String gTime = st.nextLine();
                    System.out.println("Enter Sport ID");
                    int sportID = num.nextInt();

                    PreparedStatement pst = con.prepareStatement(
                            "INSERT INTO gameschedule (gameID, playerID, gDate, gTime, sportID) VALUES (?, ?, ?, ?, ?);");

                    pst.setInt(1, gameID);
                    pst.setString(2,playerID);
                    pst.setString(3, gDate);
                    pst.setString(4, gTime);
                    pst.setInt(5,sportID);
                    pst.execute();
                    System.out.println("Added Successfully");



                    break;
                case 2:
                    while (rs.next()) {
                        System.out.println(rs.getInt("gameID"));
                        System.out.println(rs.getString("playerID"));
                        System.out.println(rs.getString("gDate"));
                        System.out.println(rs.getString("gTime"));
                        System.out.println(rs.getString("sportID"));

                    }
                    break;
                case 3:
                    pst = con.prepareStatement("delete from gameschedule where gameID = ?");
                    System.out.println("Enter the game ID that is to be deleted:");
                    gameID = num.nextInt();
                    pst.setString(1, String.valueOf(gameID));
                    pst.execute();
                    System.out.println("Successfully Deleted");
                    break;
                case 4:
                    pst = con.prepareStatement("update gameschedule set  playerID=?, gDate=?, gTime=?, sportID=? where gameID = ?");
                    System.out.println("Enter ID number that is to be updated:");
                    gameID = num.nextInt();
                    pst.setInt(3, gameID);
                    System.out.println("Update Player ID");
                    playerID= st.nextLine();
                    System.out.println("Update Date");
                    gDate = st.nextLine();
                    System.out.println("Update Time");
                    gTime = st.nextLine();
                    System.out.println("Update Sport ID");
                    sportID = st.nextInt();
                    pst.setString(1, playerID);
                    pst.setString(2, gDate);
                    pst.setString(3, gTime);
                    pst.setInt(4, sportID);
                    pst.setInt(5,gameID);
                    pst.execute();
                    System.out.println("Update Successful");
                    break;
                case 5:
                    System.out.println("Thank you and Goodbye!");
                    System.exit(0);

                    break;
            }
        } while (choices != 0);

    }
}



