package co.verisoft.fw.utils;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * singleton class 
 * handle Action Time this class made for 
 * get the total time for any action that made from webdriver
 * such as click , sendkeys , navigate ,....
 * @author David Yehezkel
 *
 */
public class ActionTime {
	private static ActionTime measureTime = null;
	private long start , end ,delta;
	private ActionTime() {}
	
	public static ActionTime getMeasureTime() {
		if(measureTime == null) {
			measureTime = new ActionTime();
		}
		return measureTime;
	}
	
	public void captureStartTime() {
		this.start = System.currentTimeMillis();
	}
	
	public void captureEndTime() {
		this.end = System.currentTimeMillis();
	}
	
	public void delta() {
		this.delta = this.end - this.start;
	}
	
	public long getDelta() {
		this.delta();
		return this.delta;
	}

	/**
	 * this function add specific amount of time unit to time
	 *
	 * @param myTime time to change
	 * @param timeUnit time unit to add
	 * @param amount amount to add
	 * @return new time after adding the time unit
	 * @throws ParseException
	 */
	public static String addTimeToTime(String myTime, int timeUnit, int amount) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		Date d = df.parse(myTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(timeUnit, amount);
		return df.format(cal.getTime());
	}

	/**
	 * convert string to LocalDate
	 * @param date - date as string
	 * @param format - format of date to convert
	 * @return
	 */
	public static LocalDate convertStringToLocalDate(String date, String format){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		formatter = formatter.withLocale(Locale.ENGLISH);  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
		return LocalDate.parse(date, formatter);
	}
	
}
