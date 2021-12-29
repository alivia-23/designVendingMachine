package designVendingMachine;

public class Product {
	String name;
	int price;
	int quantity;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
		this.quantity = 0;
	}
	
	@Override
	public String toString() {
		return String.format("Name %s, price %d", this.name, this.price);	
    }
}
