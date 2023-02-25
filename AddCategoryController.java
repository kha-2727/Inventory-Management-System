package application;

import java.io.IOException;

import buisnessLayer.Category;
import buisnessLayer.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddCategoryController {

	@FXML
	private TextArea cat_desc;


	@FXML
	private TextField cat_name;

	@FXML
	void addCategory(ActionEvent event) {
		
		if(cat_name.getText() == null || cat_name.getText().trim().isEmpty()) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Category name is required!!!");

			a1.show();
		} else {


			Category c = new Category(cat_name.getText(), cat_desc.getText());
			String result = this.store.AddCategory(c);

			if (result == null) {
				Alert a1 = new Alert(AlertType.NONE);

				a1.setAlertType(AlertType.INFORMATION);

				a1.setHeaderText("Successfully Added Category");

				a1.show();
				Parent root;
				try {
					FXMLLoader loader=new FXMLLoader(getClass().getResource("Main.fxml"));
					root = (Parent) loader.load();
					MainController controller= loader.getController();
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

	

	Store store;

	public void initData(Store s) {
		this.store = s;
	}



}
