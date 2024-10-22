package exceptionBus;

public class BusException extends RuntimeException {
	
	public BusException() {
		super();
	}
	
	public BusException(String message) {
		super(message);
	}
}