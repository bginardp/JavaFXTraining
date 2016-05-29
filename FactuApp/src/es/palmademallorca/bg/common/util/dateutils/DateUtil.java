package es.palmademallorca.bg.common.util.dateutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Helper functions for handling dates.
 *
 * @author Marco Jakob
 */
public class DateUtil {

	private static List<DateTimeFormatter> patrons = new ArrayList<DateTimeFormatter>() {
		{
			add(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			add(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			add(DateTimeFormatter.ofPattern("ddMMyyyy"));
		}
	};

	/** The date pattern that is used for conversion. Change as you wish. */
	private static final String DATE_PATTERN = "dd-MM-yyyy";

	/** The date formatter. */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_PATTERN);

	/**
	 * Returns the given date as a well formatted String. The above defined
	 * {@link DateUtil#DATE_PATTERN} is used.
	 *
	 * @param date
	 *            the date to be returned as a string
	 * @return formatted string
	 */
	public static String format(Date date) {
		if (date == null) {
			return null;
		}
		return DATE_FORMATTER.format(date);
	}

	/**
	 * Converts a String in the format of the defined
	 * {@link DateUtil#DATE_PATTERN} to a {@link LocalDate} object.
	 *
	 * Returns null if the String could not be converted.
	 *
	 * @param dateString
	 *            the date as String
	 * @return the date object or null if it could not be converted
	 */
	public static Date parse(String dateString) {
		try {
			return DATE_FORMATTER.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}
/**
 *
 * @param dateString
 * @return
 */
	public static LocalDate parseLD(String dateString) {
		for (DateTimeFormatter formatter : patrons) {
			try {
				return LocalDate.parse(dateString, formatter);
			} catch (DateTimeParseException e) {
				;
			}
		}
		return null;
	}

	/**
	 * Checks the String whether it is a valid date.
	 *
	 * @param dateString
	 * @return true if the String is a valid date
	 */
	public static boolean validDate(String dateString) {
		// Try to parse the String.
		return DateUtil.parse(dateString) != null;
	}
}