package buisnessLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Order {
	static int tempid=0;
	int id;
	Customer c;
	ArrayList<purchasedProducts> products=new ArrayList<purchasedProducts>();
	LocalDate d;
	public Order(Customer c, ArrayList<purchasedProducts> products, LocalDate d) {
		super();
		this.c = c;
		this.products = products;
		this.d = d;
	}
	public Order(int id, Customer c, ArrayList<purchasedProducts> products, LocalDate d) {
		this.id=id;
		this.c = c;
		this.products = products;
		this.d = d;
	}
	public Order() {
		
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public ArrayList<purchasedProducts> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<purchasedProducts> products) {
		this.products = products;
	}
	public LocalDate getD() {
		return d;
	}
	public void setD(LocalDate d) {
		this.d = d;
	}

	
}
