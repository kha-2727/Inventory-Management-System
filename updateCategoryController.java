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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class updateCategoryController {

	@FXML
	private Label Selectedcategory;

	@FXML
	private MenuButton categoryMenu;

	@FXML
	private TextArea cat_desc;

	@FXML
	private TextField cat_name;

	Store store;

	@FXML
	void HandlecategoryMenu(ActionEvent event) {
		Selectedcategory.setText(((MenuItem) event.getSource()).getText());
		Category tempc = this.store.searchCategory(Selectedcategory.getText());
		
		cat_name.setText(tempc.getName());
		cat_desc.setText(tempc.getDescription());

	}

	public void initData(Store s) {
		this.store = s;

		for (int i = 0; i < this.store.getProductCatalog().getCategories().size(); i++) {
			MenuItem menuItem = new MenuItem(this.store.getProductCatalog().getCategories().get(i).getName());
			menuItem.setOnAction(this::HandlecategoryMenu);
			menuItem.setStyle("-fx-pref-height: 30px");
			categoryMenu.getItems().add(menuItem);
		}
	}

	@FXML
    void updateCategory(ActionEvent event) {
    	Category tempc = this.store.searchCategory(Selectedcategory.getText());
    	if(tempc!=null) {
    		if(cat_name.getText() == null || cat_name.getText().trim().isEmpty()) {
    			Alert a1 = new Alert(AlertType.NONE);

    			a1.setAlertType(AlertType.ERROR);

    			a1.setHeaderText("Category name is required!!!");

    			a1.show();
    		} else {
		    	Category c = new Category(cat_name.getText(), cat_desc.getText());
				String result = this.store.UpdateCategory(tempc.getId(),c);
		
				if (result == null) {
					Alert a1 = new Alert(AlertType.NONE);
		
					a1.setAlertType(AlertType.INFORMATION);
		
					a1.setHeaderText("Successfully Updated Category");
		
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
    	else {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please Select a category to be updated");

			a1.show();
		}
    	
	}
    

}
