package buisnessLayer;

public class Product {
	static int tempid;
	int id;
	String name;
	int quantity;
	double sellingPrice;
	double purchasedPrice;
	Category category;
	public Product(int id, String name, int quantity, double sellingPrice, double purchasedPrice, Category category) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.sellingPrice = sellingPrice;
		this.purchasedPrice = purchasedPrice;
		this.category = category;
	}
	public Product(String name, int quantity, double sellingPrice, double purchasedPrice, Category category) {
		this.name = name;
		this.quantity = quantity;
		this.sellingPrice = sellingPrice;
		this.purchasedPrice = purchasedPrice;
		this.category = category;
	}
	public Product() {
		this.id = 0;
		this.name = "";
		this.quantity = 0;
		this.sellingPrice = 0;
		this.purchasedPrice = 0;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public double getPurchasedPrice() {
		return purchasedPrice;
	}
	public void setPurchasedPrice(double purchasedPrice) {
		this.purchasedPrice = purchasedPrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	

}
