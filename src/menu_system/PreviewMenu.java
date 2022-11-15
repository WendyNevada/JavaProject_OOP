package menu_system;

public class PreviewMenu {

	MenuData menuData = new MenuData();
	
	public PreviewMenu() {
		System.out.println("Foods\n===========================");
		menuData.printFood();
		System.out.println("\n\nDrinks\n===========================");
		menuData.printDrink();
		System.out.println("\n\nSideDishes\n===========================");
		menuData.printSideDish();
	}

	public static void main(String[] args) {
		new PreviewMenu();
	}

}
