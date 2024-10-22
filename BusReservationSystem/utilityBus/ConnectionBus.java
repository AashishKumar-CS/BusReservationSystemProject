package utilityBus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


   public class ConnectionBus {
	
      public static ConnectionBus obj = null ;
	
	private ConnectionBus() {

      }
	
	   public Connection getConnection() {
            
		   Connection con = null;
		
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/busApp","root", "sqlAsh@06");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	public static ConnectionBus getInstance() {
		
		
		if(obj == null) {
			
			obj = new ConnectionBus();
		}
		return obj;
	}
}

