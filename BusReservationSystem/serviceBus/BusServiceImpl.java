package serviceBus;

import java.util.List;

import daoBus.BusDao;
import daoBus.BusDaoImpl;
import exceptionBus.BusException;
import modelBus.Bus;

public class BusServiceImpl implements BusServices {
	
	BusDao dao = new BusDaoImpl();

	@Override
	public void addBus(Bus bus) throws BusException {
		
		dao.addBus(bus);
	}

	@Override
	public Bus getBusById(int busId) throws BusException {
		
		return dao.getBusById(busId);
	}

	@Override
	public List<Bus> getAllBuses() throws BusException {
		
		return dao.getAllBuses();
	}

	@Override
	public void updateBus(Bus bus) throws BusException {
		
		dao.updateBus(bus);
	}

	@Override
	public void deleteBus(int busId) throws BusException {
		
		dao.deleteBus(busId);
	}
}
