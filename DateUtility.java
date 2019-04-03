/**
 * Is the class of DataUtility.
 * where we manipulate the data and time inputs
 *
 * @author Georgios Ntrallos
 * @version 1.0
 */

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Provides date utility methods for converting dates from string to
 * Java 8 date formats, and vice versa.
 *
 * @author  Georgios Ntrallos
 * @version  1.0
 */
public class DateUtility
{

	/**
	 * Transform the string to LocalDate
	 * @param aDate take a string that is represent a date
	 * @return the string to LocalDate
	 * @see https://www.mkyong.com/java8/java-8-how-to-convert-string-to-localdate/
	 * @see https://stackoverflow.com/questions/32823368/java-8-localdatetime-is-parsing-invalid-date
	 * @see https://stackoverflow.com/questions/17405714/java-how-do-you-return-a-value-from-try-catch-and-finally
	 * @see https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeParseException.html
	 * @throws DateTimeParseException if the date is not valid
	 */
	public static LocalDate convertDate(String aDate) {

		LocalDate reDa = null;

		try {
			DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("d/MM/yyyy");
			LocalDate tranToDate = LocalDate.parse(aDate,  datePattern);
			reDa = tranToDate;

		} catch (DateTimeParseException exe) {
			//throw exe;
			System.out.println("The Input doesen't follow the patter d/MM/yyyy");
		}
		return reDa;
	}

	/**
	 * Transform the string to LocalTime
	 * @param aTime a string that is contain the time
	 * @return the time in LocalTime
	 * @throw DateTimeParseException if the time is not valid
	 */
	public static LocalTime convertTime(String aTime) {

		LocalTime reT = null;

		try {
			DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("H:mm");
			LocalTime tranToTime = LocalTime.parse(aTime, timePattern);
			reT = tranToTime;
		} catch ( DateTimeParseException exe ) {
			// throw exe;
			System.out.println("The Input doesen't follow the patter HH:mm");
		}

		return reT;
	}

	/**
	 * @see https://stackoverflow.com/questions/28177370/how-to-format-localdate-to-string
	 * Receives times a LocalDate object, converting to string
	 * @param aDate a Date to convert
	 * @return date as a strong
	 */
	public static String dateToString(LocalDate aDate) {
		
		DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String datSt = aDate.format(datePattern);
		
		return  datSt;
	}

	/**
	 * Receives times a LocalTime object, converting to string
	 *
	 * @param       aTime   a time to convert
	 * @return      time as formatted string
	 */
	public static String timeToString(LocalTime aTime)
	{
		return aTime.toString();
	}

}