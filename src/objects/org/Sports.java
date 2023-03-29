package objects.org;

public class Sports {
    private int sportsID;
    private String sportsDesc, sportsCat, sportType;

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

    @Override
    public String toString() {
        return String.format("%-15s%-15s%-15s%-15s%n", sportsID, sportsDesc, sportsCat, sportType);
    }
}
