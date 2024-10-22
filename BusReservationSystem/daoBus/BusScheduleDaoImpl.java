package daoBus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exceptionBus.BusException;
import modelBus.BusSchedule;
import utilityBus.ConnectionBus;

public class BusScheduleDaoImpl implements BusScheduleDao {

	@Override
	public String addBusSchedule(BusSchedule busSchedule) throws BusException {
		
	String query = "INSERT INTO bus_schedules (bus_number, route_name, departure_time, arrival_time, source_stop, destination_stop) VALUES (?, ?, ?, ?, ?, ?)";	
		
	try (Connection con = ConnectionBus.getInstance().getConnection();
            PreparedStatement ppst = con.prepareStatement(query)) {
		
		ppst.setInt(1, busSchedule.getBusNumber());
        ppst.setString(2, busSchedule.getRouteName());
        ppst.setTimestamp(3, Timestamp.valueOf(busSchedule.getDepartureTime()));
        ppst.setTimestamp(4, Timestamp.valueOf(busSchedule.getArrivalTime()));
        ppst.setString(5, busSchedule.getSourceStop());
        ppst.setString(6, busSchedule.getDestinationStop());
        ppst.executeUpdate();
		
	} catch (SQLException e) {
        e.printStackTrace();
   }	
		return query;
}

	
	@Override
	public BusSchedule getBusSchedule(int busNumber) throws BusException {
		
		String query = "SELECT * FROM bus_schedules WHERE bus_number = ?";
		BusSchedule busSchedules = null;
		
        try (Connection con = ConnectionBus.getInstance().getConnection();
                PreparedStatement ppst = con.prepareStatement(query)) {
        	
        	ppst.setInt(1, busNumber);
            try (ResultSet rs = ppst.executeQuery()) {
                if (rs.next()) {
                	 busSchedules = new BusSchedule();
                     busSchedules.setScheduleId(rs.getInt("schedule_id"));
                     busSchedules.setBusNumber(rs.getInt("bus_number"));
                     busSchedules.setRouteName(rs.getString("route_name"));
                     busSchedules.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
                     busSchedules.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
                     busSchedules.setSourceStop(rs.getString("source_stop"));
                     busSchedules.setDestinationStop(rs.getString("destination_stop"));
                }
            }
            
        } catch (SQLException e) {
        	 throw new BusException();
        }
		
		return busSchedules;
	}


	@Override
	public List<BusSchedule> getAllBusSchedules() throws BusException {
		
		String query = "SELECT * FROM bus_schedules";
        List<BusSchedule> busSchedules = new ArrayList<>();
        
        try (Connection con = ConnectionBus.getInstance().getConnection();
                PreparedStatement ppst = con.prepareStatement(query)) {
        	
        	
        	try (ResultSet rs = ppst.executeQuery()) {
                while (rs.next()) {
                	BusSchedule busSchedule = new BusSchedule();
                	busSchedule.setScheduleId(rs.getInt("schedule_id"));
                    busSchedule.setBusNumber(rs.getInt("bus_number"));
                    busSchedule.setRouteName(rs.getString("route_name"));
                    busSchedule.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
                    busSchedule.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());
                    busSchedule.setSourceStop(rs.getString("source_stop"));
                    busSchedule.setDestinationStop(rs.getString("destination_stop"));
                    busSchedules.add(busSchedule);
                }
            }
        	
        } catch (SQLException e) {
            e.printStackTrace();	
        }
		
		return busSchedules;
	}

	@Override
	public void updateBusSchedule(BusSchedule busSchedule) throws BusException {
		
		if (!busRouteExists(busSchedule.getBusNumber())) {
	        throw new BusException("The bus number " + busSchedule.getBusNumber() + " does not exist in bus routes.");
	    }

		
		String query = "UPDATE bus_schedules SET bus_number = ?, route_name = ?, departure_time = ?, arrival_time = ?, source_stop = ?, destination_stop = ? WHERE schedule_id = ?";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
                PreparedStatement ppst = con.prepareStatement(query)) {
			
			ppst.setInt(1, busSchedule.getBusNumber());
            ppst.setString(2, busSchedule.getRouteName());
            ppst.setTimestamp(3, Timestamp.valueOf(busSchedule.getDepartureTime()));
            ppst.setTimestamp(4, Timestamp.valueOf(busSchedule.getArrivalTime()));
            ppst.setString(5, busSchedule.getSourceStop());
            ppst.setString(6, busSchedule.getDestinationStop());
            ppst.setInt(7, busSchedule.getScheduleId());
            
            
            int rowsUpdated = ppst.executeUpdate();
            if (rowsUpdated == 0) {
                throw new BusException("No bus schedule found with the given bus number: " + busSchedule.getBusNumber());
            }

        } catch (SQLException e) {
            throw new BusException("Error updating bus schedule: " + e.getMessage()); // Throw BusException with message
        }
	}

	@Override
	public void deleteBusSchedule(int id) throws BusException {
		
		String query = "DELETE FROM bus_schedules WHERE schedule_id = ?";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
                PreparedStatement ppst = con.prepareStatement(query)) {
			
			ppst.setInt(1, id);
	        ppst.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();	
		}
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
	
	private boolean busRouteExists(int busNumber) {
        String query = "SELECT COUNT(*) FROM bus_routes WHERE bus_number = ?";
        try (Connection con = ConnectionBus.getInstance().getConnection();
             PreparedStatement ppst = con.prepareStatement(query)) {
            ppst.setInt(1, busNumber);
            ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            
        }
        return false; 
    }
}

	
