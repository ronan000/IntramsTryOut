package objects.org;

public class GamesSecondAssessment {
    private int gameID, gSecondID, sportID;
    private String playerID, remark;

    public GamesSecondAssessment(){}
    public GamesSecondAssessment(int gameID, int gSecondID, String playerID, int sportID, String remark){
        this.gameID = gameID;
        this.gSecondID = gSecondID;
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

    public int getgSecondID() {
        return gSecondID;
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

    public void setgSecondID(int gSecondID) {
        this.gSecondID = gSecondID;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return gSecondID + ", " + gameID + ", " + playerID + ", " + sportID + ", " + remark;
    }
}
