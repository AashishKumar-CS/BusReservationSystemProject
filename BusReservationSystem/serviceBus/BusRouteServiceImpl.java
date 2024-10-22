package serviceBus;

import java.util.List;

import daoBus.BusRouteDao;
import daoBus.BusRouteDaoImpl;
import exceptionBus.BusException;
import modelBus.BusRoute;

public class BusRouteServiceImpl implements BusRouteServices {
	
	BusRouteDao dao = new BusRouteDaoImpl();

	@Override
	public String addBusRoute(BusRoute busRoute) throws BusException {
		String res = dao.addBusRoute(busRoute);
		return res;
	}

	@Override
	public BusRoute getBusRoute(int id) throws BusException {
		
		return dao.getBusRoute(id);
	}

	@Override
	public List<BusRoute> getAllBusRoutes() throws BusException {
		
		return dao.getAllBusRoutes();
	}

	@Override
	public void updateBusRoute(BusRoute busRoute) throws BusException {
		dao.updateBusRoute(busRoute);
		
	}

	@Override
	public void deleteBusRoute(int id) throws BusException {
		dao.deleteBusRoute(id);
		
	}
	
	public List<BusRoute> searchBusRoute(int busNumber , String routeName ) throws BusException {
		
		return dao.searchBusRoute(busNumber, routeName);
	}
}
