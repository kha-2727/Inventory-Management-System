package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import buisnessLayer.Product;
import buisnessLayer.Store;
import buisnessLayer.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StockInController  implements Initializable {

    @FXML
    private Label Selectedproduct;

    @FXML
    private Label Selectedsupplier;

    @FXML
    private GridPane product;

    @FXML
    private MenuButton productMenu;

    @FXML
    private TextField purchased_price;

    @FXML
    private TextField quantity;

    @FXML
    private GridPane supplier;
    
    @FXML
    private TextArea reason;

    @FXML
    private MenuButton supplierMenu;
    
    Store store;
	    
    
    public void initData(Store s) {
    	this.store=s;
    	
    	MenuItem menuItem1 = new MenuItem("");
		menuItem1.setOnAction(this::HandleproductMenu);
		menuItem1.setStyle("-fx-pref-height: 30px");
		productMenu.getItems().add(menuItem1);
    	
    	for (int i = 0; i < this.store.getProductCatalog().getProducts().size(); i++) {
			MenuItem menuItem = new MenuItem(this.store.getProductCatalog().getProducts().get(i).getName());
			menuItem.setOnAction(this::HandleproductMenu);
			menuItem.setStyle("-fx-pref-height: 30px");
			productMenu.getItems().add(menuItem);
		}
    	
    	
    	MenuItem menuItem2 = new MenuItem("");
		menuItem2.setOnAction(this::HandlesupplierMenu);
		menuItem2.setStyle("-fx-pref-height: 30px");
		supplierMenu.getItems().add(menuItem2);
    	
    	for (int i = 0; i < this.store.getSuppliers().size(); i++) {
			MenuItem menuItem = new MenuItem(this.store.getSuppliers().get(i).getId()+","+this.store.getSuppliers().get(i).getName()+"("+this.store.getSuppliers().get(i).getEmail()+")");
			menuItem.setOnAction(this::HandlesupplierMenu);
			menuItem.setStyle("-fx-pref-height: 30px");
			supplierMenu.getItems().add(menuItem);
		}
    	purchased_price.setText("0");
    }

    @FXML
    void HandleproductMenu(ActionEvent event) {
    	Selectedproduct.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void HandlesupplierMenu(ActionEvent event) {
    	Selectedsupplier.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void addStock(ActionEvent event) {
    	if(Selectedproduct.getText() == null || Selectedproduct.getText().trim().isEmpty()) {
    		Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please Select a product");

			a1.show();
    	}
    	else if(!isInt(quantity.getText())){
    		Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please enter a positive integer for quantity");

			a1.show();
    	}
    	else if(reason.getText() == null || reason.getText().trim().isEmpty()) {
    		Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please enter a reason for adding stock");

			a1.show();
    	}
    	else {
    		Supplier s;
    		boolean isvalidsupplier=true;
    		if(Selectedsupplier.getText() == null || Selectedsupplier.getText().trim().isEmpty()) {
	    		s=null;
	    		
	    	}
    		else {
    			ArrayList<String> arr=new ArrayList<>(Arrays.asList(Selectedsupplier.getText().split(",")));
    			String reqid=arr.get(0);
    			s=this.store.searchSupplier(Integer.parseInt(reqid));
    			if(s==null) {
    				isvalidsupplier=false;
    			}
    			
    			
    		}
    		
	    	Product tempP=this.store.searchProduct(Selectedproduct.getText());
	    	if(tempP!=null) {
	    		if(isvalidsupplier==false) {
	    			Alert a1 = new Alert(AlertType.NONE);

					a1.setAlertType(AlertType.ERROR);

					a1.setHeaderText("Please select a valid supplier");

					a1.show();
	    		}
	    		else if(!isInt(purchased_price.getText())) {
    
	    			Alert a1 = new Alert(AlertType.NONE);

					a1.setAlertType(AlertType.ERROR);

					a1.setHeaderText("Please enter a poitive integer for price");

					a1.show();
	    		}
	    		else {
		    		String result=this.store.addStock(tempP,s,Integer.parseInt(purchased_price.getText()), Integer.parseInt(quantity.getText()), reason.getText());
		    		if(result==null) {
		    			Alert a1 = new Alert(AlertType.NONE);
	
						a1.setAlertType(AlertType.INFORMATION);
	
						a1.setHeaderText("Successfully added Stock");
	
						a1.show();
						
						Parent root;
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
							root = (Parent) loader.load();
							MainController controller = loader.getController();
							controller.initData(this.store);
	
							Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
							Scene scene = new Scene(root);
							stage.setScene(scene);
							stage.show();
						} catch (IOException e) {
							e.printStackTrace();
						}
		    		}
		    		else {
		    			Alert a1 = new Alert(AlertType.NONE);

						a1.setAlertType(AlertType.ERROR);

						a1.setHeaderText(result);

						a1.show();
		    		}
	    		}
	    		
	    	}
	    	else {
	    		Alert a1 = new Alert(AlertType.NONE);

				a1.setAlertType(AlertType.ERROR);

				a1.setHeaderText("Please Select a product");

				a1.show();
	    	}
    	}
    }
    
    private static boolean isInt(String str) {
		try {
			int d = Integer.parseInt(str);
			if (d <= 0) {
				return false;
			}

		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

}
