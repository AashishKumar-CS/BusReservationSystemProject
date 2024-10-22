package modelBus;

public class Bus {
	
	private int busId;
	private int busNumber;
	private String busType;
	private int totalSeats;
	private int availableSeats;
	
	
	
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public int getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	
	
	public Bus(int busId, int busNumber, String busType, int totalSeats, int availableSeats) {
		super();
		this.busId = busId;
		this.busNumber = busNumber;
		this.busType = busType;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
	}
	
	
	public Bus() {}
	
	
	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busNumber=" + busNumber + ", busType=" + busType + ", totalSeats="
				+ totalSeats + ", availableSeats=" + availableSeats + "]";
	}
}
