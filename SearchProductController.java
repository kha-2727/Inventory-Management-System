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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;



public class SearchProductController  implements Initializable {
	
ArrayList<Product> availableStock= new ArrayList<Product>();


@FXML
private TextField item_name;
	
	@FXML
    private TableView<AvailableStock> table;
	
	@FXML
    private TableColumn<AvailableStock, String> category;

    @FXML
    private TableColumn<AvailableStock, String> productName;

    @FXML
    private TableColumn<AvailableStock, String> purchasedPrice;

    @FXML
    private TableColumn<AvailableStock, String> quantity;

    @FXML
    private TableColumn<AvailableStock, String> sellingPrice;
	
	Store store;
	
	public void initData(Store s) {
		this.store = s;


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
	
	ObservableList<AvailableStock> getAllRows(String n){
		final ObservableList<AvailableStock> rows = FXCollections.observableArrayList();
		for(int i=0; i<availableStock.size(); i++) {
				String str1=n.toLowerCase();
				String str2=availableStock.get(i).getName().toLowerCase();
				if(str2.contains(str1)) {
					//System.out.println(availableStock.get(i).getName()+"  "+ n);
					AvailableStock row=new AvailableStock(availableStock.get(i).getName(), availableStock.get(i).getCategory().getName(), String.valueOf(availableStock.get(i).getQuantity()), String.valueOf(availableStock.get(i).getSellingPrice()));
					rows.add(row);
				}
				
		}
		return rows;
	}
	

    @FXML
    void searchProduct(ActionEvent event) {

    	//System.out.println(item_name.getText());
    	
    	final ObservableList<AvailableStock> rows=getAllRows(item_name.getText());
		
		table.getItems().clear();
			
		table.getItems().addAll(rows);
    }
    
    @FXML
    void handlekey(KeyEvent event) {
    	//System.out.println(item_name.getText());
    	
    	final ObservableList<AvailableStock> rows=getAllRows(item_name.getText());
		
		table.getItems().clear();
			
		table.getItems().addAll(rows);
    			
		
    }

}