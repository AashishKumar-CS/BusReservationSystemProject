package modelBus;

import java.sql.Date;
import java.sql.Timestamp;

public class Booking {
	
	private int bookingId;
    private int userId;
    private int scheduleId;
    private int busNumber;
    private String routeName;
    private String passengerName;
    private int passengerAge;
    private String passengerContact;
    private java.sql.Date travelDate;
    private String seatPreference;
    private int seatNumber;
    private String bookingStatus;
    private java.sql.Timestamp bookingDate;
    
    
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public int getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getPassengerAge() {
		return passengerAge;
	}
	public void setPassengerAge(int passengerAge) {
		this.passengerAge = passengerAge;
	}
	public String getPassengerContact() {
		return passengerContact;
	}
	public void setPassengerContact(String passengerContact) {
		this.passengerContact = passengerContact;
	}
	public java.sql.Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(java.sql.Date travelDate) {
		this.travelDate = travelDate;
	}
	public String getSeatPreference() {
		return seatPreference;
	}
	public void setSeatPreference(String seatPreference) {
		this.seatPreference = seatPreference;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public java.sql.Timestamp getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(java.sql.Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	
	public Booking(int bookingId, int userId, int scheduleId, int busNumber, String routeName, String passengerName,
			int passengerAge, String passengerContact, Date travelDate, String seatPreference, int seatNumber,
			String bookingStatus, Timestamp bookingDate) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.scheduleId = scheduleId;
		this.busNumber = busNumber;
		this.routeName = routeName;
		this.passengerName = passengerName;
		this.passengerAge = passengerAge;
		this.passengerContact = passengerContact;
		this.travelDate = travelDate;
		this.seatPreference = seatPreference;
		this.seatNumber = seatNumber;
		this.bookingStatus = bookingStatus;
		this.bookingDate = bookingDate;
	}
    
    
	public Booking() {}
	@Override
	
	
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", userId=" + userId + ", scheduleId=" + scheduleId + ", busNumber="
				+ busNumber + ", routeName=" + routeName + ", passengerName=" + passengerName + ", passengerAge="
				+ passengerAge + ", passengerContact=" + passengerContact + ", travelDate=" + travelDate
				+ ", seatPreference=" + seatPreference + ", seatNumber=" + seatNumber + ", bookingStatus="
				+ bookingStatus + ", bookingDate=" + bookingDate + "]";
	}
}