package serviceBus;

import java.util.List;

import daoBus.UserDao;
import modelBus.BusRoute;
import modelBus.BusSchedule;
import modelBus.User;
import daoBus.UserDaoImpl;
import exceptionBus.BusException;

public class UserServiceImpl implements UserServices {
	
	UserDao dao = new UserDaoImpl();
	
	

	@Override
	public boolean registerUser(User user) throws BusException {
		
		return dao.registerUser(user);
		
	}

	@Override
	public User getUser(int id) throws BusException {
		
		return dao.getUser(id);
	}

	@Override
	public void updateUser(User user) throws BusException {
		dao.updateUser(user);

	}

	@Override
	public void deleteUser(int id) throws BusException {
		dao.deleteUser(id);

	}

	@Override
	public boolean isUsernameAvailable(String username)throws BusException {
		return false;
	}

	@Override
	public boolean isEmailAvailable(String email)throws BusException {
		// TODO Auto-generated method stub
		return false;
	}
	
    public boolean login(String username, String password) throws BusException {
    	
		return dao.login(username, password);
	}

      public List<BusRoute> searchBusRoute(int busNumber , String routeName ) throws BusException {
		
		return dao.searchBusRoute(busNumber, routeName);
	}
    
      public List<BusSchedule> searchBusSchedule(int busNumber , String routeName ) throws BusException {
  		
  		return dao.searchBusSchedule(busNumber, routeName);
  }
}
