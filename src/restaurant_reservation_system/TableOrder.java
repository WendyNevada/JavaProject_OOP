package restaurant_reservation_system;

public abstract class TableOrder {

	private String name;
	private Integer qty;
	private Integer subPrice;

	public String getName() {
		return name;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getSubPrice() {
		return subPrice;
	}
	
	public void setSubPrice(Integer subPrice) {
		this.subPrice = subPrice;
	}

	public TableOrder(String name, Integer qty, Integer subPrice) {
		super();
		this.name = name;
		this.qty = qty;
		this.subPrice = subPrice;
	}

}
