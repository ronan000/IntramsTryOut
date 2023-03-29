package objects.org;

import java.util.List;

public class Team {
    private String teamID, sportID;
    private List<Player> members;

    public Team(){}

    public Team(String teamID, String sportID, List<Player> members){
        this. teamID = teamID;
        this.sportID = sportID;
        this.members = members;
    }

    public String getTeamID() {
        return teamID;
    }

    public List<Player> getMembers() {
        return members;
    }

    public String getSportID() {
        return sportID;
    }

    public void setSportID(String sportID) {
        this.sportID = sportID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public void setMembers(List<Player> members) {
        this.members = members;
    }
}
