package designVendingMachine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
	Map<String, Product> map;
	int balance = 0;
	
	public enum Coin {
		PENNY(1), NICKEL(5), DIME(10), QUARTER(25);
		int value;
		
		Coin(int value) {
			this.value = value;
		}
	}
	
	public VendingMachine() {
		map = new HashMap<>();
	}
	
	public void addNewProduct(String name, int price) {
		Product product = new Product("name", price);
		map.put(name, product);
	}
	
	public List<Product> getAllProducts() {
		List<Product> products = (List<Product>)map.values();
		Collections.sort(products, (a, b) -> a.price - b.price);
		return products;
	}
	
	public boolean stockProduct(String name, int quantity) {
		Product curProduct = map.get(name);
		if (map.containsKey(name)) {
			int curQuantity = map.get(name).quantity;
			curQuantity += quantity;
			curProduct.quantity = curQuantity;
			map.put(name, curProduct);
			return true;
		}
		return false;
	}
	
	public boolean insertCoin(String amount) {
		try {
			Coin coin = Coin.valueOf(amount);
			balance += coin.value; 
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}
	
	public boolean purchaseProduct(String name, int quantity) {
		Product product = map.get(name);
		int availableQuantity = map.get(name).quantity;
		
		if (availableQuantity >= quantity && balance >= (product.price * quantity)) {
			availableQuantity -= quantity;
			balance -= product.price * quantity;
			product.quantity = availableQuantity;
			map.put(name, product);
			return true;
		} 
		return false;
	}
	
	public List<String> checkout() {
		List<String> result = new ArrayList<>();
		int availableBalance = this.balance;
		Coin[] coins = Coin.values();
		for(int i = coins.length-1; i >= 0; i--) {
			Coin coin = coins[i];
			if(availableBalance >= coin.value) {
				int n = availableBalance/coin.value;
				result.add(String.format("%d %d", n, coin.value));
				availableBalance = availableBalance - n * coin.value;
			}
		}
		
		this.balance = 0;
		return result;
	}
	
	
}
