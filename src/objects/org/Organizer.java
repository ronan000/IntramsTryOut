package objects.org;

import objects.Coach;
import objects.SetConnection;
import objects.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Organizer {
    private String orgName;
    private int password;
    private Scanner keyboard = new Scanner(System.in);
    private Connection con = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    public Organizer() {
    }

    public Organizer(String orgName, int password) {
        this.orgName = orgName;
        this.password = password;
    }

    public String getOrgName() {
        return orgName;
    }

    public int getPassword() {
        return password;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public boolean logIn(Organizer credentials){
        String query = "SELECT * FROM ORGANIZER";
        try {
            con = SetConnection.getConnection();
            statement = con.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String u = resultSet.getString("username");
                int p = resultSet.getInt("org_password");
                if (u.equalsIgnoreCase(credentials.getOrgName()) && p == credentials.getPassword())
                    return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void organizerMenu() {
        System.out.println("\n\t\t\t\tORGANIZER MENU\n" +
                "[1] Students\n" +
                "[2] Players\n" +
                "[3] Coaches\n" +
                "[4] Sports\n" +
                "[5] First Game Assessment Schedule\n" +
                "[6] Exit\n" +
                "Select the number of table: ");
        String t = keyboard.nextLine();
        switchTables(t);
    }
    public void switchTables(String table){
        switch (table){
            case "1":
                studentSubmenu();
                break;
            case "2":
                playerSubmenu();
                break;
            case "3":
                coachSubmenu();
                break;
            case "4":
                sportsSubmenu();
                break;
            case "5":
                try {
                    firstGameSubmenu();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "6":
                System.out.println("Thank you for using the app.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input.");
                organizerMenu();
                break;
        }
    }

    public void studentSubmenu(){
        System.out.println("\t\tStudent Table\n" +
                "[1] Register Student\n" +
                "[2] Show List of Applicant Students\n" +
                "[3] Update Student Data\n" +
                "[4] Search a Student\n" +
                "[5] Remove Applicant Student\n" +
                "[6] Back\n" +
                "Enter the number of option: ");
        studentCRUD(keyboard.nextLine());
    }
    public void studentCRUD(String option){
        Student student = new Student();
        switch (option){
            case "1":
                System.out.println("REGISTER STUDENT");
                System.out.print("Enter Student ID: ");
                student.setID(Integer.parseInt(keyboard.nextLine()));
                System.out.print("Enter First name: ");
                student.setFirstName(keyboard.nextLine());
                System.out.print("Enter Last name: ");
                student.setLastName(keyboard.nextLine());
                System.out.print("Enter Gender: ");
                student.setGender(keyboard.nextLine());
                System.out.print("Enter Course: ");
                student.setCourse(keyboard.nextLine());
                System.out.print("Enter Year: ");
                student.setCourseYear(Integer.parseInt(keyboard.nextLine()));
                student.registerStudent(student);
                organizerMenu();
                break;
            case "2":
                System.out.println("LIST OF APPLICANT STUDENTS");
                try {
                    student.getStudentsList();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                organizerMenu();
                break;
            case "3":
                System.out.println("UPDATE STUDENT DATA");
                System.out.print("Enter old ID number: ");
                student.setID(Integer.parseInt(keyboard.nextLine()));
                System.out.print("Enter first name: ");
                student.setFirstName(keyboard.nextLine());
                System.out.print("Enter last name: ");
                student.setLastName(keyboard.nextLine());
                System.out.print("Enter gender: ");
                student.setGender(keyboard.nextLine());
                System.out.print("Enter course: ");
                student.setCourse(keyboard.nextLine());
                System.out.print("Enter Year: ");
                student.setCourseYear(Integer.parseInt(keyboard.nextLine()));
                System.out.print("Enter new ID number: ");
                String newID = keyboard.nextLine();
                student.updateStudentData(student, Integer.parseInt(newID));
                organizerMenu();
                break;
            case "4":
                System.out.println("SEARCH A STUDENT");
                System.out.print("Enter Student ID number to search: ");
                System.out.println(student.searchStudent(Integer.parseInt(keyboard.nextLine())));
                organizerMenu();
                break;
            case "5":
                System.out.println("REMOVE APPLICANT STUDENT");
                System.out.print("Enter the ID Number of student to remove: ");
                student.removeRegistration(Integer.parseInt(keyboard.nextLine()));
                organizerMenu();
                break;
            case "6":
                organizerMenu();
            break;
            default:
                System.out.println("Invalid input.");
                organizerMenu();
                break;
        }
    }

    public void playerSubmenu(){
        System.out.println("\t\tPlayer Table\n" +
                "[1] Accept Student\n" +
                "[2] Show List of Players\n" +
                "[3] Update Player Data\n" +
                "[4] Search a Player\n" +
                "[5] Remove a Player\n" +
                "[6] Back\n" +
                "Enter number of option: ");
        playerCRUD(keyboard.nextLine());
    }

    public void playerCRUD(String option){
        Player player = new Player();
        switch (option){
            case "1":
                System.out.println("ACCEPT STUDENT FOR TRYOUT");
                System.out.print("Enter Student ID: ");
                player.setStudentID(Integer.parseInt(keyboard.nextLine()));
                player.setPlayerID(player.generatePlayerID());
                System.out.print("Enter Sports ID number: ");
                player.setSportID(keyboard.nextLine());
                player.acceptPlayers(player);
                organizerMenu();
                break;
            case "2":
                System.out.println("LIST OF PLAYERS");
                player.showListPlayers();
                organizerMenu();
                break;
            case "3":
                System.out.println("UPDATE PLAYER DATA");
                System.out.print("Enter player ID number to update: ");
                player.setPlayerID(keyboard.nextLine());
                System.out.print("Enter new sport ID: ");
                player.setSportID(keyboard.nextLine());
                player.updatePlayerData(player);
                organizerMenu();
                break;
            case "4":
                System.out.println("SEARCH PLAYER");
                System.out.print("Enter the player ID to search: ");
                System.out.println(player.searchPlayer(keyboard.nextLine()));
                organizerMenu();
                break;
            case "5":
                System.out.println("REMOVE PLAYER");
                System.out.print("Enter player number to remove: ");
                player.removePlayer(keyboard.nextLine());
                organizerMenu();
            case "6":
                organizerMenu();
                break;
            default:
                System.out.println("Invalid input.");
                organizerMenu();
                break;
        }

    }

    public void firstGameSubmenu() throws ParseException {
        System.out.println("\t\tFirst Game Assessment Table\n" +
                "[1] Add First Game Assessment Schedule\n" +
                "[2] Show List of First Game Assessment Schedule\n" +
                "[3] Update First Game Assessment Schedule\n" +
                "[4] Remove a First Game Assessment Schedule\n" +
                "[5] Back\n" +
                "Enter the number of option: ");
        firstGameCRUD(keyboard.nextLine());
    }

    public void firstGameCRUD(String option) throws ParseException {
        Player p = new Player();
        GamesFirstAssessment firstAssessment = new GamesFirstAssessment();
        switch (option){
            case "1":
                System.out.println("ADD FIRST GAME ASSESSMENT SCHEDULE");
                System.out.print("Enter Player Number: ");
                String pNum = keyboard.nextLine();
                if(firstAssessment.firstGameExists(firstAssessment.getPlayerNum()) == true || p.playerExists(pNum) == true){
                    firstAssessment.setPlayerNum(pNum);
                    firstAssessment.setGameID(firstAssessment.generateGameID(pNum));
                    System.out.print("Enter Date (mm/dd/yyyy): ");
                    String d = keyboard.nextLine();
                    if(firstAssessment.isValidDate(d) == true){
                        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                        Date date = format.parse(d);
                        firstAssessment.setGameDate(date);
                        System.out.print("Enter Game Result (win/lose/pend): ");
                        firstAssessment.setResult(keyboard.nextLine());
                        firstAssessment.addGamesFirstAssessment(firstAssessment);
                        organizerMenu();
                    }
                    else{
                        System.out.println("Invalid date format.");
                        organizerMenu();
                    }
                }
                else{
                    System.out.println("Player number " + pNum + " does not exists.");
                    organizerMenu();
                }
                organizerMenu();
                break;
            case "2":
                System.out.println("SHOW LIST OF FIRST GAME ASSESSMENT SCHEDULE");
                firstAssessment.firstGameAssessmentList();
                organizerMenu();
                break;
            case "3":
                System.out.println("UPDATE FIRST GAME ASSESSMENT SCHEDULE");
                System.out.println("\t[1] Update Date\n\t[2] Update Result");
                System.out.println("Enter number of option: ");
                String res = keyboard.nextLine();
                switch (res){
                    case "1":
                        System.out.println("\t\tUpdate Game Schedule Date");
                        System.out.print("Enter the Player Number: ");
                        firstAssessment.setPlayerNum(keyboard.nextLine());
                        System.out.print("Enter Date (mm/dd/yyyy): ");
                        String dt= keyboard.nextLine();
                        if(firstAssessment.isValidDate(dt) == true){
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                            Date date = format.parse(dt);
                            firstAssessment.setGameDate(date);
                            firstAssessment.updateFirstGameDate(firstAssessment);
                            organizerMenu();
                        }
                        else{
                            System.out.println("Invalid date format.");
                        }
                        break;
                    case "2":
                        System.out.println("\t\tUpdate Game Remark");
                        System.out.print("Enter the Player Number: ");
                        firstAssessment.setPlayerNum(keyboard.nextLine());
                        System.out.print("Enter Game Result (win/lose/pend): ");
                        firstAssessment.setResult(keyboard.nextLine());
                        firstAssessment.updateFirstGameRemark(firstAssessment);
                        organizerMenu();
                        break;
                    default:
                        System.out.println("Invalid input.");
                        organizerMenu();
                        break;
                }

                organizerMenu();
                break;
            case "4":
                System.out.println("REMOVE A FIRST GAME ASSESSMENT SCHEDULE");
                System.out.print("Enter Player Number to remove Schedule: ");
                firstAssessment.setPlayerNum(keyboard.nextLine());
                firstAssessment.removeFirstGameSchedule(firstAssessment);
                organizerMenu();
                break;
            case "5":
                organizerMenu();
                break;
            default:
                System.out.println("Invalid input.");
                organizerMenu();
        }
    }

    public void coachSubmenu(){
        System.out.println("\t\tCoach Table\n" +
                "[1] Add Coach\n" +
                "[2] Show List of Coaches\n" +
                "[3] Update Coach Data\n" +
                "[4] Search Coach\n" +
                "[5] Remove a Coach\n" +
                "[6] Back\n" +
                "Enter the number of option: ");
        coachCRUD(keyboard.nextLine());
    }

    public void coachCRUD(String option){
        Coach coach = new Coach();
        switch (option){
            case "1":
                System.out.println("ADD COACH");
                coach.setCoachID(coach.generateCoachID());
                System.out.print("Enter first name: ");
                coach.setFirstName(keyboard.nextLine());
                System.out.print("Enter last name: ");
                coach.setLastName(keyboard.nextLine());
                System.out.print("Enter sports ID: ");
                coach.setSportID(keyboard.nextLine());
                coach.setCoachID(coach.generateCoachID());
                coach.addCoach(coach);
                organizerMenu();
                break;
            case "2":
                System.out.println("LIST OF COACHES");
                coach.showCoachList();
                organizerMenu();
                break;
            case "3":
                System.out.println("UPDATE COACH DATA");
                System.out.print("Enter coach ID number to update: ");
                coach.setCoachID(keyboard.nextLine());
                System.out.print("Enter new first name: ");
                coach.setFirstName(keyboard.nextLine());
                System.out.print("Enter new last name: ");
                coach.setLastName(keyboard.nextLine());
                System.out.print("Enter sports ID number: ");
                coach.setSportID(keyboard.nextLine());
                coach.updateCoach(coach);
                organizerMenu();
                break;
            case "4":
                System.out.println("SEARCH COACH");
                System.out.print("Enter the coach ID number to search: ");
                System.out.println(coach.searchCoach(Integer.parseInt(keyboard.nextLine())));
                organizerMenu();
                break;
            case "5":
                System.out.println("REMOVE COACH");
                System.out.print("Enter the coach ID to remove: ");
                coach.removeCoach(keyboard.nextLine());
                organizerMenu();
                break;
            case "6":
                organizerMenu();
                break;
            default:
                System.out.println("Invalid input.");
                organizerMenu();
        }
    }

    public void sportsSubmenu(){
        System.out.println("\t\tSports Table\n" +
                "[1] Add Sports\n" +
                "[2] Show List of Sports\n" +
                "[3] Search Sports\n" +
                "[4] Remove a Sports\n" +
                "[5] Back\n" +
                "Enter the number of option: ");
        sportsCRUD(keyboard.nextLine());
    }

    public void sportsCRUD(String option) {
        Sports sports = new Sports();
        switch (option) {
            case "1":
                System.out.println("ADD SPORTS");
                System.out.print("Enter Sports Description: ");
                sports.setSportsDesc(keyboard.nextLine());
                System.out.print("Enter Sports Category: ");
                sports.setSportsCat(keyboard.nextLine());
                System.out.print("Enter Sports Type: ");
                sports.setSportType(keyboard.nextLine());
                System.out.print("Enter the Sports Venue: ");
                sports.setVenue(keyboard.nextLine());
                sports.setSportsID(sports.generateSportsID(sports));
                sports.addSports(sports);
                organizerMenu();
                break;
            case "2":
                System.out.println("SHOW LIST OF SPORTS");
                try {
                    sports.getSportsList();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                organizerMenu();
                break;
            case "3":
                System.out.println("SEARCH SPORTS");
                System.out.print("Enter the name of the sports to search: ");
                sports.searchSports(keyboard.nextLine());
                organizerMenu();
                break;
            case "4":
                System.out.println("REMOVE A SPORTS");
                System.out.print("Enter the sports ID to remove: ");
                sports.removeSports(keyboard.nextLine());
                organizerMenu();
                break;
            case "5":
                organizerMenu();
                break;
            default:
                System.out.println("Invalid input.");
        }
    }
}
