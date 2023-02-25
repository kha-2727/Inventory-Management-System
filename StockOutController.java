package application;

import java.io.IOException;

import buisnessLayer.Product;
import buisnessLayer.Stockout;
import buisnessLayer.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class StockOutController {
	@FXML
    private Label Selectedproduct;

    @FXML
    private GridPane itemProduct;

    @FXML
    private MenuButton productMenu;

    @FXML
    private TextField quantity;
    
    @FXML
    private TextArea reason;
    
    
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
    	
    }

    @FXML
    void HandleproductMenu(ActionEvent event) {
    	Selectedproduct.setText(((MenuItem) event.getSource()).getText());
    }

    @FXML
    void removeStock(ActionEvent event) {
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

			a1.setHeaderText("Please enter a reason for removing stock");

			a1.show();
    	}
    	else {
	    	Product tempP=this.store.searchProduct(Selectedproduct.getText());
	    	if(tempP!=null) {
	    		String result=this.store.removeStock(tempP, Integer.parseInt(quantity.getText()), reason.getText());
	    		if(result==null) {
	    			Alert a1 = new Alert(AlertType.NONE);

					a1.setAlertType(AlertType.INFORMATION);

					a1.setHeaderText("Successfully Removed Stock");

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
}
