package objects.org;

public class Organizer {
    private String orgName, password;

    public Organizer() {
    }

    public Organizer(String orgName, String password) {
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

    public void organizerMenu() {
        System.out.println("\t\t\t\tORGANIZER MENU\n" +
                "[1] Schedule Games\n" +
                "[2] Update Game\n" +
                "[3] Remove Game\n" +
                "[4] Set First Assessment Schedule\n" +
                "[5] Set Second Assessment Schedule\n" +
                "[6] Add Sports\n" +
                "[7] View List of Sports\n");

    }
}
