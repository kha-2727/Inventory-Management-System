package buisnessLayer;

public class Stockout extends Stock {
	
	static int tempid;
	int id;
	String reason;

	public Stockout() {
		super();
	}

	public Stockout(int id, Product product, int quantity, String reason) {
		super(product, quantity);
		this.id=id;
		this.reason = reason;
	}
	
	public Stockout(Product product, int quantity, String reason) {
		super(product, quantity);
		this.reason = reason;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
