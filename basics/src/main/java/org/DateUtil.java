package org;

// https://www.w3schools.com/java/default.asp

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;

public class DateUtil {

	public static final String YYYYMMDD_HHMMSS = "yyyyMMdd-hhmmss";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddhhmmss";
	public static final String YYYYMMDD_HHMMSS_ = "yyyyMMdd_hhmmss";
	public static final String DDMMYYYY = "dd/MM/yyyy";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYMMDD = "yyMMdd";
	public static final String YYYY = "yyyy";
	public static final String HHMMSS = "hhmmss";

	public static void main(String[] args) {

		String str;

		System.out.println("DateUtil");

		/* Classes
		import java.time.* // date and time API
		
		LocalDate: date (yyyy-MM-dd)

		LocalTime: hour (HH-mm-ss-ns)

		LocalDateTime: date + time (yyyy-MM-dd-HH-mm-ss-ns)

		ZonedDateTime: date + time + time zone

		DateTimeFormatter: formatter
		*/

		/***************************************/

		/* current date */
		// import java.time.LocalDate
		LocalDate myObj = LocalDate.now(); // 2023-06-20

		/* current time */
		//import java.time.LocalTime
		LocalTime myObj2 = LocalTime.now(); // 20:02:58.394541

		/* current date and time */
		//import java.time.LocalDateTime
		LocalDateTime myObj3 = LocalDateTime.now(); // 2023-06-20T20:02:58.394762

		/* create date */
		LocalDate date1 = LocalDate.of(2015, Month.JANUARY, 20);
		LocalDate date2 = LocalDate.of(2015, 1, 20);

		/* create time */
		LocalTime time1 = LocalTime.of(6, 15, 30, 200); // hour, minute, seconds, nanoseconds

		/* create Date Time */
		LocalDateTime dateTime1 = LocalDateTime.of(date1, time1);
		LocalDateTime dateTime2 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);

		/* Get time in ms */
		long start = System.currentTimeMillis();
		
		/* Get process time */

		// start time
		long time = System.currentTimeMillis();
		
		// process
		System.out.println(LongStream.range(0, 100000000).sum());
		
		// process time
		System.out.println(System.currentTimeMillis() - time);

		/* methods */
		date1.getDayOfWeek();
		date1.getDayOfYear();
		date1.getMonth();

		date1 = date1.plusDays(2);
		date1 = date1.plusWeeks(1);
		date1 = date1.plusMonths(1);
		date1 = date1.plusYears(5);

		dateTime1 = dateTime1.minusDays(1);
		dateTime1 = dateTime1.minusHours(10);
		dateTime1 = dateTime1.minusSeconds(30);

		/* Formatting Date and Time */
		// import java.time.LocalDateTime;
		// import java.time.format.DateTimeFormatter;

		LocalDateTime myDateObj = LocalDateTime.now(); // 2023-06-20T20:10:45.427136
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj); // 20-06-2023 20:10:45

		/* of(pattern):

		yyyy-MM-dd			"1988-09-29"	
		dd/MM/yyyy			"29/09/1988"	
		dd-MMM-yyyy			"29-Sep-1988"	
		E, MMM dd yyyy		"Thu, Sep 29 1988"

		where:
			MMMM 	: month, ex: M = 1, MM = 01, MMM = Jan, MMMM = January
			dd 		: date, ex: dd = 01
			yyyy	: year, ex: yy = 19, yyyy = 2019
			hh		: hour, ex: hh = 01
			:		: colon
			mm		: minute
		*/
		
		LocalDate date = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time4 = LocalTime.of(11, 12, 34);
		LocalDateTime dateTime = LocalDateTime.of(date, time4);
		System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
		System.out.println(time4.format(DateTimeFormatter.ISO_LOCAL_TIME));
		System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		/* output:
		2020-01-20
		11:12:34
		2020-01-20T11:12:34
		 */
		
		LocalDate date3 = LocalDate.of(2020, Month.JANUARY, 20);
		LocalTime time2 = LocalTime.of(11, 12, 34);
		LocalDateTime dateTime3 = LocalDateTime.of(date3, time2);
		DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		System.out.println(shortF.format(dateTime)); // 1/20/20 11:12 AM
		System.out.println(mediumF.format(dateTime)); // Jan 20, 2020 11:12:34 AM

		DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm");
		System.out.println(dateTime.format(f)); // January 20, 2020, 11:12

		/* Parsing Dates and Times */
		DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM dd yyyy");
		LocalDate date4 = LocalDate.parse("01 02 2015", f2);
		LocalTime time3 = LocalTime.parse("11:22");
		System.out.println(date4); // 2015-01-02
		System.out.println(time3); // 11:22

		/* Convert Date to String */
		Date dateNow = new Date();
		String sDateNow = new SimpleDateFormat("yyyyMMdd").format(dateNow);

		/* Convert String to Date */
		String dateString = "2023-09-21";
		String dateFormatPattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
	
		try {
			Date date5 = dateFormat.parse(dateString);
		} catch(ParseException e) {
			e.printStackTrace();
		}

		/* Date comparison */
		Date date5 = new Date();
		Date date6 = new Date();

		if(date5.before(date6));
					   
		LocalDate start2 = LocalDate.of(2015, Month.JANUARY, 1);
		LocalDate end = LocalDate.of(2015, Month.MARCH, 30);
		if(start2.isBefore(end));
			
		/* Period */
		Period period = Period.ofMonths(1);
		Period period2 = Period.of(1, 0, 7); // every year and 7 days
		start2 = start2.plus(period2); // adds the period
		
		/***************************************/

		/**
		 * measure the process time
		 */
		long time5 = System.currentTimeMillis();
		// some process
		System.out.println(System.currentTimeMillis() - time5); // get time in ms

		/**
		 * set birthdate of 30 years old
		 */
		LocalDate.now().minusYears(30);
		
		/**
		 * formatDate: Date to String format
		 */
		System.out.println(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		System.out.println(DateUtil.formatDate("07-08-2021","dd-MM-yyyy","dd/MM/yyyy"));
		System.out.println(DateUtil.formatDate("20210807","yyyyMMdd","dd/MM/yyyy"));

		/**
		 * strDateToDate
		 */
		System.out.println(DateUtil.strDateToDate("2021-08-07","yyyy-MM-dd"));

		/**
		 * dateToFormatDate
		 */
		System.out.println(DateUtil.dateToFormatDate(new Date(), "yyyy-MM-dd"));
		
		/**
		 * getCurrentDate
		 */
		Date date7 = getCurrentDate();
		System.out.println("date7: " + date7);
		
		/**
		 * Get a moment in time on the time-line, with nanosecond precision and is not dependent on the time zone
		 * or calendar system.
		 */
		//import java.time.Instant; // Java 8, Date-Time API
		// java.time.Instant.now()

		// current moment in UTC
		Instant now = Instant.now();

		// current moment in UTC
		str = getCurrentDateUTC(); // 2024-08-25T01:22:48.328Z

		// adding duration to Instant
		Instant futureInstant = now.plusSeconds(3600); // Adding 1 hour (3600 seconds)
		System.out.println("Future Instant: " + futureInstant);
				
		// comparing Instants
		System.out.println("Is futureInstant after now? " + futureInstant.isAfter(now));

		// getting Epoch Second and Nanosecond
		long epochSecond = now.getEpochSecond();
		int nano = now.getNano();
		System.out.println("Epoch Second: " + epochSecond);
		System.out.println("Nanosecond: " + nano);
				
		/* Alternatives:	
		Timestamps in a Database: get date and time, use java.sql.Timestamp

		Representing Durations: use java.time.Duration

		System Time: use System.currentTimeMillis()

		Application Time with Time Zone: use ZonedDateTime
		*/

//		public static final Date PERSON_BIRTH_DATE = Date.from(
//				LocalDate.of(1975, Month.JANUARY, 1)
//						.atStartOfDay(LIMA_ZONE_ID)
//						.toInstant());
	}

	public static String getDateNowStr()
	{
		DateFormat dateFormat = DateFormat.getDateInstance(3);
		return dateFormat.format(new Date());
	}

	public static Date getDateNow()
	{
		return new Date();
	}

	public static String getHourNowString()
	{
	    return formatDateNow("HH:mm");
	}
	  
	public static String formatDateNow(String format)
	{
		Locale currentLocale = new Locale("pe", "ES");
		DateFormat dateFormat = new SimpleDateFormat(format, currentLocale);
		return dateFormat.format(new Date());
	}
	
	public static String formatDateLocal(Date date, String pattern)
	{
		Locale currentLocale = new Locale("pe", "ES");
		DateFormat dateFormat = new SimpleDateFormat(pattern, currentLocale);
		return dateFormat.format(date);
	}
	
	/**
	 * From Date to pattern (ex: yyyy-MM-dd).
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date, String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
	    return format.format(date);
	}
	
	public static String formatDateNow(String date, String format)
	{
		Date dateTemp;
		if ((date == null) || (date.length() == 0)) {
			dateTemp = new Date();
		} else {
			dateTemp = strDateToDate(date, "yyyy-MM-dd");
		}
		Locale currentLocale = new Locale("pe", "ES");
		DateFormat dateFormat = new SimpleDateFormat(format, 
		currentLocale);
		return dateFormat.format(dateTemp);
	}

	/**
	 * ex: from 07-08-2021 to 07/08/2021
	 *     from 20210807 to 07/08/2021
	 * @param startDate
	 * @param startFormat
	 * @param endFormat
	 * @return
	 */
	public static String formatDate(String startDate, String startFormat, String endFormat){
		try{
			SimpleDateFormat formatoIngreso = new SimpleDateFormat(startFormat);
			SimpleDateFormat formatoSalida = new SimpleDateFormat(endFormat);
			Date fecha = formatoIngreso.parse(startDate);
			return formatoSalida.format(fecha);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	/**
	 * ex: from (new Date(), "yyyy-MM-dd") to Sat Aug 07 00:00:00 COT 2021
	 * @param date
	 * @param formatDate
	 * @return
	 */
	public static Date dateToFormatDate(Date date, String formatDate){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
			String strFecha = sdf.format(date);
			Date dateFormatted = sdf.parse(strFecha);
			return dateFormatted;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * ex: from 2021-08-07 to Sat Aug 07 00:00:00 COT 2021
	 * @param strDate
	 * @param formatDate
	 * @return
	 */
	public static Date strDateToDate(String strDate, String formatDate){
		try{
			SimpleDateFormat formatoIngreso = new SimpleDateFormat(formatDate);
			Date date = formatoIngreso.parse(strDate);
			return date;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static int compareDate(Date firstDate, Date endDate)
	{
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(firstDate);
		Calendar calendarFirst = new GregorianCalendar(calendar.get(1), 
		calendar.get(2), 
		calendar.get(5));
		calendar.setTime(endDate);
		Calendar calendarEnd = new GregorianCalendar(calendar.get(1), 
		calendar.get(2), 
		calendar.get(5));
		
		if (calendarFirst.getTime().getTime() < calendarEnd.getTime().getTime()) {
			return 1;
		}
		
		if (calendarFirst.getTime().getTime() == calendarEnd.getTime().getTime()) {
			return 0;
		}
		return -1;
	}
  
//	public int compareDate(String firstDate, String endDate, String format)
//	{
//		int result = 0;
//
//		try {
//			String[] sFormat = {format};
//
//			Date dFechaInicio = DateUtils.parseDate(firstDate, sFormat);
//			Date dFechaFin = DateUtils.parseDate(endDate, sFormat);
//			result = dFechaInicio.compareTo(dFechaFin);
//		} catch (ParseException e)
//		{
//			System.out.println(e.getMessage());
//		}
//
//		return result;
//	}

	public static String getDateLarge()
	{
		String fecha= new SimpleDateFormat("EEEE d 'de' MMMMM 'de' yyyy", new Locale("ES")).format(new Date());
		String[] arregloFecha = fecha.split(" ");
	
		if ((arregloFecha.length >= 1) && 
		  (arregloFecha[0].length() >= 1)) {
		  arregloFecha[0] = 
			(arregloFecha[0].substring(0, 1).toUpperCase() + arregloFecha[0].substring(1, arregloFecha[0].length()));
		}
		if ((arregloFecha.length >= 4) && 
		  (arregloFecha[3].length() >= 1)) {
		  arregloFecha[3] = 
			arregloFecha[3].substring(0, 1).toUpperCase() + arregloFecha[3].substring(1, arregloFecha[3].length());
		}
		fecha = "";
		String[] arrayOfString1;
		int j = (arrayOfString1 = arregloFecha).length;
		for (int i = 0; i < j; i++)
		{
		  String item = arrayOfString1[i];
		  fecha = fecha + item + " ";
		}
		return fecha;
	}

	public static boolean validateTime24Hours(String time)
	{
		if ((time == null) || ("".equals(time))) {
			return false;
		}
		
		Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		Matcher matcher = pattern.matcher(time);
		return matcher.matches();
	}

	public static String formatDateLabel(Date date)
	{
		String stringFecha = "";
		Format formatter = new SimpleDateFormat("dd");
		stringFecha = stringFecha + formatter.format(date) + " de ";
		formatter = new SimpleDateFormat("MMMM");
		stringFecha = stringFecha + formatter.format(date) + " del ";
		formatter = new SimpleDateFormat("yyyy");
		stringFecha = stringFecha + formatter.format(date);
	
		return stringFecha;
	}
	
	public static String formatDateMasMenos(String format, int daysMasMenos)
	{
		Locale currentLocale = new Locale("pe", "ES");
		DateFormat dateFormat = new SimpleDateFormat(format, currentLocale);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, daysMasMenos);
		return dateFormat.format(calendar.getTime());
	}
	
	/**
	 * getCurrentDate
	 */
	public static Date getCurrentDate(){
		String timeZoneId = "America/New_York";
				
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
		return calendar.getTime();
	}

	/**
	 * Convert String Date to Date with ZonedDateTime format
	 */
	public static Date strToDateConvert(String dateString, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format + Constants.PARCIAL_FORMATTER);
		ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateString + Constants.PARCIAL_STRING_DATE, formatter);
		return Date.from(zonedDateTime.toInstant());
	}

	/**
	 * Convert Date to String "yyyy-MM-dd"
	 */
	public static String dateStrFromDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	/**
	 * getCurrentTimestamp
	 */
	public static Date getCurrentTimestamp() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.getTime();
	}
	
	/**
	 * getDateString
	 */
	public static String getDateString(String text) {
		if (ValidationUtil.isEmpty(text)) {
			return Constants.EMPTY;
		}
		String fecha[] = text.split("\\/");
		if(fecha.length == 3){
		StringBuilder builder = new StringBuilder();
			builder.append(fecha[2]);
			builder.append(fecha[1]);
			builder.append(fecha[0]);
			return builder.toString();
		}
		return text;
	}

	/**
	 * getDateDatoBasico
	 */
	public static String getDateDatoBasico(String text) {
		if (ValidationUtil.isEmpty(text)) {
			return Constants.EMPTY;
		}
		String fecha[] = text.split("\\/");
		if(fecha.length == 3){
			StringBuilder builder = new StringBuilder();
			builder.append(fecha[0]);
			builder.append(fecha[1]);
			builder.append(fecha[2]);
			return builder.toString();
		}
		return text;
	}

	/**
	 * getDateRequest
	 */
	public static String getDateRequest(String text) {
		if (ValidationUtil.isEmpty(text)) {
			return Constants.EMPTY;
		}
		String fecha[] = text.split("\\/");
		StringBuilder builder = new StringBuilder();
		builder.append(fecha[0]);
		builder.append(fecha[1]);
		builder.append(fecha[2]);
		return builder.toString();
	}
	
	public static String setFechaRender(String fecha) {
		if (ValidationUtil.isEmpty(fecha)) {
			return Constants.EMPTY;
		}
		fecha = fecha.trim();
		StringBuilder builder = new StringBuilder();
		builder.append(fecha.substring(6, 8));
		builder.append("/");
		builder.append(fecha.substring(4, 6));
		builder.append("/");
		builder.append(fecha.substring(0, 4));// 2017 04 03
		return builder.toString();
	}

	public static String setFechaRenderDatoBasico(String fecha) {// 29042017
		if (ValidationUtil.isEmpty(fecha)) {
			return Constants.EMPTY;
		}
		
		fecha = fecha.trim();
		StringBuilder builder = new StringBuilder();
		builder.append(fecha.substring(0, 2));
		builder.append("/");
		builder.append(fecha.substring(2, 4));
		builder.append("/");
		builder.append(fecha.substring(4, 8));// 2017 04 03
		return builder.toString();
	}
	
	public static String obtenerFechaActual() {
		Date fechaActual = getCurrentTimestamp();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = sdf.format(fechaActual);
		return getDateString(fechaFormateada);
	}
	
	/**
	 * construirFecha
	 * @param dia
	 * @param mes
	 * @param anio
	 * @return
	 */
	public static String construirFecha(String dia,String mes,String anio){
		if(ValidationUtil.isEmpty(dia) || "00".equals(dia) || ValidationUtil.isEmpty(mes) || "00".equals(mes) || ValidationUtil.isEmpty(anio) || "00".equals(anio)){
			return null;
		}
		StringBuilder sbFecha = new StringBuilder();
		return sbFecha.append(dia).append("/").append(mes).append("/").append(anio).toString();
	}

	// current moment in UTC
	public static String getCurrentDateUTC() {
		ZonedDateTime now = ZonedDateTime.now().withZoneSameInstant(java.time.ZoneOffset.UTC);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		return now.format(formatter);
	}
}