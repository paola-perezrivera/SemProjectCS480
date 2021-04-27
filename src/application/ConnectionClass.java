package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// contains the MySQL log in information and currently opened
// connection. Used so that the connection can be accessed any-
// where in the controller class.
public class ConnectionClass
{
	
    public static Connection connection = null;
    String url, password, userStr;
    
    // constructor, takes the url, user, and password to access the database
    public ConnectionClass( String url, String userStr, String password) {
    	try {
    		this.url = url;
    		this.password = password;
    		this.userStr = userStr;
			this.connection = DriverManager.getConnection(url, userStr, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    
    // returns if there is a current connection open, otherwise
    // opens another with saved login information
    public Connection getCurrentConnection()
    {
        if(connection != null)
        {
            return connection;
        }
        else
        {
            try {
				return DriverManager.getConnection(url, userStr, password);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
        }
    }
    
    
}