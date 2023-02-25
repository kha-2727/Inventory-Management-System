package application;

import java.io.IOException;

import buisnessLayer.Customer;
import buisnessLayer.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddCustomerController {

	Store store;

	public void initData(Store s) {
		this.store = s;
	}

	@FXML
	private TextField address;

	@FXML
	private TextField email;

	@FXML
	private TextField name;

	@FXML
	private TextField phone;

	@FXML
	private TextField zip;
	
	private boolean isvalidEmail(String email1) {
		String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		if(email1.matches(regex)) {
			return true;
		}
		return false;
	}
	private boolean isvalidPhone(String phoneno) {
		String regex = "^[0]+[3]+\\d{9}";
		if(phoneno.matches(regex)) {
			return true;
		}
		return false;
	}

	@FXML
	void addCustomer(ActionEvent event) {
		if (name.getText() == null || name.getText().trim().isEmpty()) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Name is required!!!");

			a1.show();
		}
		else if (email.getText() == null || email.getText().trim().isEmpty() || !isvalidEmail(email.getText())) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please enter a valid email");

			a1.show();
		}  
		else if (phone.getText() == null || phone.getText().trim().isEmpty() || !isvalidPhone(phone.getText())) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please enter a valid phoneno");

			a1.show();
		}  
		else {
			Customer c = new Customer(name.getText(), phone.getText(), email.getText(), address.getText(),
					zip.getText());
			String result = this.store.AddCustomer(c);

			if (result == null) {
				Alert a1 = new Alert(AlertType.NONE);

				a1.setAlertType(AlertType.INFORMATION);

				a1.setHeaderText("Successfully Added Customer");

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
}
