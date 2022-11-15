package menu_system;

import java.util.Vector;

public class MenuData {
	
	Vector<Foods> foodList = new Vector<>();
	Vector<Drinks> drinkList = new Vector<>();
	Vector<SideDishes> sidedishList = new Vector<>();
	
	public String getFoodName(Integer index) {
		return foodList.get(index).getName();
	}
	
	public Integer getFoodPrice(Integer index) {
		return foodList.get(index).getPrice();
	}
	
	public String getDrinkName(Integer index) {
		return drinkList.get(index).getName();
	}
	
	public Integer getDrinkPrice(Integer index) {
		return drinkList.get(index).getPrice();
	}
	
	public String getSideDishName(Integer index) {
		return sidedishList.get(index).getName();
	}
	
	public Integer getSideDishPrice(Integer index) {
		return sidedishList.get(index).getPrice();
	}
	
	public void printFood() {
		int x=1;
		System.out.println("Food List");
		System.out.println("========================================");
		System.out.format("| No. | %-20s | %-8s |\n========================================\n", "Name", "Price");
		for (Foods f : foodList) {
			System.out.format("| %-3s | %-20s | %-7s |\n", x+".", f.getName(), "Rp "+f.getPrice()); x++;
		}
		System.out.println("========================================");
	}
	
	public void printDrink() {
		int x=1;
		System.out.println("Drink List");
		System.out.println("========================================");
		System.out.format("| No. | %-20s | %-8s |\n========================================\n", "Name", "Price");
		for (Drinks d : drinkList) {
			System.out.format("| %-3s | %-20s | %-7s |\n", x+".", d.getName(), "Rp "+d.getPrice()); x++;
		}
		System.out.println("========================================");
	}
	
	public void printSideDish() {
		int x=1;
		System.out.println("Side Dish List");
		System.out.println("========================================");
		System.out.format("| No. | %-20s | %-8s |\n========================================\n", "Name", "Price");
		for (SideDishes s : sidedishList) {
			System.out.format("| %-3s | %-20s | %-7s |\n", x+".", s.getName(), "Rp "+s.getPrice()); x++;
		}
		System.out.println("========================================");
	}
	
	public MenuData() {
		foodList.add(new Foods("Calzone", 25000));
		foodList.add(new Foods("Pizza", 35000));
		foodList.add(new Foods("Noodle", 12000));
		foodList.add(new Foods("Grilled Chicken", 27000));
		foodList.add(new Foods("Garlic Beef", 28000));
		foodList.add(new Foods("Spaghetti Carbonara", 30000));
		
		drinkList.add(new Drinks("Ice Tea", 7000));
		drinkList.add(new Drinks("Milk Tea", 12000));
		drinkList.add(new Drinks("Milk", 10000));
		drinkList.add(new Drinks("Jasmine Tea", 6000));
		drinkList.add(new Drinks("Milk Shake", 15000));
		drinkList.add(new Drinks("Thai Tea", 12000));
		
		sidedishList.add(new SideDishes("Pudding", 9000));
		sidedishList.add(new SideDishes("Crackers", 2000));
		sidedishList.add(new SideDishes("Mashed Potato", 7000));
		sidedishList.add(new SideDishes("Freanchfries", 10000));
		sidedishList.add(new SideDishes("Italian Ice Cream", 20000));
		sidedishList.add(new SideDishes("Chocolate Lava Cake", 20000));
	}

}
