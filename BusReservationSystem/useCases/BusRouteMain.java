package useCases;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import modelBus.BusRoute;
import serviceBus.BusRouteServices;
import serviceBus.BusRouteServiceImpl;


public class BusRouteMain {
	
	private static Scanner sc = new Scanner(System.in);
	private static BusRouteServices bus_routeService = new BusRouteServiceImpl();
	
	
	public void busRouteOperation() {
		
     while(true) {
			
			System.out.println("1 for Add BusRoute \n" 
			                  + "2 for Show BusRoute \n" 
					          + "3 for Show All BusRoutes \n" 
			                  + "4 for Update BusRoute \n"  
					          + "5 for Remove BusRoute \n"
			                  + "6 for Exit");
			
			int choice = sc. nextInt();
			
			
			switch(choice) {
			
			
			case 1 :
				
				System.out.println("You want to add bus route ");
                 BusRoute br = new BusRoute();
				
				System.out.println("Enter Route Id ");
				br.setId(sc.nextInt());
				
				System.out.println("Enter Bus Number ");
				br.setBusNumber(sc.nextInt());
				
				System.out.println("Enter Route Name ");
				br.setRouteName(sc.next());
				
				System.out.println("Enter Departure Time (HH:MM): ");
				String timeInput = sc.next();
				LocalTime departureTime = LocalTime.parse(timeInput);
				br.setDepartureTime(departureTime);
				
				
				System.out.println("Enter Source Stop ");
				br.setSource(sc.next());
				
				System.out.println("Enter Destination Stop ");
				br.setDestination(sc.next());
				
				bus_routeService.addBusRoute(br);	
				System.out.println("Bus Route Added Successfully ");
				
				break;
				
			case 2 :
				
				System.out.println("You want to show bus route ");
				System.out.println("Enter Bus Number ");
				int busNumber = sc.nextInt();
				sc.nextLine();
				
				BusRoute route = bus_routeService.getBusRoute(busNumber);
				
				if(route != null) {
					
					System.out.println("Route  ID: " + route.getId());
                	System.out.println("Bus Number: " + route.getBusNumber());
                    System.out.println("Route Name: " + route.getRouteName());
                    System.out.println("Departure Time: " + route.getDepartureTime());
                    System.out.println("Source Stop: " + route.getSource());
                    System.out.println("Destination Stop: " + route.getDestination());
                    
                } else {
                    System.out.println("Bus Route not found");
                }

                break;

                
			case 3 :
				
				System.out.println("You want to show all bus routes ");
                List<BusRoute> allRoutes = bus_routeService.getAllBusRoutes();
               
                for (BusRoute r : allRoutes) {
                	System.out.println("Route  ID: " + r.getId());
                	System.out.println("Bus Number: " + r.getBusNumber());
                    System.out.println("Route Name: " + r.getRouteName());
                    System.out.println("Departure Time: " + r.getDepartureTime());
                    System.out.println("Source Stop: " + r.getSource());
                    System.out.println("Destination Stop: " + r.getDestination());
                }

                break;
                
                
			case 4 :
				
				System.out.println("You want to update bus route ");
                System.out.println("Enter Bus Number ");
                int updateBusNumber = sc.nextInt();
                sc.nextLine();
                BusRoute updateRoute = bus_routeService.getBusRoute(updateBusNumber);
                if (updateRoute != null) {
                	
                	System.out.println("Enter New Bus Number ");
    				updateRoute.setBusNumber(sc.nextInt());
                	
                    System.out.println("Enter new Route Name ");
                    updateRoute.setRouteName(sc.next());
                    
                    
                    System.out.println("Enter new Departure Time (HH:MM): ");
                    String timeInput1 = sc.next();
                    if (!timeInput1.isEmpty()) {
                        try {
                            LocalTime departureTime1 = LocalTime.parse(timeInput1);
                            updateRoute.setDepartureTime(departureTime1);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid time format. Please use HH:MM format.");
                            break; // Exit the case if the format is wrong
                        }
                    } else {
                        System.out.println("Departure time cannot be empty.");
                        break; // Exit the case if the input is empty
                    }

                    System.out.println("Enter new Source Stop ");
                    updateRoute.setSource(sc.next());

                    System.out.println("Enter new Destination Stop ");
                    updateRoute.setDestination(sc.next());

                    bus_routeService.updateBusRoute(updateRoute);
                    System.out.println("Bus Route updated Successfully ");
                    
                } else {
                    System.out.println("Bus Route not found");
                }
				
                break;
                
                
			case 5 :
				
				System.out.println("You want to remove bus route ");
                System.out.println("Enter Route Id ");
                int removeRouteId = sc.nextInt();
                bus_routeService.deleteBusRoute(removeRouteId);
                System.out.println("Bus Route removed Successfully ");

                break;
                
			case 6 :
				
				System.out.println("Exiting...");
                return;

            default:

                System.out.println("Invalid choice. Please choose a valid option.");
	
			   }
          }
	}
	
	public static void main(String[] args) {
        BusRouteMain busRouteMain = new BusRouteMain();
        busRouteMain.busRouteOperation();
    }
}


