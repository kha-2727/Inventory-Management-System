package buisnessLayer;

public class Category {
	static int tempid;
	int id;
	String name;
	String description;
	public Category(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Category(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public Category() {
		this.id=0;
		this.name="";
		this.description="";
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
