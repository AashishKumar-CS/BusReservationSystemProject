package serviceBus;

import java.util.List;

import daoBus.BookingDao;
import daoBus.BookingDaoImpl;
import exceptionBus.BusException;
import modelBus.Booking;

public class BookingServiceImpl implements BookingServices {
	
	BookingDao dao = new BookingDaoImpl();

	@Override
	public void BookTicket(Booking booking) throws BusException {
		
		dao.BookTicket(booking);
		
	}

	@Override
	public Booking getBooking(int bookingId) throws BusException {
		
		return dao.getBooking(bookingId);
	}

	@Override
	public List<Booking> getAllBookings() throws BusException {
		
		return dao.getAllBookings();
	}

	@Override
	public void CancelTicket(int bookingId) throws BusException {
		
		dao.CancelTicket(bookingId);
		
	}
	
	public int assignSeatNumber(int busNumber, String seatPreference) throws BusException {
		
		return dao.assignSeatNumber(busNumber, seatPreference);
		
	}
}