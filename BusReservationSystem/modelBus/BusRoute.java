package modelBus;


import java.time.LocalTime;

public class BusRoute {
	
	private int id;
	private int busNumber;
	private String routeName;
    private LocalTime departureTime;
	private String source;
	private String destination;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public int getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}
	public LocalTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}
	
	
	public BusRoute(int id, int busNumber, String routeName, LocalTime departureTime, String source, String destination) {
		super();
		this.id = id;
		this.busNumber = busNumber;
		this.routeName = routeName;
		this.departureTime = departureTime;
		this.source = source;
		this.destination = destination;
	}
	
	
	public BusRoute() {}
	
	@Override
	public String toString() {
		return "BusRoute [id=" + id + ", busNumber=" + busNumber + ", routeName=" + routeName + ", departureTime="
				+ departureTime + ", source=" + source + ", destination=" + destination + "]";
	}
}
