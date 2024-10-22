
package useCases;

import java.util.List;
import java.util.Scanner;

import exceptionBus.BusException;
import modelBus.Booking;
import serviceBus.BookingServiceImpl;
import serviceBus.BookingServices;

public class BookingMain {
	      
		private static BookingServices bookingService = new BookingServiceImpl();

	    public void BookingOperation() {
	        Scanner scanner = new Scanner(System.in);
	        int choice;

	        do {
	            System.out.println("Booking System Menu:");
	            System.out.println("1. Book Ticket");
	            System.out.println("2. Get Booking");
	           	System.out.println("3. Get All Bookings");
	            System.out.println("4. Cancel Ticket");
	            System.out.println("5. Exit");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();

	            switch (choice) {
	                case 1:
	                	BookTicket(scanner);
	                    break;
	                case 2:
	                    getBooking(scanner);
	                   break;
	                case 3:
	                    getAllBookings();
	                    break;
	                case 4:
	                    CancelTicket(scanner);
	                    break;     
	                case 5:
	                    System.out.println("Exiting...");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 5);

	        scanner.close();
	    }

	    private static void BookTicket(Scanner scanner) {
	        Booking booking = new Booking();
	        System.out.print("Enter user ID: ");
	        booking.setUserId(scanner.nextInt());
	        System.out.print("Enter schedule ID: ");
	        booking.setScheduleId(scanner.nextInt());
	        System.out.print("Enter bus number: ");
	        booking.setBusNumber(scanner.nextInt());
	        System.out.print("Enter route name: ");
	        booking.setRouteName(scanner.next());
	        System.out.print("Enter passenger name: ");
	        booking.setPassengerName(scanner.next());
	        System.out.print("Enter passenger age: ");
	        booking.setPassengerAge(scanner.nextInt());
	        System.out.print("Enter passenger contact: ");
	        booking.setPassengerContact(scanner.next());
	        System.out.print("Enter travel date (YYYY-MM-DD): ");
	        booking.setTravelDate(java.sql.Date.valueOf(scanner.next()));
	        System.out.print("Enter seat preference (window/aisle): ");
	        booking.setSeatPreference(scanner.next());
	        booking.setBookingStatus("confirmed"); // Default status
	        booking.setSeatNumber(0); // Initial seat number, will be assigned later
	        
	        
	     // Assign seat number based on preference
	        try {
	            int assignedSeatNumber = bookingService.assignSeatNumber(booking.getBusNumber(), booking.getSeatPreference());
	            booking.setSeatNumber(assignedSeatNumber);
	            bookingService.BookTicket(booking);
	            System.out.println("Ticket Booking successfull");
	        } catch (BusException e) {
	            System.out.println("Error while booking: " + e.getMessage());
	        }
	    }

	    private static void getBooking(Scanner scanner) {
	        System.out.print("Enter booking ID: ");
	        Booking booking = bookingService.getBooking(scanner.nextInt());
	        if (booking != null) {
	            System.out.println("Booking Details: " + booking);
	        } else {
	            System.out.println("Booking not found.");
	        }
	    }

	    private static void getAllBookings() {
	        List<Booking> bookings = bookingService.getAllBookings();
	        if (bookings.isEmpty()) {
	            System.out.println("No bookings found.");
	        } else {
	            System.out.println("All Bookings:");
	            for (Booking booking : bookings) {
	                System.out.println(booking);
	            }
	        }
	    }    

	    private static void CancelTicket(Scanner scanner) {
	        System.out.print("Enter booking ID to cancel: ");
	        try {
	            bookingService.CancelTicket(scanner.nextInt());
	            System.out.println("Booking cancelled successfully!");
	        } catch (BusException e) {
	            System.out.println("Error while cancelling booking: " + e.getMessage());
	        }
	    }
	    
	    public static void main(String[] args) {
	    	
	    	BookingMain bookingMain = new BookingMain();
	    	bookingMain.BookingOperation();		
	}
}
