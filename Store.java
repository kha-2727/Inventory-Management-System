package buisnessLayer;

import java.util.ArrayList;

public class Store {
	ProductCatalog productCatalog = new ProductCatalog();
	ArrayList<Customer> customers= new ArrayList<Customer>();
	ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	ArrayList<Review> reviews= new ArrayList<Review>();
	ArrayList<StockIn> stockins= new ArrayList<StockIn>();
	ArrayList<Stockout> stockouts= new ArrayList<Stockout>();
	ArrayList<Order> orders=new ArrayList<Order>();
	
	public Store() {
		loadData();
	}
	
	

	public ArrayList<Review> getReviews() {
		return reviews;
	}



	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public String removeStock(Product p, int q, String reason) {
		String tempres=productCatalog.removeStockofProduct(p, q);
		if(tempres!=null) {
			return tempres;
		}
		Stockout s=new Stockout(p, q, reason);
		stockouts.add(s);
		return null;
	}
	
	public String addStock(Product p, Supplier sup, int price , int q, String reason) {
		String tempres=productCatalog.addStockofProduct(p, q);
		if(tempres!=null) {
			return tempres;
		}
		StockIn s=new StockIn(p, q, price, sup, reason);
		stockins.add(s);
		return null;
	}


	public void loadData() {
		productCatalog.loadData();
		loadCustomerData();
		loadSupplierData();
	}
	
	void loadCustomerData() {
		Customer c1=new Customer("Iman", "03331111111", "iman@gmail.com", "G-9", "1234");
		this.AddCustomer(c1);
	}
	
	void loadSupplierData() {
		Supplier s1=new Supplier("Supplier1", "03334111111", "sup1@gmail.com", "G-9");
		this.AddSupplier(s1);
	
		Supplier s2=new Supplier("Supplier2", "03334161111", "sup2@gmail.com", "G-9");
		this.AddSupplier(s2);
	
	}
	
	public Category searchCategory(String n) {
		return productCatalog.searchCategory(n);
	}
	
	public Product searchProduct(String n) {
		return productCatalog.searchProduct(n);
	}
	
	
	public ProductCatalog getProductCatalog() {
		return productCatalog;
	}

	public void setProductCatalog(ProductCatalog productCatalog) {
		this.productCatalog = productCatalog;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}

	public ArrayList<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(ArrayList<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public String AddCategory(Category c) {
		return productCatalog.AddCategory(c);
	}
	
	public void AddReview(Review r) {
		Review.tempid++;
		r.id=Review.tempid;
		this.reviews.add(r);
			
	}
	
	public String AddProduct(Product p) {
		return productCatalog.AddProduct(p);
	}
	
	public String AddCustomer(Customer c) {
		for(int i=0; i<customers.size(); i++) {
			if(customers.get(i).getEmail().equalsIgnoreCase(c.getEmail())) {
				return "This email already exist";
			}
			if(customers.get(i).getPhone().equalsIgnoreCase(c.getPhone())) {
				return "This phone number already exist";
			}
		}
		Customer.tempid++;
		c.id=Customer.tempid;
		customers.add(c);
		return null;
		
	}
	
	public String AddSupplier(Supplier c) {
		for(int i=0; i<suppliers.size(); i++) {
			if(suppliers.get(i).getEmail().equalsIgnoreCase(c.getEmail())) {
				return "This email already exist";
			}
			if(suppliers.get(i).getPhone().equalsIgnoreCase(c.getPhone())) {
				return "This phone number already exist";
			}
		}
		Supplier.tempid++;
		c.id=Supplier.tempid;
		suppliers.add(c);
		return null;
		
	}
	
	public String UpdateCategory(int n,Category c) {
		return productCatalog.UpdateCategory(n, c);
	}
	
	public String DeleteCategory(int n) {
		return productCatalog.DeleteCategory(n);
	}
	
	public String DeleteProduct(int n) {
		return productCatalog.DeleteProduct(n);
	}
	
	public String UpdateProduct(int n,Product p) {
		return productCatalog.UpdateProduct(n, p);
	}

	public ArrayList<Product> getAvailableStock() {
		return productCatalog.getAvailableStock();		
	}

	public int searchCustomerIndex(int n) {
		int ind=-1;
		for(int i=0; i<customers.size(); i++) {
			if(customers.get(i).id==n) {
				return i;
			}
		}
		return ind;
	}
	
	public int searchSupplierIndex(int n) {
		int ind=-1;
		for(int i=0; i<suppliers.size(); i++) {
			if(suppliers.get(i).id==n) {
				return i;
			}
		}
		return ind;
	}
	
	public Customer searchCustomer(int n) {
		for(int i=0; i<customers.size(); i++) {
			if(customers.get(i).id==n) {
				return customers.get(i);
			}
		}
		return null;
	}
	
	public Supplier searchSupplier(int n) {
		for(int i=0; i<suppliers.size(); i++) {
			if(suppliers.get(i).id==n) {
				return suppliers.get(i);
			}
		}
		return null;
	}
	
	public String UpdateCustomer(int n,Customer c) {
		int ind=searchCustomerIndex(n);
		
		for(int i=0; i<customers.size(); i++) {
			if(customers.get(i).getEmail().equalsIgnoreCase(c.getEmail()) && i!=ind) {
				return "This email already exist";
			}
			if(customers.get(i).getPhone().equalsIgnoreCase(c.getPhone()) && i!=ind) {
				return "This phone number already exist";
			}
		}
		
		if(ind!=-1) {
			c.id=n;
			customers.set(ind, c);
			return null;
		}
		else {
			return "The required customer donot exist";
		}
	}
	
	public String UpdateSupplier(int n,Supplier c) {
		int ind=searchSupplierIndex(n);
		
		for(int i=0; i<suppliers.size(); i++) {
			if(suppliers.get(i).getEmail().equalsIgnoreCase(c.getEmail()) && i!=ind) {
				return "This email already exist";
			}
			if(suppliers.get(i).getPhone().equalsIgnoreCase(c.getPhone()) && i!=ind) {
				return "This phone number already exist";
			}
		}
		
		if(ind!=-1) {
			c.id=n;
			suppliers.set(ind, c);
			return null;
		}
		else {
			return "The required supplier donot exist";
		}
	}
	
	public String DeleteCustomer(int n) {
		int ind=searchCustomerIndex(n);
		
		if(ind!=-1) {
			customers.remove(ind);
			return null;
		}
		else {
			return "The required customer donot exist";
		}
	}
	
	public String DeleteSupplier(int n) {
		int ind=searchSupplierIndex(n);
		
		if(ind!=-1) {
			suppliers.remove(ind);
			return null;
		}
		else {
			return "The required supplier donot exist";
		}
	}
	
	public Double calculateTotalCostofOrder(ArrayList<purchasedProducts> p) {
		Double total=0.0;
    	for(int i=0; i<p.size(); i++) {
    		if(p.get(i).p!=null) {
    			total+=p.get(i).getP().getSellingPrice()*p.get(i).getQuantity();
    		}
    		
    	}
    	return total;
	}
	
	public String checkProducts(ArrayList<purchasedProducts> p) {
		if(p.size()==0) {
			return "No products in order";
		}
    	for(int i=0; i<p.size(); i++) {
    		if(p.get(i).p==null) {
    			String ret="Product No "+Integer.toString(i+1)+" is incorrect";
    			return ret;
    		}
    		if(p.get(i).getQuantity()==0) {
    			String ret="Quantity of product No "+Integer.toString(i+1)+" is incorrect";
    			return ret;
    		}
    		
    		if(p.get(i).getQuantity()>p.get(i).p.getQuantity()) {
    			String ret="Quantity of product No "+Integer.toString(i+1)+" is more than available, Available="+p.get(i).p.getQuantity();
    			return ret;
    		}
    		
    		for(int j=i+1; j<p.size(); j++) {
    			if(p.get(j).p==null) {
        			String ret="Product No "+Integer.toString(j+1)+" is incorrect";
        			return ret;
        		}
    			if(p.get(i).p.getName()==p.get(j).p.getName()) {
    				String ret="Product No "+Integer.toString(i+1)+" is repeated product";
        			return ret;
    			}
    		}
    		
    	}
    	return null;
	}
	
	public void AddOrder(Order o) {
		
		
		Supplier.tempid++;
		o.id=Supplier.tempid;
		orders.add(o);
		
		for(int i=0; i<o.products.size(); i++) {
			o.products.get(i).p.setQuantity(o.products.get(i).p.getQuantity()-o.products.get(i).quantity);
		}
		
		DisplayallOrders();
		
		
	}
	
	public void DisplayallOrders() {
		System.out.println("---------------------");
		for(int i=0; i<orders.size(); i++) {
			System.out.println("Order No: "+(i+1));
			System.out.println(orders.get(i).getC().getName()+"  "+orders.get(i).getD());
			for(int j=0; j<orders.get(i).products.size(); j++) {
				System.out.println(orders.get(i).products.get(j).getP().getName()+ "  "+orders.get(i).products.get(j).getQuantity());
	    	}
		}
	}

}
