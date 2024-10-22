package daoBus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import exceptionBus.BusException;
import modelBus.BusRoute;
import utilityBus.ConnectionBus;


public class BusRouteDaoImpl implements BusRouteDao {

	@Override
	public String addBusRoute(BusRoute busRoute) throws BusException {
		
		String query = "INSERT INTO bus_routes (bus_number, route_name, departure_time, source_stop, destination_stop) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	             PreparedStatement ppst = con.prepareStatement(query)) {
			
			    ppst.setInt(1, busRoute.getBusNumber());
			    ppst.setString(2, busRoute.getRouteName());
			    
			    LocalTime localTime = busRoute.getDepartureTime();
		        java.sql.Time time = java.sql.Time.valueOf(localTime);
		        ppst.setTime(3, time);
			    
	            ppst.setString(4, busRoute.getSource());
	            ppst.setString(5, busRoute.getDestination());
	            ppst.executeUpdate();
	            
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
		}	
		return query;
	}

	@Override
	public BusRoute getBusRoute(int id) throws BusException {
		
		String query = "SELECT * FROM bus_routes WHERE bus_number = ?";
		BusRoute busRoute = null;
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
				PreparedStatement ppst = con.prepareStatement(query)) {
			
			
			ppst.setInt(1, id);
			
			try (ResultSet rs = ppst.executeQuery()) {
                if (rs.next()) {
                    busRoute = new BusRoute();
                    busRoute.setId(rs.getInt("route_id"));
                    busRoute.setBusNumber(rs.getInt("bus_number"));
                    busRoute.setRouteName(rs.getString("route_name"));
                    
                    Time departureTime = rs.getTime("departure_time"); 
                    if (departureTime != null) {
                        busRoute.setDepartureTime(departureTime.toLocalTime()); // Convert to LocalTime
                    }
                    
                    busRoute.setSource(rs.getString("source_stop"));
                    busRoute.setDestination(rs.getString("destination_stop"));
                }
            }
			
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        return busRoute;
	}

	@Override
	public List<BusRoute> getAllBusRoutes() throws BusException {
		
		String query = "SELECT * FROM bus_routes";
        List<BusRoute> busRoutes = new ArrayList<>();
        try (Connection con = ConnectionBus.getInstance().getConnection();
        		PreparedStatement ppst = con.prepareStatement(query)) {
           
        	try (ResultSet rs = ppst.executeQuery()) {
                while (rs.next()) {
                    BusRoute busRoute = new BusRoute();
                    busRoute.setId(rs.getInt("route_id"));
                    busRoute.setBusNumber(rs.getInt("bus_number"));
                    busRoute.setRouteName(rs.getString("route_name"));
                    
                    
                    Time departureTime = rs.getTime("departure_time");
                    if (departureTime != null) {
                        busRoute.setDepartureTime(departureTime.toLocalTime()); 
                    }

                    busRoute.setSource(rs.getString("source_stop"));
                    busRoute.setDestination(rs.getString("destination_stop"));
                    busRoutes.add(busRoute);
                }
            }
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
		return busRoutes ;
	}

	@Override
	public void updateBusRoute(BusRoute busRoute) throws BusException {
		
	  String query = "UPDATE bus_routes SET bus_number = ?, route_name = ?, departure_time = ?, source_stop = ?, destination_stop = ? WHERE bus_number = ?";
	  
	  try (Connection con = ConnectionBus.getInstance().getConnection();
      		PreparedStatement ppst = con.prepareStatement(query)) {
		  
		  ppst.setInt(1, busRoute.getBusNumber());
		  ppst.setString(2, busRoute.getRouteName());
		  
		  
		  LocalTime localTime = busRoute.getDepartureTime();
	        java.sql.Time time = java.sql.Time.valueOf(localTime);
	        ppst.setTime(3, time);
		  
		  
          ppst.setString(4, busRoute.getSource());
          ppst.setString(5, busRoute.getDestination());
          ppst.setInt(6, busRoute.getId());
          ppst.executeUpdate();
          
      } catch (SQLException e) {
          e.printStackTrace();
      }	  	  
 }

	@Override
	public void deleteBusRoute(int id) throws BusException {
		
		String query = "DELETE FROM bus_routes WHERE route_id = ?";
		
		try (Connection con = ConnectionBus.getInstance().getConnection();
	      		PreparedStatement ppst = con.prepareStatement(query)) {
			
			 ppst.setInt(1, id);
	            ppst.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
		}
	}
	
	public List<BusRoute> searchBusRoute(int busNumber , String routeName ) throws BusException {
	
	List<BusRoute> matchingRoutes = new ArrayList<>();
	String query = "SELECT * FROM bus_routes WHERE bus_number = ? AND route_name LIKE ?" ;
	
	
	 try (Connection con = ConnectionBus.getInstance().getConnection();
	      		PreparedStatement ppst = con.prepareStatement(query)) {
		 
		 ppst.setInt(1, busNumber);
         ppst.setString(2, "%" + routeName + "%");
         
         try(ResultSet rs = ppst.executeQuery()){
         
         while (rs.next()) {
             BusRoute route = new BusRoute();
             route.setId(rs.getInt("route_id"));
             route.setBusNumber(rs.getInt("bus_number"));
             route.setRouteName(rs.getString("route_name"));
             
             Time departureTime = rs.getTime("departure_time");
             if (departureTime != null) {
                 route.setDepartureTime(departureTime.toLocalTime()); 
             }
             
             route.setSource(rs.getString("source_stop"));
             route.setDestination(rs.getString("destination_stop"));
             // Set other properties as needed
             matchingRoutes.add(route);   
         }
       }
         
     } catch (SQLException e) {
         throw new BusException("Error searching for bus routes: " + e.getMessage());
     }
	 
     return matchingRoutes;
  }

}
