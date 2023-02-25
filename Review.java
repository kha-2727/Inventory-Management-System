package buisnessLayer;

public class Review {
	static int tempid;
	int id;
	Product p;
	Customer c;
	String review;
	

	
	public Review(int id, Product p, Customer c, String review) {
		this.id=id;
		this.p = p;
		this.c = c;
		this.review = review;
	}
	
	
	public Review(Product p, Customer c, String review) {
		this.p = p;
		this.c = c;
		this.review = review;
	}
	
	
	
	
	public Review() {
	
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
}
