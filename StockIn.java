package buisnessLayer;

public class StockIn extends Stock {

	static int tempid;
	int id;
	Supplier supplier;
	String reason;
	int purchasePrice;
	
	

	public StockIn(int id, Product product, int quantity, Supplier supplier, String reason, int purchasePrice) {
		super(product, quantity);
		this.id = id;
		this.supplier = supplier;
		this.reason = reason;
		this.purchasePrice = purchasePrice;
	}

	public StockIn(Product product, int quantity, int purchasePrice, Supplier supplier, String reason) {
		super(product, quantity);
		this.supplier = supplier;
		this.reason=reason;
		this.purchasePrice=purchasePrice;
	}

	public StockIn(Product product, int quantity) {
		super(product, quantity);
	}
	
	public StockIn() {
		super();
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
