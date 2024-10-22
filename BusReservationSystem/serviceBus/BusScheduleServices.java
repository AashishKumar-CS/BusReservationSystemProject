package serviceBus;

import java.util.List;

import exceptionBus.BusException;
import modelBus.BusSchedule;

public interface BusScheduleServices {
	
	
String addBusSchedule(BusSchedule busSchedule) throws BusException;
	
	BusSchedule getBusSchedule(int busNumber) throws BusException;
	
	List<BusSchedule> getAllBusSchedules() throws BusException;
	
	void updateBusSchedule(BusSchedule busSchedule) throws BusException;
	
	void deleteBusSchedule(int id) throws BusException;
	
	List<BusSchedule> searchBusSchedule(int busNumber , String routeName ) throws BusException;

}
