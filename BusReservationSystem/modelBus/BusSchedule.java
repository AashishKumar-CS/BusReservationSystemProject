package modelBus;

import java.time.LocalDateTime;

public class BusSchedule {
	
	private int scheduleId;
	private int busNumber;
	private String routeName;
	private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String sourceStop;
    private String destinationStop;
	
    
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

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getSourceStop() {
		return sourceStop;
	}

	public void setSourceStop(String sourceStop) {
		this.sourceStop = sourceStop;
	}

	public String getDestinationStop() {
		return destinationStop;
	}

	public void setDestinationStop(String destinationStop) {
		this.destinationStop = destinationStop;
	}

	public BusSchedule(int scheduleId, int busNumber, String routeName, LocalDateTime departureTime,
			LocalDateTime arrivalTime, String sourceStop, String destinationStop) {
		super();
		this.scheduleId = scheduleId;
		this.busNumber = busNumber;
		this.routeName = routeName;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.sourceStop = sourceStop;
		this.destinationStop = destinationStop;
	}

	public BusSchedule() {}

	@Override
	public String toString() {
		return "BusSchedule [scheduleId=" + scheduleId + ", busNumber=" + busNumber + ", routeName=" + routeName
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", sourceStop=" + sourceStop
				+ ", destinationStop=" + destinationStop + "]";
	}
	
}
