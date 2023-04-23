package objects.coach;

public class GameResult {
    private String gameID;
    int teamID, wins, losses, sportID;

    public GameResult(){}
    public GameResult(String gameID, int teamID, int wins, int losses, int sportID){
        this.gameID = gameID;
        this.teamID = teamID;
        this.wins = wins;
        this.losses = losses;
        this. sportID = sportID;
    }

    public String getGameID() {
        return gameID;
    }

    public int getTeamID() {
        return teamID;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getSportID() {
        return sportID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }


    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }


    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%-15s%n", gameID, teamID, wins, losses, sportID);
    }

}
