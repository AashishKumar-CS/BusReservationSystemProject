package useCases;

import java.util.Scanner;

public class BusAppMain {
	
	 static Scanner sc = new Scanner(System.in);
	 
	 public static void main(String[] args) {
		 
		 System.out.println(" ***** WELCOME TO DTC BUS RESERVATION SYSTEM ***** ");
		 
		 System.out.println("---------------------------------------");
		 
		 System.out.println(" Developed by -: \n" 
				 
		 		               
				                                          + " ** DELHI TRANSPORT CORPORATION ** \n");
		 
		 
		 AdminMain am = new AdminMain();
		 UserMain um = new UserMain();
		 
		 while(true) {
				
				System.out.println("  Press 1 for ADMIN PANEL \n " 
				                 + " Press 2 for USER PANEL \n " 
				                 + " Press 3 for EXIT ");
				
				
				int choice = sc.nextInt();
				
				switch(choice) {
				
				case 1 :
					
					am.AdminOperation();
					break;
					
				case 2 :
					
					um.UserOperation();
					break;
					
				case 3 :
					
					System.out.println(" Exiting..... ");
					
					System.exit(0);
					
					
		 
	}
				
   }
 }

}
