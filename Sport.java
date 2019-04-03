/**
 * Is the super class Sports
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */

import java.io.IOException;
import java.util.ArrayList;

public class Sport
{
    protected String name;
    protected int usageFee;
    protected int insuranceFee;
    protected ArrayList<Court> courList;


    /**
     *
     * @param spArg A string which contains values
     * for the sports
     *
     */
    public Sport(String[] spArg) throws Exception {
        this.name = spArg[0];
        this.usageFee = Integer.valueOf(spArg[1]) ;
        this.insuranceFee = Integer.valueOf(spArg[3]);
        courList = strinToArr(spArg);
    }

    /**
     * constructor of the class Sport
     * @param name
     * @param usageFee
     * @param insuranceFee
     */
    public Sport(String name,int usageFee, int insuranceFee){
        this.name = name;
        this.usageFee = usageFee;
        this.insuranceFee = insuranceFee;
        courList = new ArrayList<Court>();
    }

    /**
     * getter of the name value
     * @return the name of the sport
     */
    public String getName() {
        return this.name;
    }

    /**
     * setter of the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter of the value usageFee
     * @return the usage fee
     */
    public int getUsageFee() {
        return this.usageFee;
    }

    /**
     * setter of the value usageFee
     * @param usageFee
     */
    public void setUsageFee(int usageFee) {
        this.usageFee = usageFee;
    }

    /**
     * getter of the value insuranceFee
     * @return the fee of the insurance
     */
    public int getInsuranceFee() {
        return this.insuranceFee;
    }

    /**
     * setter of the value insuranceFee
     * @param insuranceFee
     */
    public void setInsuranceFee(int insuranceFee) {
        this.insuranceFee = insuranceFee;
    }

    /**
     *
     * @return the list of courts
     */
    public ArrayList<Court> getCourtList(){
        return this.courList;
    }


    /**
     * That method create an array list by the strings of the file
     * @see https://stackoverflow.com/questions/11001720/get-only-part-of-an-array-in-java
     * @see
     * @param spoL take the string from the constructor
     * @return an ArrayList of courts
     */
    protected ArrayList<Court> strinToArr(String[] spoL) {

        ArrayList<Court> tempL = new ArrayList<Court>();
        for( int i = 4; i <spoL.length; i++ ){
            int courtNum = Integer.valueOf(spoL[i]);
            Court addCo = new Court(courtNum);
            tempL.add(addCo);
        }

        return tempL;
    }

    /**
     * write the the data of Booking to the file
     */
    public ArrayList<String> writeDataTo(){
        ArrayList<String> rb = new ArrayList<String>();

        try{
            for(int i = 0; i < courList.size(); i++) {
                if (!courList.get(i).getListOfBook().isEmpty()) {
                    rb.addAll(courList.get(i).saveBooking());
                }
            }
        } catch (IOException e) {
            System.out.println("Problem with the file!!");
        }
        
        return rb;
    }


    /**
     * String representation of the class Sport
     */
    public String toString()
    {
        return "The name of the sport is: " + name + ", the usage fee is: " + usageFee + " and the insurance fee is: " + insuranceFee ;
    }

    /**
	 * compare the Class Sport
	 */
    public boolean equals(Object spo) {
        boolean re = false;
        if(this == spo) {
            re = true;
        } else if ( spo instanceof Sport) {
            Sport other = (Sport) spo;
            re = name.equals(other.name) && usageFee == other.insuranceFee
                    && insuranceFee == other.insuranceFee && courList.equals(other.courList);
        }

        return re;
    }
   
}
