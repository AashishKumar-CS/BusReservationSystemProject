package useCases;

import java.util.List;
import java.util.Scanner;

import modelBus.Admin;
import modelBus.User;
import serviceBus.AdminServices;
import serviceBus.UserServiceImpl;
import serviceBus.UserServices;
import serviceBus.AdminServiceImpl;


public class AdminMain {
	
	private static Scanner sc = new Scanner(System.in);
	private static AdminServices adminService = new AdminServiceImpl();
	private static UserServices userservice = new UserServiceImpl();
	
	
	
	public void AdminOperation() {
		
    while(true) {
			
			System.out.println("   Press 1 for Login as Admin \n  " 
			                   +" Press 2 for Register as Admin \n  " 
					           +" Press 3 for Exit \n " );
			                  
			int choice = sc. nextInt();
			
			switch(choice) {
			
			case 1 :
				
				System.out.println("Enter Admin Username: ");
                String username = sc.next();
                System.out.println("Enter Admin Password: ");
                String password = sc.next();

                if (adminService.login(username, password)) {
                    System.out.println("Login Successful!");
                    AdminDashboard();
                } else {
                    System.out.println("Invalid Username or Password!");
                }
                break;		
			
			case 2 :
				
				System.out.println("Enter Admin Username: ");
                String newUsername = sc.next();
                System.out.println("Enter Admin Password: ");
                String newPassword = sc.next();
                System.out.println("Enter Admin Email: ");
                String newEmail = sc.next();

                Admin admin = new Admin();
                admin.setUsername(newUsername);
                admin.setPassword(newPassword);
                admin.setEmail(newEmail);

                if (adminService.registerAdmin(admin)) {
                    System.out.println("Registration Successful!");
                } else {
                    System.out.println("Registration Failed!");
                }
                break;
                
			case 3 :
				
                System.out.println("Exiting...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid Choice!");
			  	
	}
   }
 }
	
	private void AdminDashboard() {
		
		System.out.println("1 for Update Admin Details \n" 
                + "2 for Remove Admin \n" 
		        + "3 for Show All Admins \n"    
                + "4 for Show User Details \n"
                + "5 for Remove User \n "
			 +"6 for Show All Users \n " 
             +"7 for Bus Route Management \n "
			 +"8 for Bus Schedule Management \n "
             +"9 for Bus Management Section \n "
			 +"10 for Booking Management section \n "
             +"11 for Logout \n "  );

       int choice = sc.nextInt();

      switch (choice) {
      
      case 1 : 
    	  
    	  System.out.println("Enter Admin ID to Update: ");
          int adminId = sc.nextInt();
          System.out.println("Enter New Username: ");
          String newUsername = sc.next();
          System.out.println("Enter New Password: ");
          String newPassword = sc.next();
          System.out.println("Enter New Email: ");
          String newEmail = sc.next();

          Admin admin = new Admin();
          admin.setId(adminId);
          admin.setUsername(newUsername);
          admin.setPassword(newPassword);
          admin.setEmail(newEmail);

          adminService.updateAdmin(admin);
			System.out.println("Admin Updated Successfully ");
         
			break;
    	  
      case 2 :
    	  
    	  System.out.println("Enter Admin ID to Remove: ");
          int removeAdminId = sc.nextInt();
          
          adminService.deleteAdmin(removeAdminId);
          System.out.println("Admin Removed Successfully ");
			   
          break;
          
      case 3 :
    	  
    	  List<Admin> admins = adminService.getAllAdmins();
          for (Admin adminItem : admins) {
              System.out.println("Admin ID: " + adminItem.getId());
              System.out.println("Username: " + adminItem.getUsername());
              System.out.println("Email: " + adminItem.getEmail());
              System.out.println();
          }
          
          break;
          
      case 4 :
    	  
    	  System.out.println("Enter User Id to display User ");
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
          
      case 5 :
    	  
    	  System.out.println("Enter User Id to remove User ");
			int userId = sc.nextInt();
			userservice.deleteUser(userId);
			System.out.println("User Removed Successfully ");
			
			break;
			
      case 6 :
    	  
    	  List<User> users = adminService.getAllUsers();
          for (User u : users) {
              System.out.println("User  ID: " + u.getId());
              System.out.println("Username: " + u.getUsername());
              System.out.println("Password: " + u.getPassword());
              System.out.println("Email: " + u.getEmail());
              System.out.println("Contact Number: " + u.getContactNumber());
          }
          
          break;
		
      case 7 :
    	  
    	  BusRouteMain busRouteMain = new BusRouteMain();
          busRouteMain.busRouteOperation();
    	  
          break;
          
      case 8 :
    	  
    	BusScheduleMain busScheduleMain = new BusScheduleMain();
  		busScheduleMain.busScheduleOperation();
  		
  		break;
  		
      case 9 :
    	  
    	  BusMain bus = new BusMain();
          bus.BusOperation();
          
          break;
          
      case 10 :
    	  
    	  BookingMain bookingMain = new BookingMain();
			bookingMain.BookingOperation();
    	  
    	  break;
    	  
      case 11 :
    	  
    	  System.out.println("Logging out...");
          return;

      default:
          System.out.println("Invalid Choice!");	
	}
  }
}