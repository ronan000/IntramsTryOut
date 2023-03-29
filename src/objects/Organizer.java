package objects;

public class Organizer {
    private String orgName, password;

    public Organizer(){
    }

    public Organizer(String orgName, String password){
        this.orgName = orgName;
        this.password = password;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getPassword() {
        return password;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
