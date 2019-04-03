/**
 * Write a description of class Start here.
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */

import java.util.Scanner;

public class Start
{

    public static void main(String[] args)
    {
        try{
            Club sportsClub = new Club("Sports Club");
            while(true)
                switch (menu() )
                {
                    case 1:
                        UserInterface consoleApp = new UserInterface(sportsClub);
                        consoleApp.run();
                        break;
                    case 2:
                        return;
                           
                    default:
                        System.out.println ( "Unrecognized option" );
                        break;
                }

        } catch (Exception ex) {
            System.out.println(ex);
        }



    }

    /**
     * Main menu of the start Class
     * @return the number of choice
     */
    public static int  menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to use console or GUI?");
        System.out.println("1) console");
        System.out.println("2) Exit");
        int option = sc.nextInt();
        sc.nextLine();
        return  option;
    }

}
