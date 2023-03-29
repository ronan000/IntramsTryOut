package objects.table;

public class Player {
    private int coachID, playerID, sportID, studentID;

    public Player(){}

    public Player(int playerID, int studentID, int sportID, int coachID){
        this.playerID = playerID;
        this.studentID = studentID;
        this.sportID = sportID;
        this.coachID = coachID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getSportID() {
        return sportID;
    }

    public int getCoachID() {
        return coachID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public void setSportID(int sportID) {
        this.sportID = sportID;
    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return playerID + ", " + studentID + ", " + sportID + ", " + coachID;
    }
}
