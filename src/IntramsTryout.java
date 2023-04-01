import objects.org.Organizer;

import java.util.Scanner;

public class IntramsTryout {
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\t\t\t\tWELCOME TO SAMCIS INTRAMS TRYOUT!\n");
        System.out.println("Login as:\n" +
                "[1] Organizer\n" +
                "[2] Coach\n" +
                "[3] Student\n" +
                "[4] Exit\n" +
                "Enter number of choice: ");
        loginAs(keyboard.nextLine());
    }

    public static void loginAs(String choice){
        Scanner keyboard = new Scanner(System.in);
        switch (choice){
            case "1":
                System.out.println("\t\tLOGIN AS ORGANIZER");
                Organizer o = new Organizer();
                System.out.print("Enter username: ");
                String u = keyboard.nextLine();
                System.out.print("Enter password: ");
                String p = keyboard.nextLine();
                if(o.logIn(u, p) == true){
                    System.out.println("\tSuccessfully logged in as ORGANIZER!\n");
                    o.organizerMenu();
                }
                else {
                    System.out.println("Invalid username or password.");
                }
                break;
            case "2":
                //Log in for coach
                break;
            case "3":
                //For student
                break;

        }

    }
}
