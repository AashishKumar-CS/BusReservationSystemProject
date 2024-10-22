package serviceBus;

import java.util.List;

import exceptionBus.BusException;
import modelBus.Admin;
import modelBus.Booking;
import modelBus.User;

public interface AdminServices {
	
    List<Admin> getAllAdmins() throws BusException;
	
    Admin getAdminById(int id) throws BusException;
    
    boolean registerAdmin(Admin admin) throws BusException;
    
    void updateAdmin(Admin admin) throws BusException;
    
    void deleteAdmin(int id) throws BusException;
    
    User getUser(int id) throws BusException;
    
    List<User> getAllUsers() throws BusException;
    
    void deleteUser(int id) throws BusException;

	boolean login(String username, String password);
	
	 Booking getBooking(int bookingId) throws BusException;
		
     List<Booking> getAllBookings() throws BusException;

}
