package daoBus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptionBus.BusException;
import modelBus.Bus;
import utilityBus.ConnectionBus;

public class BusDaoImpl implements BusDao {

	@Override
	public void addBus(Bus bus) throws BusException {
		
		String query = "INSERT INTO buses (bus_number, bus_type, total_seats, available_seats) VALUES (?, ?, ?, ?)";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
			ppst.setInt(1, bus.getBusNumber());
			ppst.setString(2, bus.getBusType());
			ppst.setInt(3, bus.getTotalSeats());
			ppst.setInt(4, bus.getAvailableSeats());
			ppst.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }	
  }

	@Override
	public Bus getBusById(int busId) throws BusException {
		
		String query = "SELECT * FROM buses WHERE bus_id = ?";
        Bus bus = null;
        
        try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
        	
        	ppst.setInt(1, busId);
        	ResultSet rs = ppst.executeQuery();
            if (rs.next()) {
                bus = new Bus();
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusNumber(rs.getInt("bus_number"));
                bus.setBusType(rs.getString("bus_type"));
                bus.setTotalSeats(rs.getInt("total_seats"));
                bus.setAvailableSeats(rs.getInt("available_seats"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
			
        return bus;
	}

	@Override
	public List<Bus> getAllBuses() throws BusException {
		
		List<Bus> buses = new ArrayList<>();
        String query = "SELECT * FROM buses";
        
        try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
        	
        	ResultSet rs = ppst.executeQuery(query);
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setBusId(rs.getInt("bus_id"));
                bus.setBusNumber(rs.getInt("bus_number"));
                bus.setBusType(rs.getString("bus_type"));
                bus.setTotalSeats(rs.getInt("total_seats"));
                bus.setAvailableSeats(rs.getInt("available_seats"));
                buses.add(bus);
        }
        
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
    return buses;
}

	@Override
	public void updateBus(Bus bus) throws BusException {
		
		String query = "UPDATE buses SET bus_type = ?, available_seats = ? WHERE bus_id = ?";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
			//ppst.setInt(1, bus.getBusNumber());
			ppst.setString(1, bus.getBusType());
			//ppst.setInt(3, bus.getTotalSeats());
			ppst.setInt(2, bus.getAvailableSeats());
			ppst.setInt(3, bus.getBusId());
			ppst.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }	
  }

	@Override
	public void deleteBus(int busId) throws BusException {
		
		String query = "DELETE FROM buses WHERE bus_id = ?";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
			ppst.setInt(1, busId);
			ppst.executeUpdate();
			
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
}
