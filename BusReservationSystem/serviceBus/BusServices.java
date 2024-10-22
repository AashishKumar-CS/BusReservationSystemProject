package serviceBus;

import java.util.List;

import exceptionBus.BusException;
import modelBus.Bus;

public interface BusServices {
	
	
void addBus(Bus bus) throws BusException;
	
	Bus getBusById(int busId) throws BusException;
	
	List<Bus> getAllBuses() throws BusException;
	
	void updateBus(Bus bus) throws BusException;
	
    void deleteBus(int busId) throws BusException;

}
