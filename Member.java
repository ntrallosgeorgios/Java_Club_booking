/**
 * Is the class of Members
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */
import java.util.ArrayList;

public class Member
{

    private String name;
    private int memId;
    private ArrayList<String> sportPlay;
    private boolean financial;

    /**
     * Constructor of the Class Member
     * @param memId Member Id
     * @param name name of the Member
     */
    public Member( int memId, String name, boolean financial){
        this.memId = memId;
        this.name = name;
        this.financial = financial;
        sportPlay = new ArrayList<String>();
    }

    public Member(String[] memAttr) {

        this.memId = Integer.parseInt(memAttr[0]);
        this.name = memAttr[1];
        this.financial = Boolean.parseBoolean(memAttr[2]);
        this.sportPlay = getSpoL(memAttr);
    }

    /**
     *
     * @return the member Id
     */
    public int getMemId() {
        return this.memId;
    }

    /**
     *
     * @param memId set the member Id
     */
    public void setMemId(int memId) {
        this.memId = memId;
    }

    /**
     *
     * @return the name of the member
     */
    public String getMemName() {
        return this.name;
    }

    /**
     *
     * @param name set a new name 
     */
    public void setMemName(String name) {
        this.name = name;
    }

    /**
     *
     * @return if the member is financial
     */
    public boolean getFinan() {
        return this.financial;
    }

    /**
     *
     * @param financial change the value of the financial 
     */
    public void setFinan(boolean financial) {
        this.financial = financial;
    }

    /**
     * getter of the SportPlay
     * @return the String array of the sport that Member played
     */
    public ArrayList<String> getSportPlay() {
        return this.sportPlay;
    }

    /**
     * Add a sport to the list of sports
     * @param sportPlay
     */
    public boolean addSportPlay(String sportPlay) {
        return this.sportPlay.add(sportPlay);
    }

    /**
     * Method that is taking the input from the file
     * and return an ArrayList of string
     * @param arg the string array from the file
     * @return the ArrayList
     */
    private ArrayList<String> getSpoL(String[] arg){

        ArrayList<String> tmpList = new ArrayList<String>() ;

        for(int i = 3; i < arg.length; i++) {
            tmpList.add(arg[i]);
        }

        return tmpList;
    }

    /**
     * string representation of the object Member
     */
    public String toString()
    {
        return "The member id is : " + memId + " with name: " + name + "and they are financial " + String.valueOf(financial)  ;
    }

}