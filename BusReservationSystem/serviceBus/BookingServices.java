package serviceBus;

import java.util.List;

import exceptionBus.BusException;
import modelBus.Booking;

public interface BookingServices {
	

	void BookTicket(Booking booking) throws BusException;
	
	Booking getBooking(int bookingId) throws BusException;
	
	List<Booking> getAllBookings() throws BusException;
	
	void CancelTicket(int bookingId) throws BusException;
	
	int assignSeatNumber(int busNumber, String seatPreference) throws BusException;

}
