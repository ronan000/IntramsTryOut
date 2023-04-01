package objects.org;

import objects.Coach;
import objects.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

public class Organizer {
    private String orgName, password;
    private Scanner keyboard = new Scanner(System.in);

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

    public boolean logIn(String orgName, String password){
        String name, pass;
        try {
            name = Files.readAllLines(Paths.get("src/res/data/orgcredentials.txt")).get(0);
            pass = Files.readAllLines(Paths.get("src/res/data/orgcredentials.txt")).get(1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return orgName.equals(name) && password.equals(pass);
    }

    public void organizerMenu() {
        System.out.println("\n\t\t\t\tORGANIZER MENU\n" +
                "[1] Students\n" +
                "[2] Players\n" +
                "[3] Teams\n" +
                "[4] Coaches\n" +
                "[5] Sports\n" +
                "[6] Game Schedule\n" +
                "[7] Exit\n" +
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
                teamSubmenu();
                break;
            case "4":
                coachSubmenu();
                break;
            case "5":
                sportsSubmenu();
                break;
            case "6":
                try {
                    GameSchedule gameSchedule = new GameSchedule();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "7":
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
                "[3] Remove Applicant Student\n" +
                "[4] Back\n" +
                "Enter the number of option: ");
        studentCRUD(keyboard.nextLine());
    }
    public void studentCRUD(String option){
        Student student = new Student();
        switch (option){
            case "1":
                System.out.println("REGISTER STUDENT");
                System.out.print("Enter Student ID: ");
                student.setID(Integer.parseInt(String.valueOf(keyboard.nextLine())));
                System.out.print("Enter First name: ");
                student.setFirstName(keyboard.nextLine());
                System.out.print("Enter Last name: ");
                student.setLastName(keyboard.nextLine());
                System.out.print("Enter Gender: ");
                student.setGender(keyboard.nextLine());
                System.out.print("Enter Course: ");
                student.setCourse(keyboard.nextLine());
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
                System.out.println("REMOVE APPLICANT STUDENT");
                System.out.print("Enter the ID Number of student to remove: ");
                student.removeRegistration(Integer.parseInt(keyboard.nextLine()));
                organizerMenu();
                break;
            case "4":
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
                "[3] Search a Player\n" +
                "[4] Remove a Player\n" +
                "[5] Back\n" +
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
                System.out.println("Enter Sports ID number: ");
                player.setSportID(Integer.parseInt(keyboard.nextLine()));
                System.out.print("Enter Coach ID: ");
                player.setCoachID(Integer.parseInt(keyboard.nextLine()));
                System.out.print("Enter Team ID: ");
                player.setTeamID(Integer.parseInt(keyboard.nextLine()));
                player.acceptPlayers(player);
                organizerMenu();
                break;
            case "2":
                System.out.println("LIST OF PLAYERS");
                player.showPlayerList();
                organizerMenu();
                break;
            case "3":
                System.out.println("SEARCH PLAYER");
                System.out.print("Enter the player ID to search: ");
                System.out.println(player.searchPlayer(keyboard.nextLine()));
                organizerMenu();
                break;
            case "4":
                System.out.println("REMOVE PLAYER");
                System.out.println("Enter player number to remove: ");
                player.removePlayer(keyboard.nextLine());
                organizerMenu();
            case "5":
                organizerMenu();
                break;
            default:
                System.out.println("Invalid input.");
                organizerMenu();
                break;
        }

    }

    public void teamSubmenu(){
        System.out.println("\t\tTeams Table\n" +
                "[1] Create Team\n" +
                "[2] Show List of Teams\n" +
                "[3] Search Team\n" +
                "[4] Remove a Team\n" +
                "[5] Back\n" +
                "Enter the number of option: ");
        teamCRUD(keyboard.nextLine());
    }

    public void teamCRUD(String option){
        Team team = new Team();
        switch (option){
            case "1":
                System.out.println("ADD TEAM");
                team.setTeamID(team.generateTeamID());
                System.out.print("Enter team name: ");
                team.setTeamName(keyboard.nextLine());
                team.addTeam(team);
                organizerMenu();
                break;
            case "2":
                System.out.println("SHOW LIST OF TEAMS");
                team.showTeamList();
                organizerMenu();
                break;
            case "3":
                System.out.println("SEARCH TEAM");
                System.out.print("Search team name: ");
                System.out.println(team.searchTeam(keyboard.nextLine()));
                organizerMenu();
                break;
            case "4":
                System.out.println("REMOVE A TEAM");
                System.out.print("Enter the team name to remove: ");
                team.removeTeam(keyboard.nextLine());
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
                "[3] Remove a Coach\n" +
                "[4] Back\n" +
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
                coach.addCoach(coach);
                organizerMenu();
                break;
            case "2":
                System.out.println("LIST OF COACHES");
                try {
                    coach.getCoachList();
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
                System.out.println("REMOVE A COACH");
                System.out.print("Enter coach ID number to remove: ");
                coach.removeCoach(Integer.parseInt(keyboard.nextLine()));
                organizerMenu();
                break;
            case "4":
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
                "[3] Remove a Sports\n" +
                "[4] Back\n" +
                "Enter the number of option: ");
        sportsCRUD(keyboard.nextLine());
    }

    public void sportsCRUD(String option){
        Sports sports = new Sports();
        switch (option){
            case "1":
                System.out.println("ADD SPORTS");
                sports.setSportsID(sports.generateSportsID());
                System.out.print("Enter Sports Description: ");
                sports.setSportsDesc(keyboard.nextLine());
                System.out.print("Enter Sports Category: ");
                sports.setSportsCat(keyboard.nextLine());
                System.out.print("Enter Sports Type: ");
                sports.setSportType(keyboard.nextLine());
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
                System.out.println("REMOVE A SPORTS");
                System.out.print("Enter the sports ID to remove: ");
                sports.removeSports(Integer.parseInt(keyboard.nextLine()));
                organizerMenu();
                break;
            case "4":
                organizerMenu();
                break;
            default:
                System.out.println("Invalid input.");
                organizerMenu();
        }

    }
}
