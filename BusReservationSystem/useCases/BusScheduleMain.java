package useCases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import modelBus.BusRoute;
import modelBus.BusSchedule;

import serviceBus.BusScheduleServices;
import serviceBus.BusScheduleServiceImpl;
import serviceBus.BusRouteServices;
import serviceBus.BusRouteServiceImpl;

public class BusScheduleMain {
	
	private static Scanner sc = new Scanner(System.in);
	private static BusScheduleServices bus_scheduleService = new BusScheduleServiceImpl();
	private static BusRouteServices bus_routeService = new BusRouteServiceImpl();
	
	public void busScheduleOperation() {
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		
  while(true) {
			
			System.out.println("1 for Add BusSchedule \n" 
			                  + "2 for Show BusSchedule \n" 
					          + "3 for Show All BusSchedules \n" 
			                  + "4 for Update BusSchedule \n"  
					          + "5 for Remove BusSchedule \n"
			                  + "6 for Exit");
			
			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number.");
				sc.nextLine(); // discard invalid input
			}
			
			
			int choice = sc. nextInt();
			sc.nextLine(); // consume the newline character
			
			switch(choice) {
			
			case 1 :
				
				System.out.println("You want to add bus schedule ");
				BusSchedule bs = new BusSchedule();
				
				System.out.println("Enter Schedule Id ");
                bs.setScheduleId(sc.nextInt());
                
                System.out.print("Enter Bus Number: ");
                int busNumber = sc.nextInt();
                bs.setBusNumber(busNumber);
                sc.nextLine();
                
                
                BusRoute route = bus_routeService.getBusRoute(busNumber);
                if (route == null) {
                    System.out.println("Bus number " + busNumber + " does not exist in bus routes. Please add it first.");
                    return;
                }
                 
                System.out.print("Enter Route Name: ");
                bs.setRouteName(sc.nextLine());

                
                LocalDateTime departureTime;
                LocalDateTime arrivalTime;

                
                System.out.print("Enter Departure Time (yyyy-MM-dd HH:mm:ss): ");
                try {
                    departureTime = LocalDateTime.parse(sc.nextLine(), formatter);
                    bs.setDepartureTime(departureTime);
                } catch (Exception e) {
                    System.out.println("Invalid departure time format. Please use yyyy-MM-dd HH:mm:ss.");
                    return;
                }
                
                
                System.out.print("Enter Arrival Time (yyyy-MM-dd HH:mm:ss): ");
                try {
                    arrivalTime = LocalDateTime.parse(sc.nextLine(), formatter);
                    bs.setArrivalTime(arrivalTime);
                } catch (Exception e) {
                    System.out.println("Invalid arrival time format. Please use yyyy-MM-dd HH:mm:ss.");
                    return;
                }
                
                
                System.out.print("Enter Source Stop: ");
                bs.setSourceStop(sc.nextLine());
                
                System.out.println("Enter Destination Stop: ");
                bs.setDestinationStop(sc.nextLine());
               
                bus_scheduleService.addBusSchedule(bs);
                System.out.println("Bus Schedule Added Successfully ");
                
                break;
                
			case 2 :
				
				System.out.println("You want to show bus schedule ");
                System.out.println("Enter Bus Number ");
                int busNumber1 = sc.nextInt();
                sc.nextLine();
                BusSchedule schedule = bus_scheduleService.getBusSchedule(busNumber1);
                if (schedule != null) {
                    System.out.println("Schedule Id: " + schedule.getScheduleId());
                    System.out.println("Bus Number: " + schedule.getBusNumber());
                    System.out.println("Route Name: " + schedule.getRouteName());
                    System.out.println("Departure Time: " + schedule.getDepartureTime());
                    System.out.println("Arrival Time: " + schedule.getArrivalTime());
                    System.out.println("Source Stop: " + schedule.getSourceStop());
                    System.out.println("Destination Stop: " + schedule.getDestinationStop());
                    
                } else {
                    System.out.println("Bus Schedule not found");
                }

                break;
				
				
			case 3 :
				
				System.out.println("You want to show all bus schedules ");
                List<BusSchedule> allSchedules = bus_scheduleService.getAllBusSchedules();
                for (BusSchedule s : allSchedules) {
                  
                	System.out.println("Schedule Id: " + s.getScheduleId());
                    System.out.println("Bus Number: " + s.getBusNumber());
                    System.out.println("Route Name: " + s.getRouteName());
                    System.out.println("Departure Time: " + s.getDepartureTime());
                    System.out.println("Arrival Time: " + s.getArrivalTime());
                    System.out.println("Source Stop: " + s.getSourceStop());
                    System.out.println("Destination Stop: " + s.getDestinationStop());
                		
                }

                break;
                
			case 4 :
				
				System.out.println("You want to update bus schedule ");
                System.out.println("Enter Bus Number ");
                int updateBusNumber = sc.nextInt();
                sc.nextLine();
                BusSchedule updateSchedule = bus_scheduleService.getBusSchedule(updateBusNumber);
                
                if (updateSchedule != null) {
                	
                    System.out.print("Enter New Bus Number: ");
                    int newBusNumber = sc.nextInt();
                    sc.nextLine();
                    
                    if (newBusNumber != updateBusNumber) {
                        if (bus_scheduleService.getBusSchedule(newBusNumber) != null) {
                            System.out.println("Bus number " + newBusNumber + " already exists. Please enter a different bus number.");
                            return;
                        }
                    }

                    updateSchedule.setBusNumber(newBusNumber);
                     
                    System.out.print("Enter New Route Name: ");
                    updateSchedule.setRouteName(sc.nextLine());
                    
                   
                    LocalDateTime departureTime1;
                    LocalDateTime arrivalTime1;

                    System.out.print("Enter New Departure Time (yyyy-MM-dd HH:mm:ss): ");
                    try {
                        departureTime1 = LocalDateTime.parse(sc.nextLine(), formatter);
                        updateSchedule.setDepartureTime(departureTime1);
                    } catch (Exception e) {
                        System.out.println("Invalid departure time format. Please use yyyy-MM-dd HH:mm:ss.");
                        return;
                    }
                    
                    
                    System.out.print("Enter New Arrival Time (yyyy-MM-dd HH:mm:ss): ");
                    try {
                        arrivalTime1 = LocalDateTime.parse(sc.nextLine(), formatter);
                        updateSchedule.setArrivalTime(arrivalTime1);
                    } catch (Exception e) {
                        System.out.println("Invalid arrival time format. Please use yyyy-MM-dd HH:mm:ss.");
                        return;
                    }
                    
                    
                    System.out.print("Enter New Source Stop: ");
                    updateSchedule.setSourceStop(sc.nextLine());
                    
                    System.out.println("Enter New Destination Stop: ");
                    updateSchedule.setDestinationStop(sc.nextLine());
                    
                    
                    bus_scheduleService.updateBusSchedule(updateSchedule);
                    System.out.println("Bus Schedule updated Successfully ");
                } else {
                    System.out.println("Bus Schedule not found");
                }
                    
                break;
                
			case 5 :
				
				System.out.println("You want to remove bus schedule ");
                System.out.println("Enter Schedule Id ");
                int removeScheduleId = sc.nextInt();
                bus_scheduleService.deleteBusSchedule(removeScheduleId);
                System.out.println("Bus Schedule removed Successfully ");
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
		BusScheduleMain busScheduleMain = new BusScheduleMain();
		busScheduleMain.busScheduleOperation();
	}
}

