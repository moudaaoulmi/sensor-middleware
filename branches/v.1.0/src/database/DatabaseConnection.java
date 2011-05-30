package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseConnection 
{

	  public void execute (String host, String database, String user, String password) 
      throws Exception { 
         
        /* run driverTest method shown below */ 
        driverTest(); 
         
        /* make the connection to the database */ 
        Connection conMe = connect (host, database, user, password); 

        /* now run a select query of the intended database */ 
        executeQuery(conMe, "SELECT * FROM users"); 
         
        /* close the database */ 
        conMe.close(); 
	  }
	  
	  public void driverTest () 
	  { 
          try { 
                  Class.forName("com.mysql.jdbc.Driver"); 
                  System.out.println("MySQL Driver Found"); 
          } catch (java.lang.ClassNotFoundException e) { 
                  System.out.println("MySQL JDBC Driver not found ... "); 
          } 
        } 
	  
	  public Connection connect (String host, String database, String user, String password) 
      throws Exception 
      { 

		String url = ""; 
		try 
		{ 
			url = "jdbc:mysql://" + host + ":3306/" + database; 
		    Connection con = DriverManager.getConnection(url, user, password); 
			System.out.println("Connection established to " + url + "..."); 
			return con; 
		} 
		catch (java.sql.SQLException e) 
		{ 
		    System.out.println("Connection couldn't be established to " + url); 
		    throw (e); 
		} 
	  }
	  
	  
	  
	  public void executeQuery(Connection con, String sqlStatement) 
      throws Exception 
      { 
            try 
            { 
                Statement cs = con.createStatement(); 
                ResultSet sqls = cs.executeQuery(sqlStatement); 

                while (sqls.next()) 
                { 
                    String id = (sqls.getObject("name").toString()); 
                    String data = (sqls.getObject("password").toString()); 
                    System.out.println(id + " " + data); 
                } 

                sqls.close(); 

            } 
            catch (SQLException e) 
            { 
                System.out.println ("Error executing sql statement"); 
                throw (e); 
            } 
	  }
	  
	  public void executeInsertQuery(Connection con, String sqlStatement) 
      throws Exception 
      { 
            try 
            { 
                Statement cs = con.createStatement(); 
                cs.executeUpdate(sqlStatement); 
                cs.close(); 
            } 
            catch (SQLException e) 
            { 
                System.out.println ("Error executing sql statement"); 
                throw (e); 
            } 
	  }
}
