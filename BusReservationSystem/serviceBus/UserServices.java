package serviceBus;


import java.util.List;

import exceptionBus.BusException;
import modelBus.BusRoute;
import modelBus.BusSchedule;
import modelBus.User;

public interface UserServices {
	
	boolean registerUser(User user) throws BusException;
	
    User getUser(int id) throws BusException;
    
    void updateUser(User user) throws BusException;
    
    void deleteUser(int id) throws BusException;
    
    boolean isUsernameAvailable(String username) throws BusException;
    
    boolean isEmailAvailable(String email) throws BusException;
    
    boolean login(String username , String password) throws BusException;
    
    List<BusRoute> searchBusRoute(int busNumber , String routeName ) throws BusException;
    
    List<BusSchedule> searchBusSchedule(int busNumber , String routeName ) throws BusException;
	   
}
	
	
