package buisnessLayer;

import java.util.ArrayList;

public class ProductCatalog {
	ArrayList<Category> categories=new ArrayList<Category>();
	ArrayList<Product> products=new ArrayList<Product>();
	
	
	
	
	public ProductCatalog() {
	}
	
	
	
	public ArrayList<Category> getCategories() {
		return categories;
	}



	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}



	public ArrayList<Product> getProducts() {
		return products;
	}



	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}



	public String AddCategory(Category c) {
		
		for(int i=0; i<categories.size(); i++) {
			if(categories.get(i).name.equalsIgnoreCase(c.name)) {
				return "This category name already exist";
			}
		}
		Category.tempid++;
		c.id=Category.tempid;
		categories.add(c);
		return null;
	}
	
	public String UpdateCategory(int n,Category c) {
		int ind=searchCategoryIndex(n);
		
		for(int i=0; i<categories.size(); i++) {
			if(categories.get(i).name.equalsIgnoreCase(c.name) && i!=ind) {
				return "This category name already exist";
			}
		}
		
		if(ind!=-1) {
			c.id=n;
			categories.set(ind, c);
			for(int i=0; i<products.size(); i++) {
				if(products.get(i).getCategory().getId()==n) {
					products.get(i).setCategory(c);
				}
			}
			return null;
		}
		else {
			return "The required category donot exist";
		}
	}
	
	public String DeleteCategory(int n) {
		int ind=searchCategoryIndex(n);
		if(ind!=-1) {
			for(int i=0; i<products.size(); i++) {
				if(products.get(i).getCategory().getId()==n) {
					return "The category cannot be deleted as there is product of this category";
				}
			}
			categories.remove(ind);
			return null;
		}
		else {
			return "The required category donot exist";
		}
	}
	
	public String DeleteProduct(int n) {
		int ind=searchProductIndex(n);
		if(ind!=-1) {
			products.remove(ind);
			return null;
		}
		else {
			return "The required product donot exist";
		}
	}
	
	public String AddProduct(Product p) {
		
		for(int i=0; i<products.size(); i++) {
			if(products.get(i).name.equalsIgnoreCase(p.name)) {
				return "This product name already exist";
			}
		}
		Product.tempid++;
		p.id=Product.tempid;
		products.add(p);
		return null;
	}
	
	public String UpdateProduct(int n,Product p) {
		int ind=searchProductIndex(n);
		
		for(int i=0; i<products.size(); i++) {
			if(products.get(i).name.equalsIgnoreCase(p.name) && i!=ind) {
				return "This product name already exist";
			}
		}
		
		if(ind!=-1) {
			p.id=n;
			products.set(ind, p);
			return null;
		}
		else {
			return "The required product donot exist";
		}
	}
	
	public Category searchCategory(String n) {
		for(int i=0; i<categories.size(); i++) {
			if(categories.get(i).name.equalsIgnoreCase(n)) {
				return categories.get(i);
			}
		}
		return null;
	}
	
	public int searchCategoryIndex(int n) {
		int ind=-1;
		for(int i=0; i<categories.size(); i++) {
			if(categories.get(i).id==n) {
				return i;
			}
		}
		return ind;
	}
	
	public Product searchProduct(String n) {
		for(int i=0; i<products.size(); i++) {
			if(products.get(i).name.equalsIgnoreCase(n)) {
				return products.get(i);
			}
		}
		return null;
	}
	
	public int searchProductIndex(int n) {
		int ind=-1;
		for(int i=0; i<products.size(); i++) {
			if(products.get(i).id==n) {
				return i;
			}
		}
		return ind;
	}
	
	public String removeStockofProduct(Product p, int q) {
		int ind=searchProductIndex(p.getId());
		if(ind!=-1) {
			if(products.get(ind).getQuantity()>=q) {
				products.get(ind).setQuantity(products.get(ind).getQuantity()-q);
				return null;
			}
			else {
				return "Required quantity product donot exist";
			}
		}
		else {
			return "Required product donot exist";
		}
		
	}
	
	public String addStockofProduct(Product p, int q) {
		int ind=searchProductIndex(p.getId());
		if(ind!=-1) {

			products.get(ind).setQuantity(products.get(ind).getQuantity()+q);
			return null;
			
		}
		else {
			return "Required product donot exist";
		}
		
	}


	void loadData() {
		Category c1=new Category("Dairy", "Dairy Products are under this category");
		this.AddCategory(c1);
	
		Product p1=new Product("Milk", 100, 90, 70, c1);
		this.AddProduct(p1);
		
		Product p2=new Product("Yougurt", 120, 80, 70, c1);
		this.AddProduct(p2);
	}
	
	public ArrayList<Product> getAvailableStock() {			
		return products;		
	}

}
