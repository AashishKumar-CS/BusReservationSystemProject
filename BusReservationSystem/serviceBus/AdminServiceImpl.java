package serviceBus;



import java.util.List;

import daoBus.AdminDao;
import daoBus.AdminDaoImpl;
import exceptionBus.BusException;
import modelBus.Admin;
import modelBus.Booking;
import modelBus.User;


public class AdminServiceImpl implements AdminServices {
	
	AdminDao dao = new AdminDaoImpl();

	@Override
	public List<Admin> getAllAdmins() throws BusException {
		
		return dao.getAllAdmins();
	}

	@Override
	public Admin getAdminById(int id) throws BusException {
		
		return dao.getAdminById(id);
	}

	@Override
	public boolean registerAdmin(Admin admin) throws BusException {
		 
		return dao.registerAdmin(admin);
		
	}

	@Override
	public void updateAdmin(Admin admin) throws BusException {
		
		dao.updateAdmin(admin);
		
	}

	@Override
	public void deleteAdmin(int id) throws BusException {
		
		dao.deleteAdmin(id);
		
	}

	@Override
	public User getUser(int id) throws BusException {
		
		return dao.getUser(id);
	}

	@Override
	public List<User> getAllUsers() throws BusException {
		
		return dao.getAllUsers();
	}

	@Override
	public void deleteUser(int id) throws BusException {
		
		dao.deleteUser(id);
		
	}
	
	
	
	public boolean login(String username, String password) throws BusException {
		
		return dao.login(username, password);
	}
	
    public Booking getBooking(int bookingId) throws BusException {
		
		return dao.getBooking(bookingId);
	}

	public List<Booking> getAllBookings() throws BusException {
		
		return dao.getAllBookings();
	}
}
