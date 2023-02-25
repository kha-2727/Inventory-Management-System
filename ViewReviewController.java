package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import buisnessLayer.Product;
import buisnessLayer.Store;
import buisnessLayer.Review;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;



class AvailableReviews {
	String Product_Name;
	String Customer;
	String review;
	

	public AvailableReviews() {
		
	}
	public AvailableReviews(String product_Name, String customer, String review) {
	
		Product_Name = product_Name;
		Customer = customer;
		this.review = review;
	}
	
	public String getProduct_Name() {
		return Product_Name;
	}
	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
	
}

public class ViewReviewController implements Initializable {
	Store store;
	
	ArrayList<Review> allreviews= new ArrayList<Review>();
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
	
	
		productName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvailableReviews, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<AvailableReviews, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getProduct_Name());
						
			}
		});
		
		customer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvailableReviews, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<AvailableReviews, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getCustomer());
						
			}
		});
		
		Review.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvailableReviews, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<AvailableReviews, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getReview());
						
			}
		});
		
		
		
			
		}
	
    
    public void initData(Store s) {
    	this.store=s;
    	
    	allreviews=this.store.getReviews();
		
		if(allreviews.size()==0) {
			table.setPlaceholder(new Label("No rows to display"));
		}
		

		final ObservableList<AvailableReviews> rows=getAllRows();
			
		table.getItems().addAll(rows);
    }
    
    ObservableList<AvailableReviews> getAllRows(){
		final ObservableList<AvailableReviews> rows = FXCollections.observableArrayList();
		for(int i=0; i<allreviews.size(); i++) {
				AvailableReviews row=new AvailableReviews(allreviews.get(i).getP().getName(), allreviews.get(i).getC().getEmail(), allreviews.get(i).getReview());
				rows.add(row);
				
		}
		return rows;
	}
    
    ObservableList<AvailableReviews> getAllRows(String n){
		final ObservableList<AvailableReviews> rows = FXCollections.observableArrayList();
		for(int i=0; i<allreviews.size(); i++) {
				String str1=n.toLowerCase();
				String str2=allreviews.get(i).getP().getName().toLowerCase();
				if(str2.contains(str1)) {
					System.out.println(allreviews.get(i).getP().getName()+"  "+ n);
					AvailableReviews row=new AvailableReviews(allreviews.get(i).getP().getName(), allreviews.get(i).getC().getEmail(), allreviews.get(i).getReview());
					rows.add(row);
				}
				
		}
		return rows;
	}
    
    @FXML
    private TableColumn<AvailableReviews, String> Review;

    @FXML
    private TableColumn<AvailableReviews, String> customer;

    @FXML
    private TextField item_name;

    @FXML
    private TableColumn<AvailableReviews, String> productName;

    @FXML
    private TableView<AvailableReviews> table;

    @FXML
    void handlekey(KeyEvent event) {
    	System.out.println(item_name.getText());
    	
    	final ObservableList<AvailableReviews> rows=getAllRows(item_name.getText());
		
		table.getItems().clear();
			
		table.getItems().addAll(rows);
    	
    }

    @FXML
    void searchProduct(ActionEvent event) {
    	System.out.println(item_name.getText());
    	
    	final ObservableList<AvailableReviews> rows=getAllRows(item_name.getText());
		
		table.getItems().clear();
			
		table.getItems().addAll(rows);
    }
}
