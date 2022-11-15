package restaurant_reservation_system;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;


public class TableBook {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
	
	Vector<TableOrder> orderList = new Vector<>();
	Integer tableNumber;
	String customerName;
	LocalTime start;
	LocalTime end;

	public Integer getTableNumber() {
		return tableNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public LocalTime getStart() {
		return start;
	}

	public LocalTime getEnd() {
		return end;
	}
	
	public void addFoodOrder(String name, Integer qty, Integer subPrice) {
		orderList.add(new FoodOrder(name, qty, subPrice));
	}
	
	public void addDrinkOrder(String name, Integer qty, Integer subPrice) {
		orderList.add(new DrinkOrder(name, qty, subPrice));
	}
	
	public void addSideDishOrder(String name, Integer qty, Integer subPrice) {
		orderList.add(new SideDishOrder(name, qty, subPrice));
	}
	
	public void printOrderList() {
	
		int x=1;
		Integer foodTotal=0, drinkTotal=0, sidedishTotal=0;
		System.out.format("| No. | %-20s | %-5s | %-15s |\n========================================================\n", "Name", "Qty", "Sub Price");
		System.out.println("Foods :");
		for (TableOrder o1 : orderList) {
			if(o1 instanceof FoodOrder) {
				System.out.format("| %-3s | %-20s | %-5s | %-15s |\n", x+".", o1.getName(), o1.getQty(), "Rp "+o1.getSubPrice()); x++;
				foodTotal += o1.getSubPrice();
			}
			
		}
		System.out.format("Food total price: Rp.");
		if(foodTotal!=0) {
			System.out.println(foodTotal);
		}
		else {
			System.out.println("-");
		}
		
		System.out.println("\nDrinks: ");
		for (TableOrder o2 : orderList) {
			if(o2 instanceof DrinkOrder) {
				System.out.format("| %-3s | %-20s | %-5s | %-15s |\n", x+".", o2.getName(), o2.getQty(), "Rp "+o2.getSubPrice()); x++;
				drinkTotal += o2.getSubPrice();
			}
			
		}
		System.out.format("Drink total price: Rp.");
		if(drinkTotal!=0) {
			System.out.println(drinkTotal);
		}
		else {
			System.out.println("-");
		}
		
		System.out.println("\nSide Dish:");
		for (TableOrder o3 : orderList) {
			if(o3 instanceof SideDishOrder) {
				System.out.format("| %-3s | %-20s | %-5s | %-15s |\n", x+".", o3.getName(), o3.getQty(), "Rp "+o3.getSubPrice()); x++;

				sidedishTotal += o3.getSubPrice();
			}
		}
		System.out.format("Side Dish total price: Rp.");
		if(sidedishTotal!=0) {
			System.out.println(sidedishTotal);
		}
		else {
			System.out.println("-");
		}
		System.out.println("========================================================");
		
		System.out.format("\nTotal Price : Rp.%s\n", foodTotal+drinkTotal+sidedishTotal);
		System.out.println("========================================================");
	}


	public TableBook(Integer tableNumber, String customerName, String start, String end) {
		super();
		this.tableNumber = tableNumber;
		this.customerName = customerName;
		this.start = LocalTime.parse(start, formatter);
		this.end = LocalTime.parse(end, formatter);
	}

}
