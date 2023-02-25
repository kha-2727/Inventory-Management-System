package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import buisnessLayer.Customer;
import buisnessLayer.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class DeleteCustomerController {
	
	Store store;
	
	@FXML
   private Label Selectedcustomer;

   @FXML
   private MenuButton customerMenu;
	
	@FXML
	void HandlecustomerMenu(ActionEvent event) {
		Selectedcustomer.setText(((MenuItem) event.getSource()).getText());
		

	}

	public void initData(Store s) {
		this.store = s;
		
		MenuItem menuItem1 = new MenuItem("");
		menuItem1.setOnAction(this::HandlecustomerMenu);
		menuItem1.setStyle("-fx-pref-height: 30px");
		customerMenu.getItems().add(menuItem1);
		
		for (int i = 0; i < this.store.getCustomers().size(); i++) {
			MenuItem menuItem = new MenuItem(this.store.getCustomers().get(i).getId()+","+this.store.getCustomers().get(i).getName()+"("+this.store.getCustomers().get(i).getEmail()+")");
			menuItem.setOnAction(this::HandlecustomerMenu);
			menuItem.setStyle("-fx-pref-height: 30px");
			customerMenu.getItems().add(menuItem);
		}
	}

	@FXML
	void DeleteCustomer(ActionEvent event) {
		try {
		ArrayList<String> arr=new ArrayList<>(Arrays.asList(Selectedcustomer.getText().split(",")));
		String reqid=arr.get(0);
		Customer tempc=this.store.searchCustomer(Integer.parseInt(reqid));
		if (tempc != null) {
			
	
					String result = this.store.DeleteCustomer(Integer.parseInt(reqid));

					if (result == null) {
						Alert a1 = new Alert(AlertType.NONE);

						a1.setAlertType(AlertType.INFORMATION);

						a1.setHeaderText("Successfully Deleted Customer");

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
		}
		catch(Exception e) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please Select a customer to be Deleted");

			a1.show();
		}


	}
}
