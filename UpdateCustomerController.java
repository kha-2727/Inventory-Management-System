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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class UpdateCustomerController {
	Store store;
	
	 @FXML
    private Label Selectedcustomer;

    @FXML
    private MenuButton customerMenu;
	
	@FXML
	void HandlecustomerMenu(ActionEvent event) {
		Selectedcustomer.setText(((MenuItem) event.getSource()).getText());
		
		ArrayList<String> arr=new ArrayList<>(Arrays.asList(Selectedcustomer.getText().split(",")));
		String reqid=arr.get(0);
		Customer tempc=this.store.searchCustomer(Integer.parseInt(reqid));
		
		name.setText(tempc.getName());
		address.setText(tempc.getAddress());
		email.setText(tempc.getEmail());
		phone.setText(tempc.getPhone());
		zip.setText(tempc.getZip());

	}

	public void initData(Store s) {
		this.store = s;
	
		
		for (int i = 0; i < this.store.getCustomers().size(); i++) {
			MenuItem menuItem = new MenuItem(this.store.getCustomers().get(i).getId()+","+this.store.getCustomers().get(i).getName()+"("+this.store.getCustomers().get(i).getEmail()+")");
			menuItem.setOnAction(this::HandlecustomerMenu);
			menuItem.setStyle("-fx-pref-height: 30px");
			customerMenu.getItems().add(menuItem);
		}
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
	void updateCustomer(ActionEvent event) {
		try {
		ArrayList<String> arr=new ArrayList<>(Arrays.asList(Selectedcustomer.getText().split(",")));
		String reqid=arr.get(0);
		Customer tempc=this.store.searchCustomer(Integer.parseInt(reqid));
		if(tempc!=null) {
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
				String result = this.store.UpdateCustomer(tempc.getId(), c);
	
				if (result == null) {
					Alert a1 = new Alert(AlertType.NONE);
	
					a1.setAlertType(AlertType.INFORMATION);
	
					a1.setHeaderText("Successfully updated customer");
	
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
		catch (Exception e){
			Alert a1 = new Alert(AlertType.NONE);
			
			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please select a customer to be updated!!!");

			a1.show();
		}
	}	
}
