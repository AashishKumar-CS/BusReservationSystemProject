package daoBus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptionBus.BusException;
import modelBus.Admin;
import modelBus.Booking;
import modelBus.User;
import utilityBus.ConnectionBus;

public class AdminDaoImpl implements AdminDao {

	@Override
	public List<Admin> getAllAdmins() throws BusException {
		
        List<Admin> admins = new ArrayList<>();
		
		String query = "SELECT * FROM Admin";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
			ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setEmail(rs.getString("email"));
                admins.add(admin);
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
		
		return admins;

	}

	@Override
	public Admin getAdminById(int id) throws BusException {
		
		String query = "SELECT * FROM Admin WHERE id = ?";
		 Admin admin = null;
		 
		 try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
			 ppst.setInt(1, id);
	            ResultSet rs = ppst.executeQuery();
	            if (rs.next()) {
	                admin = new Admin();
	                admin.setId(rs.getInt("id"));
	                admin.setUsername(rs.getString("username"));
	                admin.setPassword(rs.getString("password"));
	                admin.setEmail(rs.getString("email"));
	                
	            }
	            
	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	        return admin;     
	}

	@Override
	public boolean registerAdmin(Admin admin) throws BusException {
		
		String query = "INSERT INTO Admin (username, password, email) VALUES (?, ?, ?)";
		 
		 try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
	            ppst.setString(1, admin.getUsername());
	            ppst.setString(2, admin.getPassword());
	            ppst.setString(3, admin.getEmail());
	            ppst.executeUpdate();
	            
	        } catch (SQLException e) {
	            System.out.println("Error: " + e.getMessage());
	            throw new BusException("Failed to register admin: " + e.getMessage());
	        }
		 
		 return true;
	}

	@Override
	public void updateAdmin(Admin admin) throws BusException {
		
      String query = "UPDATE Admin SET username = ?, password = ?, email = ? WHERE id = ?";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
			ppst.setString(1, admin.getUsername());
            ppst.setString(2, admin.getPassword());
            ppst.setString(3, admin.getEmail());
            ppst.setInt(4, admin.getId());
            ppst.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

		
	}

	@Override
	public void deleteAdmin(int id) throws BusException {
		
        String query = "DELETE FROM Admin WHERE id = ?";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
			ppst.setInt(1, id);
            ppst.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
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
	public List<User> getAllUsers() throws BusException {
		
		List<User> users = new ArrayList<>();
		
		String query = "SELECT * FROM users";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
                PreparedStatement ppst = con.prepareStatement(query)) {
			
			ResultSet rs = ppst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setContactNumber(rs.getString("contactNumber"));
                users.add(user);
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return users;
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
		
     String query = "SELECT COUNT(*) FROM Admin WHERE username = ? AND password = ?";
        
        try (Connection con = ConnectionBus.getInstance().getConnection();
             PreparedStatement ppst = con.prepareStatement(query)) {
            
            ppst.setString(1, username);
            ppst.setString(2, password);
            ResultSet rs = ppst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // Return true if a matching admin is found
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            throw new BusException("Login failed: " + e.getMessage());
        }
        
        return false; // Return false if no matching admin is found
    }
    
     public Booking getBooking(int bookingId) throws BusException {
		
		String query = "SELECT * FROM bookings WHERE booking_id = ?";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)){
			
			ppst.setInt(1, bookingId);
            ResultSet rs = ppst.executeQuery();
            
            
            if (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setScheduleId(rs.getInt("schedule_id"));
                booking.setBusNumber(rs.getInt("bus_number"));
                booking.setRouteName(rs.getString("route_name"));
                booking.setPassengerName(rs.getString("passenger_name"));
                booking.setPassengerAge(rs.getInt("passenger_age"));
                booking.setPassengerContact(rs.getString("passenger_contact"));
                booking.setTravelDate(rs.getDate("travel_date"));
                booking.setSeatPreference(rs.getString("seat_preference"));
                booking.setSeatNumber(rs.getInt("seat_number"));
                booking.setBookingStatus(rs.getString("booking_status"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                return booking;
            } else {
            	return null;
            }
	} catch (SQLException e) {
        throw new BusException("Failed to retrieve booking");
    }
}
     
     public List<Booking> getAllBookings() throws BusException {
 		
 		String query = "SELECT * FROM bookings";
 		
 		try (Connection con = ConnectionBus.getInstance().getConnection();
 	             PreparedStatement ppst = con.prepareStatement(query)){
 			
 			ResultSet rs = ppst.executeQuery(query);
             List<Booking> bookings = new ArrayList<>();
 			
             
             while (rs.next()) {
                 Booking booking = new Booking();
                 booking.setBookingId(rs.getInt("booking_id"));
                 booking.setUserId(rs.getInt("user_id"));
                 booking.setScheduleId(rs.getInt("schedule_id"));
                 booking.setBusNumber(rs.getInt("bus_number"));
                 booking.setRouteName(rs.getString("route_name"));
                 booking.setPassengerName(rs.getString("passenger_name"));
                 booking.setPassengerAge(rs.getInt("passenger_age"));
                 booking.setPassengerContact(rs.getString("passenger_contact"));
                 booking.setTravelDate(rs.getDate("travel_date"));
                 booking.setSeatPreference(rs.getString("seat_preference"));
                 booking.setSeatNumber(rs.getInt("seat_number"));
                 booking.setBookingStatus(rs.getString("booking_status"));
                 booking.setBookingDate(rs.getTimestamp("booking_date"));
                 bookings.add(booking);
             }
             
             return bookings;
             
         } catch (SQLException e) {
             throw new BusException("Failed to retrieve all bookings");
    }	
 }
     
}