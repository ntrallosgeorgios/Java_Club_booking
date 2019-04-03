import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserInterface
{

    private Club sportsClub;
    private Scanner sc = new Scanner(System.in);
   
    public UserInterface(Club sportsClub)
    {
        this.sportsClub = sportsClub;
    }

    public void run()
    {
        while(true)
            switch (menu() )
            {
                case 1:
                    showAvailableCourts();
                    break;
                case 2:
                    makeBooking();
                    break;
                case 3:
                    showMemberBookings();
                    break;
                case 4:
                    showCourtBookings();
                    break;
                case 5:
                    deleteBooking();
                    break;
                case 6:
                	exitMessage();
                    return;
                default:
                    System.out.println ( "Invalid option" );
                    break;
            }
    }

    private int menu()
    {
        System.out.println("|-------------------------------------------------|");
        System.out.println("| 1 - Show Available Courts                       |");
        System.out.println("| 2 - Make Booking for Member                     |");
        System.out.println("| 3 - Show Member Bookings                        |");
        System.out.println("| 4 - Show Court Bookings                         |");
        System.out.println("| 5 - Delete Booking                              |");
        System.out.println("| 6 - Exit                                        |" );
        System.out.println("|-------------------------------------------------|");
        System.out.println("Select your option (enter a selection number): ");
        int option = sc.nextInt();
        sc.nextLine();
        return  option;
    }


    /**
     * return the available courts 
     */
    private void showAvailableCourts()
    {
    	sportsClub.availabCout();
    }

    /**
     * is the method for make the booking
     * for checking the dates I see them in the
     * following links.
     * @see https://www.tutorialspoint.com/javatime/javatime_localdate_plusdays.htm
     * @see https://beginnersbook.com/2017/11/java-8-adding-days-to-the-localdate/
     * @see https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html
     * @see http://www.java2s.com/Tutorials/Java_Date_Time/java.time/LocalTime/LocalTime_getHour_example.htm
     * @see https://stackoverflow.com/questions/3904579/how-to-capitalize-the-first-letter-of-a-string-in-java
     */
    private void makeBooking(){

        System.out.println("Give the booking informations");

        //Get the memID
        int id ;
        // Check if the input is integer
        while(true) {
            try {
                System.out.print("Give the Member ID : ");
                int idte = Integer.parseInt(sc.nextLine());

                if(!sportsClub.findMember(idte) ){
                    System.out.println("We don't have souch a member");
                } else if (!sportsClub.isFinan(idte)) {
                    System.out.println("The member is not financial");
                } else if(idte == 0) {
                	System.out.println("The member Id can't be 0");
                } else {
                    id = idte;
                    break;
                }

            } catch(Exception ex) {
                System.out.println("The Member ID should be integer");
            }
        }


        //Get the Sport
        String sport = "";
        while(true) {
            System.out.print("Enter the sport that you want to book : ");
            String sportTe = sc.nextLine();
            String spoT = sportTe.trim();
            
            // Capitalize the first letter of the input and add the other part of the string
            String ch = spoT.substring(0, 1).toUpperCase() + spoT.substring(1).toLowerCase();
            
            // Check if the sport exist in the list
            if(sportsClub.findSport(ch)) {
            	 sport = ch;
                 break;
            } else {
            	System.out.println("The Sport is not exist in the list");
            }
            
        }

        // Check if the input is integer
        //Get the court numb
        int courtN;
        while(true) {
        	try {
                System.out.print("Give the court number for booking : ");
                int courtEn =  Integer.parseInt(sc.nextLine());
                
                if(!sportsClub.courtInSport(sport , courtEn) ){
                	System.out.println( sport + " don't have court with number: " + courtEn );
                } else if(courtEn == 0) {
                	System.out.println("The court number can't be 0");
                } else {
                	 courtN = courtEn;
                     break;
                }

            } catch(Exception ex) {
                System.out.println("The court number should be integer");
            }
        }

        // Take input the date as string and check it.
        // depending the situation printing and the appropriate message
        String dayBo = "";
        while(true) {
            System.out.print("Enter the date that you want to book the format dd/mm/yyyy: ");
            String dateCh = sc.nextLine();
            LocalDate tesD= DateUtility.convertDate(dateCh);

            if( tesD == null ) {
                System.out.println("You must put an entry for the date!!");
            } else if(tesD.isAfter(LocalDate.now().plusDays(7))) {
                System.out.println("You can't book only seven days in advance!");
            } else if(tesD.isBefore(LocalDate.now())){
                System.out.println("You can't book days before the today date");
            }else {
                dayBo = dateCh;
                break;
            }

        }

        //Get amount of time
        int amTi ;
       
        // Check if the input is integer
        while(true) {
            try {
                System.out.print("Enter the amount of time that you want to book for: ");
                int timAm =  Integer.parseInt(sc.nextLine());

                if(sport.equals("Basketball") && timAm > 3){
                    System.out.println("You can book Badminton court for only 3 hours !");
                } else if (sport.equals("Badminton") && timAm > 2) {
                    System.out.println("You can book Badminton court for only 2 hours !");
                } else if(timAm == 0){
                	System.out.println("The amount of time can't be 0");
                }else {
                	amTi = timAm;
                    break;
                }

            } catch(Exception ex) {
                System.out.println("The amount of time should be integer");
            }
        }
        
        // Take input the time as string and check it.
        // depending the situation printing and the appropriate message
        String startTi = "";
        while(true) {
            System.out.print("Enter the hour in 24 Hour foramt that you want to bookit: ");
            String tiGet = sc.next();
            LocalTime timeS = DateUtility.convertTime(tiGet);
            LocalTime befNin = DateUtility.convertTime("8:59");
            LocalTime after12 = DateUtility.convertTime("22:01");
          
            if (timeS == null) {
            	System.out.println("You must put an entry for the date!!");
            } else if(timeS.isAfter(befNin)  && timeS.isBefore(after12)){
            	startTi = tiGet;
                break;
            } else {
            	System.out.println("You can book only Beteen 9am and 10pm");
            }

        }

        LocalDate inpDate = DateUtility.convertDate(dayBo);
        LocalTime inpTim = DateUtility.convertTime(startTi);

        // We add the sport and a the same time we check if added
        if (sportsClub.registerMembSpoCou(id , sport, courtN, amTi, inpDate, inpTim)) {
            System.out.println("The booking added successfully!!");
        }

    }

    /**
     * Show the members bookings.
     */
    public void showMemberBookings() {
    	
        //Get the memID
        int id ;
        // Check if the input is integer
        while(true) {
            try {
                System.out.print("Give the Member ID : ");
                int idte = Integer.parseInt(sc.nextLine());

                if(!sportsClub.findMember(idte) ){
                    System.out.println("We don't have souch a member");
                } else if (!sportsClub.isFinan(idte)) {
                    System.out.println("The member is not financial");
                } else if(idte == 0) {
                	System.out.println("The member Id can't be 0");
                } else {
                    id = idte;
                    break;
                }

            } catch(Exception ex) {
                System.out.println("The Member ID should be integer");
            }
        }
        
        ArrayList<Booking> book = sportsClub.memBook(id);
        
        System.out.printf("%7s %12s %9s %9s %7s %5s", "BookingID", "Date Booked" , "Time Start", "Time finish", "Member", "Court ID" );
        System.out.println();
        
        for(Booking memBook : book) {
        	System.out.println(memBook.toString());
        }

    }

    /**
     * show the courts that are booking
     */
    private void showCourtBookings(){
    	sportsClub.retBookCour();
    }


    /**
     * Delete the booking
     */
    private void deleteBooking(){
    	
        //Get the memID
        int id ;
        // Check if the input is integer
        while(true) {
            try {
                System.out.print("Give the Member ID : ");
                int idte = Integer.parseInt(sc.nextLine());

                if(!sportsClub.findMember(idte) ){
                    System.out.println("We don't have souch a member");
                } else if (!sportsClub.isFinan(idte)) {
                    System.out.println("The member is not financial");
                } else if(idte == 0) {
                	System.out.println("The member Id can't be 0");
                } else {
                    id = idte;
                    break;
                }

            } catch(Exception ex) {
                System.out.println("The Member ID should be integer");
            }
        }
        
        // Take input the date as string and check it.
        // depending the situation printing and the appropriate message
        String dayBo = "";
        while(true) {
            System.out.print("Enter the date that you want to book the format dd/mm/yyyy: ");
            String dateCh = sc.next();
            LocalDate tesD= DateUtility.convertDate(dateCh);

            if( tesD == null ) {
                System.out.println("You must put an entry for the date!!");
            } else if(tesD.isAfter(LocalDate.now().plusDays(7))) {
                System.out.println("You can't book only seven days in advance!");
            } else if(tesD.isBefore(LocalDate.now())){
                System.out.println("You can't book days before the today date");
            } else {
                dayBo = dateCh;
                break;
            }

        }
        
        // Take input the time as string and check it.
        // depending the situation printing and the appropriate message
        String startTi = "";
        while(true) {
            System.out.print("Enter the hour in 24 Hour foramt that you want to bookit: ");
            String tiGet = sc.next();
            LocalTime timeS = DateUtility.convertTime(tiGet);
            LocalTime befNin = DateUtility.convertTime("8:59");
            LocalTime after12 = DateUtility.convertTime("22:01");
          
            if (timeS == null) {
            	System.out.println("You must put an entry for the date!!");
            } else if(timeS.isAfter(befNin)  && timeS.isBefore(after12)){
            	startTi = tiGet;
                break;
            } else {
            	System.out.println("You can book only Beteen 9am and 10pm");
            }

        }
        
    	if(sportsClub.deleBook(id, dayBo, startTi)) {
    		System.out.println("The booking of memeber deleted");
    	} else {
    		System.out.println("The booking doesn't exist");
    	}
    	
    }
     
    /**
     * write to file the bookings
     */
    private void writeFileBook() {
    	sportsClub.writeDataToFile();
    }
    
    private void exitMessage() {
        writeFileBook();
    	System.out.println("Have an awesome day!!");
    }
    

} // end class