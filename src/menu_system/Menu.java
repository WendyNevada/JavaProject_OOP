package menu_system;

public abstract class Menu {
	
	String name;
	Integer price;
	
	public String getName() {
		return this.name;
	}
	
	public Integer getPrice() {
		return this.price;
	}
	
	public Menu(String name, Integer price) {
		this.name = name;
		this.price = price;
	}

}
