package objects;

public class Coach {
    private int coachID;
    private String firstName, lastName, sports, category;

    public Coach(){}
    public Coach(int coachID, String firstName, String lastName, String sports, String category){
        this.coachID = coachID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sports = sports;
        this.category = category;

    }

    public void setCoachID(int coachID) {
        this.coachID = coachID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCoachID() {
        return coachID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSports() {
        return sports;
    }

    public String getCategory() {
        return category;
    }
}
