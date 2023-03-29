package objects.table;

import java.sql.Time;
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
}
