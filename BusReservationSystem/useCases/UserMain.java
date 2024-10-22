package useCases;

import java.util.List;
import java.util.Scanner;

import modelBus.BusRoute;
import modelBus.BusSchedule;
import modelBus.User;

import serviceBus.UserServiceImpl;
import serviceBus.UserServices;

public class UserMain {
	
	private static Scanner sc = new Scanner(System.in);
	private static UserServices userservice = new UserServiceImpl();
	
	
	public void UserOperation() {
		
		
		while(true) {
			
			System.out.println( " Press 1 for Register User \n" 
			                  + " Press 2 for Login as User \n"
			                  + " Press 3 for Exit");
			
			int choice = sc. nextInt();
			
			switch(choice) {
			
			case 1 :
				
				System.out.println("You want to register User ");
				User user = new User();
				
				System.out.println("Enter User Id ");
				user.setId(sc.nextInt());
				
				System.out.println("Enter Username ");
				user.setUsername(sc.next());
				
				System.out.println("Enter Password ");
				user.setPassword(sc.next());
				
				System.out.println("Enter Email Id ");
				user.setEmail(sc.next());
				
				System.out.println("Enter Contact Number ");
				user.setContactNumber(sc.next());
				
				
				
				
				if(userservice.registerUser(user)) {	
				System.out.println("User Registration Successful ");
				}else {
					System.out.println("User Registration Failed ! ");
				}
				break;
				
			case 2 :
				
				System.out.println("Enter Username: ");
                String username = sc.next();
                System.out.println("Enter Password: ");
                String password = sc.next();
                
                
                if (userservice.login(username, password)) {
                    System.out.println("Login Successfull!");
                    UserDashboard();
                } else {
                    System.out.println("Invalid Username or Password !");
                }
                
                break;		
				
				
			case 3 :
				
				System.out.println("Exiting....");
				System.exit(0);
				
				break;
				
				default:
					
					System.out.println("Invalid choice! Please choose a valid option. ");	
			
			}
		}
 }
 
    private void UserDashboard() {
    	 
    	while(true) {
    		
    		System.out.println( 
			            "1 for Show User Profile \n" 
	                  + "2 for Update User Profile \n" 
			          + "3 for Search Bus Routes  \n"
	                  + "4 for Search Bus Schedules \n"
			          + "5 for Bus Availability Section \n"
	                  + "6 for Ticket Booking Section \n "
	                 + "7 for Logout ");
    		
    		int choice = sc.nextInt();
    		
    		switch(choice) {    
    		
    		case 1 :
    			
    			System.out.println("Enter User Id to Show User Details ");
                int id = sc.nextInt();
                User user = userservice.getUser(id);
                
                if (user != null) {
                	System.out.println("User Id: " + user.getId());
                	System.out.println("Username: " + user.getUsername());
                	System.out.println("Password: " + user.getPassword());
                	System.out.println("Email Id: " + user.getEmail());
                	System.out.println("Contact Number: " + user.getContactNumber());
                	
                } else {
                	    System.out.println("User not found ! ");
                }
					
                break;
                
    		case 2 :
    			
    			System.out.println("You want to update User ");
				User updateUser = new User();
				
				System.out.println("Enter User Id ");
				updateUser.setId(sc.nextInt());
				
				System.out.println("Enter new Username ");
				updateUser.setUsername(sc.next());
				
				System.out.println("Enter new Password ");
				updateUser.setPassword(sc.next());
				
				System.out.println("Enter new Email Id ");
				updateUser.setEmail(sc.next());
				
				System.err.println("Enter new Contact Number ");
				updateUser.setContactNumber(sc.next());
				
				userservice.updateUser(updateUser);
				System.out.println("User Updated Successfully ");
				
				break;
				
    		case 3 :
    			
    			System.out.print("Enter Bus Number: ");
                int busNumber = sc.nextInt();
                sc.nextLine();
    			
                System.out.print("Enter Route Name: ");
                String routeName = sc.next();
                
                List<BusRoute> routes = userservice.searchBusRoute(busNumber, routeName);
                
                if (routes.isEmpty()) {
                    System.out.println("No bus routes found for the given criteria.");
                } else {
                    System.out.println("Found Bus Routes:");
                    
                    for(BusRoute route : routes) {
                    	System.out.println("Route  ID: " + route.getId());
                    	System.out.println("Bus Number: " + route.getBusNumber());
                        System.out.println("Route Name: " + route.getRouteName());
                        System.out.println("Departure Time: " + route.getDepartureTime());
                        System.out.println("Source Stop: " + route.getSource());
                        System.out.println("Destination Stop: " + route.getDestination());
                    }
                 }
                
    	          break;
    	          
    	    	  
    		case 4 :
    			
    			System.out.print("Enter Bus Number: ");
                int searchbusNumber = sc.nextInt();
                sc.nextLine();
    			
                System.out.print("Enter Route Name: ");
                String searchrouteName = sc.next();
                
                List<BusSchedule> schedules = userservice.searchBusSchedule(searchbusNumber, searchrouteName);
    			
                if (schedules.isEmpty()) {
                    System.out.println("No bus routes found for the given criteria.");
                } else {
                    System.out.println("Found Bus Routes:");
                    
                    for(BusSchedule schedule : schedules) {
                    	
                    	System.out.println("Schedule  ID: " + schedule.getScheduleId());
                    	System.out.println("Bus Number: " + schedule.getBusNumber());
                        System.out.println("Route Name: " + schedule.getRouteName());
                        System.out.println("Arrival Time: " + schedule.getArrivalTime());
                        System.out.println("Departure Time: " + schedule.getDepartureTime());
                        System.out.println("Source Stop: " + schedule.getSourceStop());
                        System.out.println("Destination Stop: " + schedule.getDestinationStop());
                    }
                    	
                  }
                   
    	  		break;
    	  		
    		case 5 :
    			
    			BusMain bus = new BusMain();
    	          bus.BusOperation();
    	          
    	          break;
    	          
    		case 6 :
    			
    			BookingMain bookingMain = new BookingMain();
    			bookingMain.BookingOperation();
    			
    			
    			break;
    			
    		case 7 :
    			
    			 System.out.println("Logging out...");
    	          return;

    	      default:
    	          System.out.println("Invalid Choice!");		
    		
    		}
    	}
    } 
 }
 
 