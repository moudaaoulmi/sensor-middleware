package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
	static public String toMySQLDate( Date date )
	{
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		return sdf.format(date).toString();
		
	}
}
