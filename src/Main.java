import java.sql.Connection;

import database.DatabaseConfig;
import database.DatabaseConnection;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		DatabaseConnection db = new DatabaseConnection();
		Connection conn;
		try 
		{
			conn = db.connect(DatabaseConfig.HOST, DatabaseConfig.DB, DatabaseConfig.USERNAME, DatabaseConfig.PASSWORD);
			db.executeQuery(conn, "SELECT * FROM users");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
