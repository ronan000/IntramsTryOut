package objects;

public class Student {
    private int ID;
    private String firstName;
    private String lastName;
    private String course;
    private String gender;
    private String sport;

    public Student() {}
    public Student(int ID, String firstName, String lastName, String course, String gender, String sport){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.gender = gender;
        this.sport = sport;
    }

    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCourse() {
        return course;
    }

    public String getSport() {
        return sport;
    }

    public String getGender() {
        return gender;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
