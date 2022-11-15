package restaurant_reservation_system;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Vector;

import menu_system.MenuData;

public class Main {
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
	
	Vector<TableBook> tableBook = new Vector<>();
	MenuData menuData = new MenuData();
	
	Scanner scan = new Scanner(System.in);
	Integer restaurantNavigation, orderNavigation, menuNavigation;
	Integer tableFlag, timeFlag;
	
	String customerNameInput;
	Integer tableNumberInput, timePeriodInput = 0;
	Integer deleteInput;
	Integer viewInput;
	Integer manageInput;
	
	Integer orderInput;
	Integer qtyInput;
	
	private void printTimePeriod() {
		System.out.println("\nTime Period List");
		System.out.println("================");
		System.out.println("1. 10:00 - 12:00");
		System.out.println("2. 13:00 - 15:00");
		System.out.println("3. 16:00 - 18:00");
		System.out.println("================\n");
	}
	
	private void printTableBookList() {
		int x = 1;
		System.out.format("| No. | %-25s | %-15s | %-15s |\n=======================================================================\n", "Customer Name", "Table Number", "Booking Period");
		for (TableBook t : tableBook) {
			System.out.format("| %-3s | %-25s | %-15s | %-15s |\n", x+". ", t.getCustomerName(), t.getTableNumber(), t.getStart()+ " - " + t.getEnd());
			x++;
		}
		System.out.println("=======================================================================");
	}
	
	private void printTableOrderList() {
		int x=1;
		System.out.println("\nOrder Table");
		System.out.println("========================================================");
		for (TableOrder to : tableBook.get(manageInput-1).orderList) {
			System.out.format("| %-4s | %-20s | %-5s | %-15s |\n", x+".", to.getName(), to.getQty(), "Rp "+to.getSubPrice());
			x++;
		}
		System.out.println("========================================================");
	}
	
	public void validate_table(Integer tableNumberInput){
		int x = 1;
		tableFlag=1;
		
		for (TableBook t3 : tableBook) {
			if(tableNumberInput.equals(t3.tableNumber)) {
				System.out.format("\n\n| No. | %-25s| %-15s | %-15s |\n=============================================================\n", "Customer Name", "Table Number", "Booking Period");
				for (TableBook t2 : tableBook) {
					System.out.format("| %-3s | %-25s | %-15s | %-15s |\n", x+". ", t2.getCustomerName(), t2.getTableNumber(), t2.getStart()+ " - " + t2.getEnd());
					tableFlag = 0;
					x++;
				}
				System.out.println("=============================================================");
				break;
			}
		}
		
		if(x==4) {
			System.out.println("\nTable is Full! You Will be Returned to the Main Menu\n");
			tableFlag=2;
		}
		
		if(tableFlag==0) {
			System.out.println("\nThere is already someone booked this table");
			System.out.println("1. Change Table");
			System.out.println("2. Continue to select different time");
			System.out.println("3. Cancel Booking");
			System.out.print(">> ");
			tableFlag = scan.nextInt()-1;
		}
	}
	
	public void validateShift(Integer tableNumberInput, Integer timePeriodInput) {
		LocalTime validateShift = null;
		timeFlag=1;
		
		if(timePeriodInput==1) {
			validateShift = LocalTime.parse("10:00", formatter);
		}
		else if(timePeriodInput==2) {
			validateShift = LocalTime.parse("13:00", formatter);
		}
		else if(timePeriodInput==3) {
			validateShift = LocalTime.parse("16:00", formatter);
		}
		
		for (TableBook t3 : tableBook) {
			if (t3.tableNumber.equals(tableNumberInput)  && t3.start.equals(validateShift)) {
				timeFlag=0;
				System.out.println("Please Choose Another Shift!");
				break;
			}
		}
		
	}
	
	public boolean validateOrderInput(String menuName) {
		for (TableOrder v : tableBook.get(manageInput-1).orderList) {
			if(v.getName().equals(menuName)) {
				return true;
			}
		}
		return false;
	}

	private void updateOrder() {
		Integer updOrder;
		Integer updQty;
		Integer updSubPri;
		
		if(tableBook.get(manageInput-1).orderList.size()!=0) {
			printTableOrderList();
			
			do {
				System.out.print("Input index yang mau di update : ");
				updOrder = scan.nextInt();
			} while (updOrder < 0 || updOrder>tableBook.get(manageInput-1).orderList.size());
			
			do {
				System.out.print("Input quantity : ");
				updQty = scan.nextInt();
			} while (updQty <= 0);
			
			updSubPri = tableBook.get(manageInput-1).orderList.get(updOrder-1).getSubPrice()/tableBook.get(manageInput-1).orderList.get(updOrder-1).getQty() * updQty;
			tableBook.get(manageInput-1).orderList.get(updOrder-1).setSubPrice(updSubPri);
			tableBook.get(manageInput-1).orderList.get(updOrder-1).setQty(updQty);
			System.out.println("Successfully update");
			
		}
		else {
			System.out.println("There is no order on this table!");
		}
	}

	private void removeOrder() {

		int searchNumber = 0;
				
		if (tableBook.size() == 0) {
			System.out.println("There is no tablebook");
		}else {
			printTableOrderList();
			
			do {
				System.out.println("Input Order's number to be deleted: ");
				searchNumber = scan.nextInt();
			}while(searchNumber < 0 || searchNumber > tableBook.get(manageInput - 1).orderList.size());
			
			tableBook.get(manageInput - 1).orderList.remove(searchNumber-1);
			System.out.println("Successfully delete");
		}
		
	}
	
	public void createTableBook() {
		
		System.out.println("Create Table Book");
		System.out.println("=================");
		
		do {	
			do {
				System.out.print("Choose your table [1 - 16]: ");
				tableNumberInput = scan.nextInt();
				validate_table(tableNumberInput);
			} while (tableNumberInput<1 || tableNumberInput>16 || tableFlag==0);
			
			if(tableFlag==2) {
				break;
			}
			
			do {
				scan.nextLine();
				System.out.print("Enter customer name: ");
				customerNameInput = scan.nextLine();
			} while (customerNameInput.isEmpty());
			
			do {
				printTimePeriod();
				System.out.print("Choose time period: ");
				timePeriodInput = scan.nextInt();
				validateShift(tableNumberInput, timePeriodInput);
			} while (timePeriodInput<1 || timePeriodInput>3 || timeFlag==0);
			
			tableFlag=2;
		} while(tableFlag!=2);
		
		if(timePeriodInput == 1) {
			tableBook.add(new TableBook(tableNumberInput, customerNameInput, "10:00", "12:00"));
		}
		else if(timePeriodInput == 2) {
			tableBook.add(new TableBook(tableNumberInput, customerNameInput, "13:00", "15:00"));
		}
		else if(timePeriodInput == 3) {
			tableBook.add(new TableBook(tableNumberInput, customerNameInput, "16:00", "18:00"));
		}
	}
	
	public void removeTableBook() {
		System.out.println("Delete Table Book\n");
		if(tableBook.size()!=0) {
			printTableBookList();
			System.out.print("\nSelect book entry to remove: ");
			deleteInput = scan.nextInt();
			tableBook.remove(deleteInput-1);
		}
		else {
			System.out.print("No Data");
		}
	}
	
	public void orderSystem() {
		printTableBookList();
		System.out.print("\nSelect book entry to manage: ");
		manageInput = scan.nextInt();
		if(manageInput<1 || manageInput>tableBook.size()) {
			System.out.println("Entry not found");
			return;
		}
		else {
			do {
				System.out.println("\n\nOrder System");
				System.out.println(String.format("Customer Name: %s\nTable Number: %s\nBooking Period Time: %s - %s\n", tableBook.get(manageInput-1).getCustomerName(), tableBook.get(manageInput-1).tableNumber, tableBook.get(manageInput-1).getStart(), tableBook.get(manageInput-1).getEnd()));
				tableBook.get(manageInput-1).printOrderList();
				
				System.out.println("\n\n1. Add order");
				System.out.println("2. Remove order");
				System.out.println("3. Update order quantity");
				System.out.println("4. Back to restaurant system");
				System.out.print(">> ");
				orderNavigation = scan.nextInt();
				
				if(orderNavigation==1) {
					menuSystem();
				}
				else if(orderNavigation==2) {
					removeOrder();
				}
				else if(orderNavigation==3) {
					updateOrder();
				}
				
			} while(orderNavigation!=4);
		}
		
		
	}
	
	public void menuSystem() {
		do {
			System.out.println("\n\n1. Foods");
			System.out.println("2. Drinks");
			System.out.println("3. Side Dishes");
			System.out.println("4. Cancel");
			System.out.print(">> ");
			menuNavigation = scan.nextInt();
			
			if(menuNavigation==1) {
				menuData.printFood();
				System.out.print("Select your food: ");
				orderInput = scan.nextInt();
				if(validateOrderInput(menuData.getFoodName(orderInput-1))==true) {
					System.out.println("That order is already on the list");
					return;
				}
				System.out.print("Quantity: ");
				qtyInput = scan.nextInt();
				
				tableBook.get(manageInput-1).addFoodOrder(menuData.getFoodName(orderInput-1)  , qtyInput, menuData.getFoodPrice(orderInput-1)*qtyInput);
			}
			else if(menuNavigation==2) {
				menuData.printDrink();
				System.out.print("Select your drink: ");
				orderInput = scan.nextInt();
				if(validateOrderInput(menuData.getDrinkName(orderInput-1))==true) {
					System.out.println("That order is already on the list");
					return;
				}
				System.out.print("Quantity: ");
				qtyInput = scan.nextInt();
				
				tableBook.get(manageInput-1).addDrinkOrder(menuData.getDrinkName(orderInput-1)  , qtyInput, menuData.getDrinkPrice(orderInput-1)*qtyInput);
			}
			else if(menuNavigation==3) {
				menuData.printSideDish();
				System.out.print("Select your sidedish: ");
				orderInput = scan.nextInt();
				if(validateOrderInput(menuData.getSideDishName(orderInput-1))==true) {
					System.out.println("That order is already on the list");
					return;
				}
				System.out.print("Quantity: ");
				qtyInput = scan.nextInt();
				
				tableBook.get(manageInput-1).addSideDishOrder(menuData.getSideDishName(orderInput-1)  , qtyInput, menuData.getSideDishPrice(orderInput-1)*qtyInput);
			}
			return;
		} while(menuNavigation!=4);
	}
	
	public Main() {

			do {
				System.out.println("Restaurant System");
				System.out.println("1. Manage table order");
				System.out.println("2. View table booking list");
				System.out.println("3. Create table booking");
				System.out.println("4. Remove table booking");
				System.out.println("5. View table order list");
				System.out.println("6. Exit");
				System.out.print(">> ");
				restaurantNavigation = scan.nextInt(); System.out.println();
				
				if(restaurantNavigation==1) {
					if(tableBook.size()!=0) {
						orderSystem();
					}
					else {
						System.out.print("No Data");
					}
				}
				
				else if(restaurantNavigation==2) {
					if(tableBook.size()!=0) {
						printTableBookList();
					}
					else {
						System.out.print("No Data");
					}
				}
				
				else if(restaurantNavigation==3) {
					createTableBook();
				}
				
				else if(restaurantNavigation==4) {
					removeTableBook();
					
				}
				
				else if(restaurantNavigation==5) {
					if(tableBook.size()!=0) {
						printTableBookList();
						System.out.print("\nSelect book entry to view: ");
						viewInput = scan.nextInt();
						if(tableBook.get(viewInput - 1).orderList.size()!=0) {
							System.out.println(String.format("Customer Name: %s\nTable Number: %s\nBooking Period Time: %s - %s\n", tableBook.get(viewInput-1).getCustomerName(), tableBook.get(viewInput-1).tableNumber, tableBook.get(viewInput-1).getStart(), tableBook.get(viewInput-1).getEnd()));
							tableBook.get(viewInput - 1).printOrderList();
						}
						else {
							System.out.println("There is no order on this table!");
						}
					}
					else {
						System.out.print("No Data");
					}
				}
				System.out.println("\n");
			} while (restaurantNavigation!=6);
		
	}

	public static void main(String[] args) {
		new Main();
	}

}