package serviceBus;

import java.util.List;

import daoBus.BusScheduleDao;
import daoBus.BusScheduleDaoImpl;
import exceptionBus.BusException;
import modelBus.BusSchedule;

public class BusScheduleServiceImpl implements BusScheduleServices {
	
	BusScheduleDao dao = new BusScheduleDaoImpl();

	@Override
	public String addBusSchedule(BusSchedule busSchedule) throws BusException {
		String res = dao.addBusSchedule(busSchedule);
		return res;
	}

	@Override
	public BusSchedule getBusSchedule(int busNumber) throws BusException {
		
		return dao.getBusSchedule(busNumber); 
	}

	@Override
	public List<BusSchedule> getAllBusSchedules() throws BusException {
		
		return dao.getAllBusSchedules();
	}

	@Override
	public void updateBusSchedule(BusSchedule busSchedule) throws BusException {
		
		dao.updateBusSchedule(busSchedule);
		
	}

	@Override
	public void deleteBusSchedule(int id) throws BusException {
		
		dao.deleteBusSchedule(id);
		
	}
	
    public List<BusSchedule> searchBusSchedule(int busNumber , String routeName ) throws BusException {
		
		return dao.searchBusSchedule(busNumber, routeName);
	}
}
