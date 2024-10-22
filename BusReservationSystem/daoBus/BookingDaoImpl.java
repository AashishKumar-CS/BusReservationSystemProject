package daoBus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptionBus.BusException;
import modelBus.Booking;
import utilityBus.ConnectionBus;

public class BookingDaoImpl implements BookingDao {

	@Override
	public void BookTicket(Booking booking) throws BusException {
		
		String checkBookingQuery = "SELECT COUNT(*) FROM bookings WHERE bus_number = ? AND travel_date = ? AND seat_number = ?";
			
		String insertquery = "INSERT INTO bookings (user_id, schedule_id, bus_number, route_name, " +
                "passenger_name, passenger_age, passenger_contact, travel_date, seat_preference, " +
                "seat_number, booking_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		//String updateStatusquery = "UPDATE bookings SET booking_status = 'BOOKED' WHERE booking_id = ?";
		
		String updatequery = "UPDATE buses SET available_seats = available_seats - 1 WHERE bus_number = ? AND available_seats > 0";
		
		Connection con = null;	
		
		try { con = ConnectionBus.getInstance().getConnection();
				con.setAutoCommit(false);
				
				
				// Check for existing bookings
		        try (PreparedStatement checkPpst = con.prepareStatement(checkBookingQuery)) {
		            checkPpst.setInt(1, booking.getBusNumber());
		            checkPpst.setDate(2, booking.getTravelDate());
		            checkPpst.setInt(3, booking.getSeatNumber());
		            ResultSet rs = checkPpst.executeQuery();
		            if (rs.next() && rs.getInt(1) > 0) {
		                throw new BusException("The seat is already booked for this bus on the specified travel date.");
		            }
		        }
		        
				
	        try( PreparedStatement ppst = con.prepareStatement(insertquery)){
			
			ppst.setInt(1, booking.getUserId());
            ppst.setInt(2, booking.getScheduleId());
            ppst.setInt(3, booking.getBusNumber());
            ppst.setString(4, booking.getRouteName());
            ppst.setString(5, booking.getPassengerName());
            ppst.setInt(6, booking.getPassengerAge());
            ppst.setString(7, booking.getPassengerContact());
            ppst.setDate(8, booking.getTravelDate());
            ppst.setString(9, booking.getSeatPreference());
            ppst.setInt(10, booking.getSeatNumber());
            ppst.setString(11, "BOOKED");

            int rowsInserted = ppst.executeUpdate();
            
            if (rowsInserted == 0) {
                throw new BusException("Failed to insert booking record.");
            }
        }
			
	         try(PreparedStatement ppst = con.prepareStatement(updatequery)){
			ppst.setInt(1, booking.getBusNumber());
			int rowsUpdated = ppst.executeUpdate();
		
			if (rowsUpdated == 0) {
                throw new BusException("No available seats to update. Booking failed.");
            }
        }
	         
	      // Commit the transaction
	         con.commit();
	         System.out.println("Ticket booked successfully.");
	     } catch (SQLException e) {
	         // Roll back the transaction in case of an error
	             if (con != null) {
	            	 try{
	                 con.rollback();
	         } catch (SQLException rollbackEx) {
	             throw new BusException("Failed to roll back transaction: " + rollbackEx.getMessage());
	         } 
	     }
	         throw new BusException("Failed to book ticket: " + e.getMessage());
	  }  finally {
	        // Ensure the connection is closed
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException closeEx) {
	                throw new BusException("Failed to close connection: " + closeEx.getMessage());
	            }
	        }
	    }   	
  }
		

	@Override
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

	@Override
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
	
	
	@Override
	public void CancelTicket(int bookingId) throws BusException {
		
		String updateSql = "UPDATE bookings SET booking_status = 'CANCELLED' WHERE booking_id = ?";
		
		String updateBusSql = "UPDATE buses SET available_seats = available_seats + 1 WHERE bus_number = ?";
		
		Connection con = null;
		
		try{con = ConnectionBus.getInstance().getConnection();
			con.setAutoCommit(false);
			
            // Get booking details
            Booking booking = getBooking(bookingId);
            if (booking == null) {
                throw new BusException("Booking not found");
            }

            // Update booking status
            try (PreparedStatement ppst = con.prepareStatement(updateSql)){
   	             
                ppst.setInt(1, bookingId);
                int rowsAffected = ppst.executeUpdate();
                if (rowsAffected == 0) {
                    throw new BusException("Failed to update booking status. Booking may not exist.");
                }
            }

            // Update available seats
            try (PreparedStatement ppst = con.prepareStatement(updateBusSql)){
   	             
                ppst.setInt(1, booking.getBusNumber());
                int rowsAffected = ppst.executeUpdate();
                if (rowsAffected == 0) {
                    throw new BusException("Failed to update available seats. Bus may not exist.");
                }
            }
            
            con.commit();
            System.out.println("Ticket cancelled successfully.");
            
        } catch (SQLException e) {
        	try{
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException rollbackEx) {
                throw new BusException("Failed to roll back transaction: " + rollbackEx.getMessage());
            }
            throw new BusException("Failed to cancel ticket: " + e.getMessage());
        }
   }

	
	// Assign seat number based on seat preference
    public int assignSeatNumber(int busNumber, String seatPreference) throws BusException {
    	String sql = "SELECT total_seats, available_seats FROM buses WHERE bus_number = ?";
        
        try (Connection con = ConnectionBus.getInstance().getConnection();
  	             PreparedStatement ppst = con.prepareStatement(sql)){   
        	
        	ppst.setInt(1, busNumber);
            ResultSet rs = ppst.executeQuery();

            if (rs.next()) {
                int totalSeats = rs.getInt("total_seats");
                int availableSeats = rs.getInt("available_seats");
        	
                
                if (availableSeats > 0) {
                    // Assign seat number based on seat preference
                    int seatNumber;
                    if (seatPreference.equalsIgnoreCase("Window")) {
                        seatNumber = totalSeats - availableSeats + 1; // Assign window seat
                    } else if (seatPreference.equalsIgnoreCase("Aisle")) {
                        seatNumber =  totalSeats - availableSeats + 2; // Assign aisle seat (next available)
                    } else {
                        throw new BusException("Invalid seat preference");
                    }
                    return seatNumber;
                } else {
                    throw new BusException("No available seats");
                }
            } else {
                throw new BusException("Bus not found");
            }
        } catch (SQLException e) {
            throw new BusException("Failed to assign seat number: " + e.getMessage());
        } 
 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}