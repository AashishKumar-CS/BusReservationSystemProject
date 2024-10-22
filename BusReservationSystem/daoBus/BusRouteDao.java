package daoBus;

import java.util.List;

import exceptionBus.BusException;
import modelBus.BusRoute;

public interface BusRouteDao {
	
	String addBusRoute(BusRoute busRoute) throws BusException;
	
	BusRoute getBusRoute(int id) throws BusException;
	
	List<BusRoute> getAllBusRoutes() throws BusException;
	
	void updateBusRoute(BusRoute busRoute) throws BusException;
	
	void deleteBusRoute(int id) throws BusException;
	
	List<BusRoute> searchBusRoute(int busNumber , String routeName ) throws BusException;

}

