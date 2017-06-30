package org.smendes.room.reservation.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateUtil {
	
	private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	private static final String NAME_CLASS = DateUtil.class.getName();
	private static final Logger LOGGER = Logger.getLogger(NAME_CLASS);
	
	/**
	 * Return a date as a string. Example: 2014-08-20 15:30 
	 * @return Date as string.
	 */
	public static String dateTimeAsString(Date date){
		String dateAsString = "";
		dateAsString = dateTimeFormat.format(date);
		return dateAsString;
	}

	/** 
	 * Comparar se uma data esta dentro de um periodo
	 * @param dateToCompare Data para comparar
	 * @param beginDate Inicio do periodo
	 * @param endDate Final do periodo
	 * @return True se a data esta dentro do periodo, senao false.
	 */
	public static boolean dateIsBetween(final String dateToCompare, String beginDate, String endDate) {
		
		boolean isBetween = false;
		try {
			
			Date compare = toDateTime(dateToCompare);
			Date begin = toDateTime(beginDate);
			Date end = toDateTime(endDate);
			isBetween = compare.getTime() >= begin.getTime() && compare.getTime() <= end.getTime();
					
		} catch (Exception e) {
			LOGGER.logp(Level.SEVERE, NAME_CLASS, "dateIsBetween", e.getMessage());
		}

		System.out.println("dateToCompare=" + dateToCompare);
		System.out.println("beginDate=" + beginDate);
		System.out.println("endDate=" + endDate);
		System.out.println("isBetween=" + isBetween);
		
		return isBetween;
	}
	
	
	/**
	 * Convert string as a date. 
	 * @return Date.
	 * @throws BusinessException 
	 */
	public static Date toDateTime(final String dateAsString) throws Exception {
		return dateTimeFormat.parse(dateAsString.replace('T', ' '));
	}	
	
}
