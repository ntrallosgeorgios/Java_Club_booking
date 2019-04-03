/**
 * Write a description of class m here.
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */
import java.util.ArrayList;

public class Basketball extends Sport
{

	private double netHeight;

	/**
	 * constructor of the class Sport
	 * @param dataSport comma String part name , usageFee, insuranceFee
	 */
	public Basketball(String[] dataSport) throws Exception{
		super(dataSport);
		this.netHeight = Double.valueOf(dataSport[2]);
	}


	/**
	 * Constructor of the class Basketball
	 * @param name
	 * @param netHeight
	 * @param usageFee
	 * @param insuranceFee
	 */
	public Basketball(String name, double netHeight ,int usageFee, int insuranceFee){
		super(name, usageFee, insuranceFee);
		this.netHeight = netHeight;
	}

	/**
	 * getter of the value net height
	 * @return the height of the net
	 */
	public double getNetHeight() {
		return this.netHeight;
	}

	/**
	 * setter of the value netHeight
	 * @param netHeight
	 */
	public void setNetHeight(double netHeight) {
		this.netHeight = netHeight;
	}

	/**
	 * String representation of the object Basketball
	 */
	public String toString()
	{
		return super.toString() + " with net height " + netHeight;
	}

	/**
	 * compare the Class Basketball
	 */
	public boolean equals(Object bask) {
		boolean re = false;
		if(this == bask) {
			re = true;
		} else if ( bask instanceof Basketball) {
			Basketball other = (Basketball) bask;
			re = super.equals(bask) && this.netHeight == other.netHeight;
		}

		return re;
	}
}
