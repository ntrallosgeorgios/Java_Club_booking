/**
 * Is the class of court
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */

import java.util.*;
import java.io.IOException;

public class Court implements Comparable<Court>
{
    private int courtId;
    private ArrayList<Booking> courtBookings;

    /**
     * constructor of the class Court
     * @param courtId
     */
    public Court(int courtId){
        this.courtId = courtId;
        courtBookings = new ArrayList<Booking>();
    }

    /**
     * getter of the courtId
     * @return the Id of the court
     */
    public int getCourtId() {
        return this.courtId;
    }

    /**
     * setter of the courtId
     * @param courtId set a new value to the Id of the courts
     */
    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    /**
     * add a booking object to the booking list
     * @param booking take as input the booking input
     * @return true or false depending if the object added to the list
     */
    public boolean addCourtBooking(Booking booking) {

        boolean ifExist = false;

        if(!courtBookings.contains(booking)) {
            ifExist = courtBookings.add(booking);
        }

        return ifExist;
    }

    /**
     * remove a booking object from the booking list
     * @param booking take as input the booking ID
     * @return true or false depending if the object added to the list
     */
    public boolean removeCourtBooking(int bookingId) {
        boolean delBook = false;

        Iterator<Booking> bookList= courtBookings.iterator();

        while(bookList.hasNext()) { // Check if has next
            Booking dl = bookList.next(); // give to the variable dl the lab
            int bookNum = dl.getBookingId();
            // check if the number and the subject Code is the same
            if ( bookNum == bookingId ) {
                bookList.remove();

                delBook = true;
                return delBook;
            }
        }

        return delBook;
    }
     
    /**
     * 
     * @returnReturn an array with bookings
     */
    public ArrayList<Booking> getListOfBook(){
        return this.courtBookings;
    }
    
    /**
     * String representation of the Court object
     */
    public String toString()
    {
        String retStr;
        if(courtId < 10) {
            retStr =  "Basketball Court id: " + courtId;
        } else {
            retStr =  "Badminton Court id: " + courtId;
        }
        return retStr;
    }
    
    /**
     * Write to the file of Booking the bookings
     * @throws IOException
     */
    public ArrayList<String> saveBooking() throws IOException {
    	
    	ArrayList<String> dataS = new ArrayList<String>();
    	
    	for(int i = 0; i < getListOfBook().size(); i++) {
    		courtBookings.get(i).writToFil(dataS);
    	}
    	
    	return dataS;
    }

    /**
     * shorting the Courts in ascending order
     */
    public int compareTo( Court court) {

        return this.courtId > court.courtId ? 1 : this.courtId < court.courtId ? -1 : 0;
    }

}
