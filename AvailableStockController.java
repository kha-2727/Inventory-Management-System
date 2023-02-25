package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import buisnessLayer.Product;
import buisnessLayer.Store;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;


class AvailableStock {
	String Product_Name;
	String Category;
	String quantity;
	String SellingPrice;
	
	
	
	
	public AvailableStock(String product_Name, String category, String quantity, String sellingPrice) {
		
		this.Product_Name = product_Name;
		this.Category = category;
		this.quantity = quantity;
		this.SellingPrice = sellingPrice;
		
	}
	
	
	public AvailableStock() {
	
	}


	public String getProduct_Name() {
		return Product_Name;
	}


	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}


	public String getCategory() {
		return Category;
	}


	public void setCategory(String category) {
		Category = category;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getSellingPrice() {
		return SellingPrice;
	}


	public void setSellingPrice(String sellingPrice) {
		SellingPrice = sellingPrice;
	}

	
	
}



public class AvailableStockController implements Initializable {
	ArrayList<Product> availableStock= new ArrayList<Product>();
	
	@FXML
    private TableView<AvailableStock> table;
	
	@FXML
    private TableColumn<AvailableStock, String> category;

    @FXML
    private TableColumn<AvailableStock, String> productName;

    
    @FXML
    private TableColumn<AvailableStock, String> quantity;

    @FXML
    private TableColumn<AvailableStock, String> sellingPrice;
    
    Store store;
	    
    
    public void initData(Store s) {
    	
    	
    	this.store=s;
    	
    	availableStock=this.store.getAvailableStock();
		
		if(availableStock.size()==0) {
			table.setPlaceholder(new Label("No rows to display"));
		}
		
		
		
		final ObservableList<AvailableStock> rows=getAllRows();
			
		table.getItems().addAll(rows);
    }



	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
	
	
		productName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvailableStock, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<AvailableStock, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getProduct_Name());
						
			}
		});
		
		category.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvailableStock, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<AvailableStock, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getCategory());
						
			}
		});
		
		sellingPrice.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvailableStock, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<AvailableStock, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getSellingPrice());
						
			}
		});
		
		
		
		
		quantity.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<AvailableStock, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<AvailableStock, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getQuantity());
						
			}
		});
		
			
		}
		
	ObservableList<AvailableStock> getAllRows(){
		final ObservableList<AvailableStock> rows = FXCollections.observableArrayList();
		for(int i=0; i<availableStock.size(); i++) {
				AvailableStock row=new AvailableStock(availableStock.get(i).getName(), availableStock.get(i).getCategory().getName(), String.valueOf(availableStock.get(i).getQuantity()), String.valueOf(availableStock.get(i).getSellingPrice()));
				rows.add(row);
				
		}
		return rows;
	}
	
}
		
