package buisnessLayer;

import java.util.ArrayList;

public class Stock {

	Product product;
	int quantity;
	
	
	
	
	public Stock(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
		
	}



	public Stock() {
		
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	


	
	
	
	
	
	
	
}
