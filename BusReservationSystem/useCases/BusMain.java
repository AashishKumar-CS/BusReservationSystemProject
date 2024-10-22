package useCases;

import java.util.List;
import java.util.Scanner;

import modelBus.Bus;
import serviceBus.BusServiceImpl;
import serviceBus.BusServices;

public class BusMain {
	
	private static Scanner sc = new Scanner(System.in);
	private static BusServices busService = new BusServiceImpl();
	
	
	
	public void BusOperation() {
	
	while(true) {
		
		System.out.println(" 1 for Add Bus \n "
				+ "2 for Show Bus \n "
				+ "3 for Show AllBuses \n "
				+ "4 for Update Bus \n "
				+ "5 for Delete Bus \n "
				+"6 for Exit Bus Section ");
		
		int choice = sc. nextInt();
		
		switch(choice) {
		
		
		case 1 :
			
			
			System.out.println("You want to add bus ");
			Bus newBus = new Bus();
			
			System.out.println("Enter Bus Id: ");
			newBus.setBusId(sc.nextInt());
			
			System.out.println("Enter Bus Number: ");
			newBus.setBusNumber(sc.nextInt());
			
			System.out.println("Enter Bus Type: ");
			newBus.setBusType(sc.next());
			
			System.out.println("Enter Total Seats: ");
			newBus.setTotalSeats(sc.nextInt());
			
			System.out.println("Enter Available Seats: ");
			newBus.setAvailableSeats(sc.nextInt());
			
			busService.addBus(newBus);
			System.out.println("Bus Added Successfully ");
			
			break;
			
			
		case 2 :
			
			System.out.println("Enter Bus ID to show: ");
            int busIdToShow = sc.nextInt();
            Bus retrievedBus = busService.getBusById(busIdToShow);
            if (retrievedBus != null) {
                System.out.println("Bus Details: ");
                System.out.println("Bus ID: " + retrievedBus.getBusId());
                System.out.println("Bus Type: " + retrievedBus.getBusType());
                System.out.println("Total Seats: " + retrievedBus.getTotalSeats());
                System.out.println("Available Seats: " + retrievedBus.getAvailableSeats());
            } else {
                System.out.println("Bus not found.");
            }
            break;
			
			
		case 3 :
			
			List<Bus> allBuses = busService.getAllBuses();
            if (!allBuses.isEmpty()) {
                System.out.println("List of All Buses:");
                for (Bus bus : allBuses) {
                    System.out.println("Bus ID: " + bus.getBusId());
                    System.out.println("Bus Number: " + bus.getBusNumber());
                    System.out.println("Bus Type: " + bus.getBusType());
                    System.out.println("Total Seats: " + bus.getTotalSeats());
                    System.out.println("Available Seats: " + bus.getAvailableSeats()); 
                }
            } else {
                System.out.println("No buses found.");
            }
            break;
			
            
		case 4 :
			
			System.out.println("Enter Bus ID to update: ");
            int busIdToUpdate = sc.nextInt();
            Bus busToUpdate = busService.getBusById(busIdToUpdate);
            if (busToUpdate != null) {
                System.out.println("Updating Bus ID: " + busToUpdate.getBusId());
                System.out.println("Enter new Bus Type: ");
                busToUpdate.setBusType(sc.next());
                System.out.println("Enter new Available Seats: ");
                busToUpdate.setAvailableSeats(sc.nextInt());
                busService.updateBus(busToUpdate);
                System.out.println("Bus updated successfully.");
            } else {
                System.out.println("Bus not found.");
            }
            break;
            
            
		case 5 :
			
			System.out.println("Enter Bus ID to delete: ");
            int busIdToDelete = sc.nextInt();
            busService.deleteBus(busIdToDelete);
            System.out.println("Bus deleted successfully.");
            
            break;
            
		case 6 :
			
			System.out.println("Exiting Bus Section.");
            return;

        default:
            System.out.println("Invalid choice. Please try again.");
    }
}	
	}

	
	public static void main(String[] args) {
		
		BusMain bus = new BusMain();
		bus.BusOperation();
		
	}
}
