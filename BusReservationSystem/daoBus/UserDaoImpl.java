package daoBus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptionBus.BusException;
import modelBus.BusRoute;
import modelBus.BusSchedule;
import modelBus.User;
import utilityBus.ConnectionBus;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean registerUser(User user) throws BusException {
		
		String query = "INSERT INTO users (username, password, email, contactNumber ) VALUES (?, ?, ?, ?)";
		
        try (Connection con = ConnectionBus.getInstance().getConnection();
             PreparedStatement ppst = con.prepareStatement(query)) {

            ppst.setString(1, user.getUsername());
            ppst.setString(2, user.getPassword());
            ppst.setString(3, user.getEmail());
            ppst.setString(4, user.getContactNumber());
            ppst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusException("Failed to register user: " + e.getMessage());
        }
		return true;  	
	}

	@Override
	public User getUser(int id) throws BusException {
		
		String query = "SELECT * FROM users WHERE user_id = ?";
        User user = null;
        
        try (Connection con = ConnectionBus.getInstance().getConnection();
                PreparedStatement ppst = con.prepareStatement(query)) {

               ppst.setInt(1, id);
               try (ResultSet rs = ppst.executeQuery()) {

                   if (rs.next()) {
                       user = new User();
                       user.setId(rs.getInt("user_id"));
                       user.setUsername(rs.getString("username"));
                       user.setPassword(rs.getString("password"));
                       user.setEmail(rs.getString("email"));
                       user.setContactNumber(rs.getString("ContactNumber"));
                   }

               }

           } catch (SQLException e) {
               e.printStackTrace();
           }
		
		return user;
	}

	
	@Override
	public void updateUser(User user) throws BusException {
		
		String query = "UPDATE users SET username = ?, password = ?, email = ?, contactNumber = ? WHERE user_id = ?";

        try (Connection con = ConnectionBus.getInstance().getConnection();
             PreparedStatement ppst = con.prepareStatement(query)) {

            ppst.setString(1, user.getUsername());
            ppst.setString(2, user.getPassword());
            ppst.setString(3, user.getEmail());
            ppst.setInt(4, user.getId());
            ppst.setString(5, user.getContactNumber());
            
            ppst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	
	@Override
	public void deleteUser(int id) throws BusException {
		
		String query = "DELETE FROM users WHERE user_id = ?";

        try (Connection con = ConnectionBus.getInstance().getConnection();
             PreparedStatement ppst = con.prepareStatement(query)) {

            ppst.setInt(1, id);
            ppst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
	}
	
	
   public boolean login(String username, String password) throws BusException {
    	
	        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
	        
	        try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
	            
	            ppst.setString(1, username);
	            ppst.setString(2, password);
	            ResultSet rs = ppst.executeQuery();
	            
	            if (rs.next()) {
	                return rs.getInt(1) > 0; // Return true if a matching user is found
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new BusException("Login failed: " + e.getMessage());
	        }
	        
	        return false; // Return false if no matching user is found
	    }
	   
	public List<BusRoute> searchBusRoute(int busNumber , String routeName ) throws BusException {
		
		List<BusRoute> matchingRoutes = new ArrayList<>();
		String query = "SELECT * FROM bus_routes WHERE busNumber = ? AND route_name LIKE ?" ;
		
		
		 try (Connection con = ConnectionBus.getInstance().getConnection();
		      		PreparedStatement ppst = con.prepareStatement(query)) {
			 
			 ppst.setInt(1, busNumber);
	         ppst.setString(2, "%" + routeName + "%");
	         
	         try(ResultSet rs = ppst.executeQuery()){
	         
	         while (rs.next()) {
	             BusRoute route = new BusRoute();
	             route.setId(rs.getInt("id"));
	             route.setBusNumber(rs.getInt("busNumber"));
	             route.setRouteName(rs.getString("route_name"));
	             route.setSource(rs.getString("source"));
	             route.setDestination(rs.getString("destination"));
	             // Set other properties as needed
	             matchingRoutes.add(route);   
	         }
	       }
	         
	     } catch (SQLException e) {
	         throw new BusException("Error searching for bus routes: " + e.getMessage());
	     }
		 
	     return matchingRoutes;
	  }
	
       public List<BusSchedule> searchBusSchedule(int busNumber , String routeName ) throws BusException {
		
		List<BusSchedule> matchingSchedules = new ArrayList<>();
		String query = "SELECT * FROM bus_schedules WHERE busNumber = ? AND route_name LIKE ?" ;
		
		
		 try (Connection con = ConnectionBus.getInstance().getConnection();
		      		PreparedStatement ppst = con.prepareStatement(query)) {
			 
			 ppst.setInt(1, busNumber);
	         ppst.setString(2, "%" + routeName + "%");
	         
	         try(ResultSet rs = ppst.executeQuery()){
	         
	         while (rs.next()) {
	             BusSchedule schedule = new BusSchedule();
	             schedule.setScheduleId(rs.getInt("schedule_id")); // Corrected field access
                 schedule.setBusNumber(rs.getInt("bus_number"));
                 schedule.setRouteName(rs.getString("route_name"));
                 schedule.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
                 schedule.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
                 schedule.setSourceStop(rs.getString("source_stop"));
                 schedule.setDestinationStop(rs.getString("destination_stop"));
	             
	             matchingSchedules.add(schedule);   
	         }
	       }
	         
	     } catch (SQLException e) {
	         throw new BusException("Error searching for bus routes: " + e.getMessage());
	     }
		 
	     return matchingSchedules;
	  }
}
