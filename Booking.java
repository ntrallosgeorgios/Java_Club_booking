/**
 * Is the class of booking.Where we booking the a member to a court
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */

import java.time.*;
import java.util.ArrayList;

public class Booking implements Comparable<Booking>
{
    private int bookingId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private  Member bookedBy;
    private  Court bookedFor;

    /**
     * Constructor of the class Booking
     * @param bookingId the bookId
     * @param bookingDate the Booking date
     * @param startTime the starting time
     * @param endTime the end time
     * @param bookedBy the member that is booked the court
     * @param bookedFor specify the court
     */
    public Booking(int bookingId, LocalDate bookingDate, LocalTime startTime, LocalTime endTime, Member bookedBy, Court bookedFor){
        this.bookingId = bookingId;
        this.bookingDate =  bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedBy = bookedBy;
        this.bookedFor = bookedFor;
    }

    /**
     *
     * @return the booking id
     */
    public int getBookingId() {
        return this.bookingId;
    }

    /**
     *
     * @param bookingId set the booking Id
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     *
     * @return the booking date
     */
    public LocalDate getBookingDate() {
        return this.bookingDate;
    }

    /**
     *
     * @param bookingDate set a new booking date
     */
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     *
     * @return the starting time
     */
    public LocalTime getStartTi() {
        return this.startTime;
    }

    /**
     *
     * @param startTime set new starting time
     */
    public void setStartTi(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return the end time
     */
    public LocalTime getEndTi() {
        return this.endTime;
    }

    /**
     *
     * @param endTime set a new end time
     */
    public void setEndTi(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * getter of the bookedBy
     * @return the Member that was do the booking
     */
    public Member getBookBy() {
        return this.bookedBy;
    }

    /**
     * setter of the bookedBy
     * @param bookedBy change the member
     */
    public void setBookBy(Member bookedBy) {
        this.bookedBy = bookedBy;
    }

    /**
     * getter of the bookedFor
     * @return the court that was booked
     */
    public Court getBookFor() {
        return this.bookedFor;
    }

    /**
     * setter of the bookedFor
     * @param bookedFor change the court
     */
    public void setBookFor(Court bookedFor) {
        this.bookedFor = bookedFor;
    }
    
    /**
     * shorting the bookings in ascending order
     */
    public int compareTo( Booking book) {

    	return this.bookingId > book.bookingId ? 1 : this.bookingId < book.bookingId ? -1 : 0;
    }

    /**
     * Add the informations of booking to the arraylist<String>
     * in the Court class
     * @param take the array list of 
     */
    public void writToFil(ArrayList<String> data) {

        String booId = String.valueOf(this.bookingId);
        String datbo = DateUtility.dateToString(this.bookingDate);
        String tiS = DateUtility.timeToString(this.startTime);
        String tiF = DateUtility.timeToString(this.endTime);
        String memB = String.valueOf(bookedBy.getMemId());
        String courB =String.valueOf(bookedFor.getCourtId());

        String retuStr = String.format("%s,%s,%s,%s,%s,%s",booId,datbo,tiS,tiF,memB,courB);
        data.add(retuStr);
    }

    /**
     * String representation of the Object Booking.
     */
    public String toString() {
    	String booId = String.valueOf(bookingId);
        String datbo = DateUtility.dateToString(bookingDate);
        String tiS = DateUtility.timeToString(startTime);
        String tiF = DateUtility.timeToString(endTime);
        String memB = bookedBy.getMemName();
        String courB =String.valueOf(bookedFor.getCourtId());

        return String.format("%8s %12s %9s %9s %9s %5s" ,booId, datbo, tiS, tiF, memB, courB);

    }
}