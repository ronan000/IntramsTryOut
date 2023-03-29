package objects.table;

import java.util.Iterator;
import java.util.List;

public class GamesFirstAssessment {
    private int gameID, gFirstID, sportID;
    private String playerID;
    private String remark;

    public GamesFirstAssessment(){}
    public GamesFirstAssessment(int gameID, int gFirstID, String playerID, int sportID, String remark){
        this.gameID = gameID;
        this.gFirstID = gFirstID;
        this.playerID = playerID;
        this.sportID = sportID;
        this.remark = remark;
    }

    public int getGameID() {
        return gameID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public int getSportID() {
        return sportID;
    }

    public int getgFirstID() {
        return gFirstID;
    }

    public String getRemark() {
        return remark;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setgFirstID(int gFirstID) {
        this.gFirstID = gFirstID;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void updateFirstAssSched(int gFirstID, int gameID, String playerID, int sportID, List<GamesFirstAssessment> first){
        Iterator<GamesFirstAssessment> firstAssessmentIterator = first.iterator();
        while (firstAssessmentIterator.hasNext()){
            GamesFirstAssessment firstAssessment = firstAssessmentIterator.next();
            if(gFirstID == firstAssessment.getgFirstID()){
                firstAssessment.setGameID(gameID);
                firstAssessment.setPlayerID(playerID);
                firstAssessment.setSportID(sportID);
            }
        }
    }

    public  void updateRemark(int gFirstID, List<GamesFirstAssessment> first, String remark){
        Iterator<GamesFirstAssessment> firstAssessmentIterator = first.iterator();
        while (firstAssessmentIterator.hasNext()){
            GamesFirstAssessment firstAssessment = firstAssessmentIterator.next();
            if(gFirstID == firstAssessment.getgFirstID()){
                firstAssessment.setRemark(remark);
            }
        }
    }

    @Override
    public String toString() {
        return gFirstID + ", " + gameID + ", " + playerID + ", " + sportID + ", " + remark;
    }
}
