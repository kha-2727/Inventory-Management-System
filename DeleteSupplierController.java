package application;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import buisnessLayer.Store;
import buisnessLayer.Supplier;
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

public class DeleteSupplierController {
	

		
	Store store;
	@FXML
	private Label Selectedsupplier;
	
	@FXML
	private MenuButton supplierMenu;
	
	
	
	@FXML
	void HandlesupplierMenu(ActionEvent event) {
		Selectedsupplier.setText(((MenuItem) event.getSource()).getText());
	
	}
	
	public void initData(Store s) {
		this.store = s;
		
		MenuItem menuItem1 = new MenuItem("");
		menuItem1.setOnAction(this::HandlesupplierMenu);
		menuItem1.setStyle("-fx-pref-height: 30px");
		supplierMenu.getItems().add(menuItem1);
		
		for (int i = 0; i < this.store.getSuppliers().size(); i++) {
			MenuItem menuItem = new MenuItem(this.store.getSuppliers().get(i).getId()+","+this.store.getSuppliers().get(i).getName()+"("+this.store.getSuppliers().get(i).getEmail()+")");
			menuItem.setOnAction(this::HandlesupplierMenu);
			menuItem.setStyle("-fx-pref-height: 30px");
			supplierMenu.getItems().add(menuItem);
		}
	}
	
	@FXML
	void DeleteSuuplier(ActionEvent event) {
		try {
		ArrayList<String> arr=new ArrayList<>(Arrays.asList(Selectedsupplier.getText().split(",")));
		String reqid=arr.get(0);
		Supplier temps=this.store.searchSupplier(Integer.parseInt(reqid));
		if (temps != null) {
					String result = this.store.DeleteSupplier(Integer.parseInt(reqid));

					if (result == null) {
						Alert a1 = new Alert(AlertType.NONE);

						a1.setAlertType(AlertType.INFORMATION);

						a1.setHeaderText("Successfully Deleted Supplier");

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

			a1.setHeaderText("Please Select a supplier to be Deleted");

			a1.show();
		}


	}

}
