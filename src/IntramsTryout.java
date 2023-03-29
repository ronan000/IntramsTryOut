import objects.org.Organizer;

import java.util.Scanner;

public class IntramsTryout {
    public static void main(String[] args) {
        menu();
        getChoice();
    }
    public static void menu(){
        System.out.println("\t\t\t\tWELCOME TO SAMCIS INTRAMS TRYOUT!\n");
        System.out.println("Login as:\n" +
                "[1] Organizer\n" +
                "[2] Coach\n" +
                "[3] Student");
    }

    public static void getChoice(){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter number: ");
        int l = keyboard.nextInt();
        switch (l){
            case 1:
                Organizer organizer = new Organizer();
                organizer.organizerMenu();
                //showing of sports list pa lang ang functional
                //organizer.listOfSports();
                break;
            case 2:
                //For coach
                break;
            case 3:
                //For student
                break;
        }
    }

}
