package quiz_system;

import java.sql.Date;
import java.text.SimpleDateFormat;

public final class TimeConstants {
	public static final long MILLISECOND = (long)1;
	public static final long SECOND = 1000*MILLISECOND;
	public static final long MINUTE = 60*SECOND;
	public static final long HOUR = 60*MINUTE;
	public static final long DAY = 24*HOUR;
	public static final long WEEK = 7*DAY;
	public static final long MONTH = 30*DAY;
	public static final long YEAR = 365*DAY;
	public static final long TEN_YEARS = 10*YEAR;
	public static final long HUNDRED_YEARS = 100*YEAR;
	
	public static String getDate(long time) {
		Date d = new Date(time);
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        String dText = df.format(d);
        return dText;		
	}
}

