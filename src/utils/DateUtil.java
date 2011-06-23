package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil
{
	static public String toMySQLDate( Date date )
	{
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-mm-dd HH:mm:ss" );
		
		return sdf.format(date).toString();
	}
}
