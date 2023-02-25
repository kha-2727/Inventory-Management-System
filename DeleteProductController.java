package application;

import java.io.IOException;

import buisnessLayer.Product;
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DeleteProductController {
	
	@FXML
	private Label Selectedproduct;

	@FXML
	private MenuButton productMenu;
	
	Store store;
	@FXML
	void HandleproductMenu(ActionEvent event) {
		Selectedproduct.setText(((MenuItem) event.getSource()).getText());

	}

	public void initData(Store s) {
		this.store = s;
		
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
	void DeleteProduct(ActionEvent event) {
		Product tempP = this.store.searchProduct(Selectedproduct.getText());
		if (tempP != null) {
			
	
					String result = this.store.DeleteProduct(tempP.getId());

					if (result == null) {
						Alert a1 = new Alert(AlertType.NONE);

						a1.setAlertType(AlertType.INFORMATION);

						a1.setHeaderText("Successfully Deleted Product");

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

					} else {
						Alert a1 = new Alert(AlertType.NONE);

						a1.setAlertType(AlertType.ERROR);

						a1.setHeaderText(result);

						a1.show();
					}
				
			

		}
		else {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please Select a product to be Deleted");

			a1.show();
		}


	}
}
