/**
 * Write a description of class m here.
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */

import java.util.ArrayList;

public class Badminton extends Sport
{

	private boolean racketProvided;

	/**
	 * Constructor of the class Badminton
	 * @param name
	 * @param netHeight
	 * @param usageFee
	 * @param insuranceFee
	 */
	public Badminton(String name, boolean racketProvided ,int usageFee, int insuranceFee){
		super(name, usageFee, insuranceFee);
		this.racketProvided = racketProvided;
	}

	/**
	 * Constructor of the class Badminton
	 * @param dataSport the String array from the file
	 */
	public Badminton(String[] dataSport) throws Exception{
		super(dataSport);
		this.racketProvided = Boolean.valueOf(dataSport[2]);
	}

	/**
	 * getter of the variable racker provided
	 * @return if the rackets provided or not
	 */
	public boolean getRackPro() {
		return this.racketProvided;
	}

	/**
	 * setter of the provided rackets
	 * @param racketProvided
	 */
	public void setRackPro(boolean racketProvided) {
		this.racketProvided = racketProvided;
	}


	/**
	 * string representation of the object Badminton
	 */
	public String toString()
	{
		return super.toString() + " the racket provided " + String.valueOf(racketProvided);
	}

	/**
	 * compare the Class Badminton
	 */
	public boolean equals(Object badm) {
		boolean re = false;
		if(this == badm) {
			re = true;
		} else if ( badm instanceof Badminton) {
			Badminton other = (Badminton) badm;
			re = super.equals(badm) && this.racketProvided == other.racketProvided;
		}

		return re;
	}

}
