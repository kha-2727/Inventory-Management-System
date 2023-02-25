package buisnessLayer;

public class purchasedProducts {
	Product p;
	int quantity;
	
	
	
	public purchasedProducts() {
		
	}

	public purchasedProducts(Product p, int quantity) {
	
		this.p = p;
		this.quantity = quantity;
	}
	
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
