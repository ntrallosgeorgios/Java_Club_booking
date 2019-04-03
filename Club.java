import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;


public class Club {
    private String name;
    private ArrayList<Member> memberList;
    private ArrayList<Sport> sportList;
    private int bookId = 1;
    private Member membGUI;

    /**
     * Constructor of the object club
     * @param name the name of the club
     */
    public Club(String name){

        try {
            this.name = name;
            memberList = new ArrayList<Member>();
            getMembersFromFile();
            sportList = new ArrayList<Sport>();
            getSportsFromFile();
            getBookingsFromFile();

        } catch(Exception e) {
            System.out.println(e);
        }

    }

    /**
     *
     * @return the name of the club
     */
    public String getNameCl() {
        return this.name;
    }

    /**
     *
     * @param name set the bane of the club
     */
    public void setNameCl(String name) {
        this.name = name;
    }


    /**
     * Find the member in the list
     * @param memid get the member Id
     * @return
     */
    public boolean findMember(int memid) {
        boolean mem = false;

        for(int i = 0; i < memberList.size(); i++) {

            if (memberList.get(i).getMemId() == memid) {
                mem = true;
            }
        }

        return mem;
    }

    /**
     * Find the member in the list
     * @param memid get the member Id
     * @return the Member
     */
    public Member guifindMember(int memid) {



        for(int i = 0; i < memberList.size(); i++) {

            if (memberList.get(i).getMemId() == memid) {
                membGUI = memberList.get(i);
            }
        }

        return membGUI;
    }

    /**
     *
     * @param memid member id
     * @return if is financial or not
     */
    public boolean isFinan(int memid) {

        boolean re = false;

        for(int i = 0; i < memberList.size(); i++) {

            // check if the member is finan
            if (memberList.get(i).getMemId() == memid && memberList.get(i).getFinan()) {
                re = true;
            }
        }

        return re;
    }


    /**
     * Find if the sport exist in the list
     * @param name get the name of the sport
     * @return the sport if exist otherwise return null
     */
    public boolean findSport(String name) {

        boolean fiSpo = false;

        for(Sport chSp : sportList) {
            if(chSp.getName().equals(name)) {
                return true;
            }
        }

        return fiSpo;
    }

    /**
     * Check if the court is in the sport for booking
     * @param sportck
     * @param court
     * @return true or false depending if the court in in the sport
     */
    public boolean courtInSport(String sportck, int court) {

        boolean courtEx = false;
    
        
        for(Sport chSp : sportList) {
            if(chSp.getName().equals(sportck)) {
                ArrayList<Court> cou = chSp.getCourtList();
                for(Court c : cou ) {
                    if(c.getCourtId() == court) {
                        courtEx = true;
                    }
                }
            }
        }
        
        return courtEx;

    }


    /**
     * Book the member to the court
     * @param memId member id
     * @param sportBo sport that he wants to play
     * @param courtN court number
     * @param amTi the amount of time that he want to play
     * @param dayB the day that he wants to play
     * @param starTi the starting time
     * @return true or false depending if the booking happens
     */
    public boolean registerMembSpoCou(int memId, String sportBo, int courtN, int amTi, LocalDate dayB, LocalTime starTi) {

        boolean regU = false;
        Member entMe = null;
        Court booCo = null;

        // Get the Member Object
        for(int i =0 ; i < memberList.size(); i++ ) {

            // Find the member from the list
            if(memberList.get(i).getMemId() == memId) {
                entMe = memberList.get(i);
            }

        }

        // Get court object
        for(int i = 0; i < sportList.size(); i++) {
            if (sportList.get(i).getName().equals(sportBo)) {
                ArrayList<Court>spCou = sportList.get(i).courList;
                for(int j = 0; j < spCou.size(); j++) {
                    if(spCou.get(j).getCourtId() == courtN) {
                        booCo = spCou.get(j);
                    }
                }
            }
        }

        Booking addBook = new Booking(bookId, dayB, starTi, starTi.plusHours(amTi), entMe , booCo);
        
        // add the booking to the court booking list
        for(int i = 0; i < sportList.size(); i++) {
            if (sportList.get(i).getName().equals(sportBo)) {
                ArrayList<Court>spCou = sportList.get(i).courList;
                for(int j = 0; j < spCou.size(); j++) {
                    if(spCou.get(j).getCourtId() == courtN) {
                        bookId++;
                        return spCou.get(j).addCourtBooking(addBook);
                    }
                }
            }
        }

        return regU;
    }

    /**
     * Print the available courts
     */
    public void availabCout() {
        System.out.println("The free courts depending the Sport!!");
        
        for(Sport m : retSpo()) {
            System.out.print(m.getName() + " : ");
            ArrayList<Court> ar = m.courList;
            for(Court cou: ar) {
                if(cou.getListOfBook().isEmpty()){
                    System.out.print(String.valueOf(cou.getCourtId()) + " ");
                }
                
            }
            System.out.println();
        }
    }

    /**
     *
     * @return the array list of available courts of basketball
     */
    public ArrayList<Court> guiAvailCourt() {

        ArrayList<Court> tempCo = new ArrayList<Court>();
        for (Sport m : retSpo()) {

                ArrayList<Court> ar = m.courList;
                for (Court cou : ar) {
                    if (cou.getListOfBook().isEmpty()) {
                        tempCo.add(cou);
                    }

                }


        }
        return  tempCo;
    }

    /**
     * 
     * @return the array list of sports
     */
    public ArrayList<Sport> retSpo() {
        return this.sportList;
    }
    
    /**
     * Create a list of the bookings of one member
     * @param memId take the ID of the member
     * @return the booking list
     */
    public ArrayList<Booking> memBook(int memId) {

        ArrayList<Booking> rmem = new ArrayList<Booking>();


        for(Sport chSp : sportList) {
            ArrayList<Court> chCo= chSp.getCourtList();
            for(Court couC : chCo) {
                ArrayList<Booking> chBoik= couC.getListOfBook();
                for(int i =0; i < chBoik.size(); i++) {
                    if(chBoik.get(i).getBookBy().getMemId() == memId) {
                        rmem.add(chBoik.get(i));
                    }
                }
            }
        }

        Collections.sort(rmem);
        return rmem;
    }

    /**
     * Create a list of the bookings
     * @param memId take the ID of the member
     * @return the booking list
     */
    public ArrayList<Booking> guimembersBook() {

        ArrayList<Booking> rmem = new ArrayList<Booking>();


        for(Sport chSp : sportList) {
            ArrayList<Court> chCo= chSp.getCourtList();
            for(Court couC : chCo) {
                ArrayList<Booking> chBoik= couC.getListOfBook();
                for(int i =0; i < chBoik.size(); i++) {
                        rmem.add(chBoik.get(i));
                }
            }
        }

        Collections.sort(rmem);
        return rmem;
    }
    
    /**
     * delete the member booking
     * @param memId member id
     * @param dBook the date of booking booking 
     * @param tBook the time of booking
     * @return true or false depending if the bookind deleted
     */
    public boolean deleBook(int memId, String dBook, String tBook) {
        
        boolean regU = false;   
        int booCo;

        //find the booking number
        for(Sport chSp : sportList) {
            ArrayList<Court> chCo= chSp.getCourtList();
            for(Court couC : chCo) {
                ArrayList<Booking> chBoik= couC.getListOfBook();
                for(int i =0; i < chBoik.size(); i++) {
                    String dCh = DateUtility.dateToString(chBoik.get(i).getBookingDate());
                    String tCh = DateUtility.timeToString(chBoik.get(i).getStartTi());
                    
                    if(chBoik.get(i).getBookBy().getMemId() == memId 
                            && dCh.equals(dBook) 
                            &&  tCh.equals(tBook) ) {
                        booCo = chBoik.get(i).getBookingId();
                        regU = couC.removeCourtBooking(booCo);
                    }
                }
                
            }
        }
        
        return regU;
    }
    
    /**
     * 
     * @return the booking courts
     */
    public void retBookCour(){
        
        for(Sport s : sportList) {
            ArrayList<Court> cour = s.courList;
            for(Court c : cour) {
                if(!c.getListOfBook().isEmpty()) {
                    System.out.println("The court ID is : " + c.getCourtId());
                    System.out.printf("%7s %12s %9s %9s %7s %5s", "BookingID", "Date Booked" , "Time Start", "Time finish", "Member", "Court ID" );
                    System.out.println(); 
                    ArrayList<Booking> book = c.getListOfBook();
                    Collections.sort(book);
                    for(Booking b : book) {
                        System.out.println(b.toString());
                    }
                }
            }
        }
    }


    /**
     *
     * @return the array list of the bookings
     */
    public ArrayList<Booking> guiBookingCourt(){
        ArrayList<Booking>  tempBook= new ArrayList<Booking>();

        for(Sport s : sportList) {

            ArrayList<Court> cour = s.courList;
            for(Court c : cour) {
                if(!c.getListOfBook().isEmpty()) {
                    ArrayList<Booking> book = c.getListOfBook();
                    Collections.sort(book);
                    for(Booking b : book) {
                            tempBook.add(b);
                    }
                }
            }

        }

        return tempBook;
    }


    /**
     * A string representation of the class Club
     */
    public String toString()
    {
        return  name + " is the best club in town";
    }

    /**
     *  This method get data from the Sports file and create objects
     * @see demo lecture 8 Car Part Basics file handling demo file Car
     * @throws IOException something when going wrong with reading the data from the file
     * @throws FileNotFoundException if the file doesn't exist
     */
    public void getSportsFromFile() throws IOException , FileNotFoundException {

        ArrayList<String> sportFilList =  FileUtility.readFromFile("Sports.txt");

        for (String spo : sportFilList) {
            String[] spo2 = spo.split(",");

            if(spo2[0].equals("Basketball")){
                try {
                    Basketball addBask = new Basketball(spo2);
                    sportList.add(addBask);
                } catch(Exception e) {
                    System.out.println( "The Basketball didn't added to the list");
                }

            } else {
                try {
                    Badminton addBan = new Badminton(spo2);
                    sportList.add(addBan);
                } catch(Exception e) {
                    System.out.println( "The Badminton didn't added to the list");
                }

            }

        }

    }

    /**
     * This method get data from the members file and create objects
     * @see demo lecture 8 Car Part Basics file handling demo file Car
     * @throws IOException something when going wrong with reading the data from the file
     * @throws FileNotFoundException if the file doesn't exist
     */
    public void getMembersFromFile() throws IOException , FileNotFoundException{

        ArrayList<String> membeFilList =  FileUtility.readFromFile("Members.txt");

        for(String mem : membeFilList) {
            String[] memSp = mem.split(",");
            memberList.add(new Member(memSp));
        }

    }
    
    /**
     * This method get data from the members file and create objects
     * @see demo lecture 8 Car Part Basics file handling demo file Car
     * @throws IOException something when going wrong with reading the data from the file
     * @throws FileNotFoundException if the file doesn't exist
     */
    public void getBookingsFromFile() throws IOException , FileNotFoundException{

        ArrayList<String> bookingFilList =  FileUtility.readFromFile("Booking.txt");

        for(String book : bookingFilList) {
            String[] memSp = book.split(",");
            
            if(memSp.length > 0) {
                        
                int bookIdadd = Integer.valueOf(memSp[0]);
                LocalDate dayB = DateUtility.convertDate(memSp[1]);
                LocalTime starTi = DateUtility.convertTime(memSp[2]); 
                LocalTime stopTi = DateUtility.convertTime(memSp[3]);
                int memId = Integer.valueOf(memSp[4]);
                int courtN = Integer.valueOf(memSp[5]);
                
                creatBooking(bookIdadd, dayB, starTi, stopTi, memId, courtN);   
            } else {
                System.out.println("Something wrong");
            }
            
        }

    }
    
    /**
     * Create the bookings from the file 
     * @param bookId booked Id
     * @param dayB the booking date
     * @param starTi the start time
     * @param stopTi the end time
     * @param memId the member Id
     * @param courtN and the Court number
     */
    public void creatBooking(int bookIdadd, LocalDate dayB, LocalTime starTi, LocalTime stopTi, int memId, int courtN) {
        
        Member memberAdd = null;
        Court courtAdd = null;
        // Get the Member Object
        for(int i =0 ; i < memberList.size(); i++ ) {

            // Find the member from the list
            if(memberList.get(i).getMemId() == memId) {
                memberAdd = memberList.get(i);
            }

        }
        
        // get the court Object
        for(Sport tempSpo : sportList) {
            ArrayList<Court> tempCourt = tempSpo.getCourtList(); 
            for(Court searCourt : tempCourt) {
                if(searCourt.getCourtId() == courtN) {
                    courtAdd = searCourt;
                }
            }
        }
        
        Booking addBook = new Booking(bookIdadd, dayB, starTi, stopTi, memberAdd , courtAdd);
        
        // get the court Object
        for(Sport tempSpo : sportList) {
            ArrayList<Court> tempCourt = tempSpo.getCourtList(); 
            for(Court searCourt : tempCourt) {
                if(searCourt.getCourtId() == courtN) {
                    searCourt.addCourtBooking(addBook);
                    bookId++;
                }
            }
        }
        
        
    }
    
    /**
     * write the booking to the file
     */
    public void writeDataToFile() {
        try{
        ArrayList<String> dataS = new ArrayList<String>();
        
        for (Sport writeSort : sportList ){
            dataS.addAll(writeSort.writeDataTo());
        }
        
        FileUtility.writeToFile("Booking.txt", dataS);
        }
        catch(Exception e){
            System.out.println("Something went wrong!");
        }

    }
    
}