package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import buisnessLayer.Customer;
import buisnessLayer.Product;
import buisnessLayer.Review;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class addReviewController {

	@FXML
	private Label Selectedcustomer;

	@FXML
	private Label Selectedproduct;

	@FXML
	private MenuButton customerMenu;

	@FXML
	private GridPane itemProduct;

	@FXML
	private GridPane itemsupplier;

	@FXML
	private MenuButton productMenu;

	@FXML
	private TextArea review;

	@FXML
	void HandlecustomerMenu(ActionEvent event) {
		Selectedcustomer.setText(((MenuItem) event.getSource()).getText());
	}

	@FXML
	void HandleproductMenu(ActionEvent event) {
		Selectedproduct.setText(((MenuItem) event.getSource()).getText());
	}

	@FXML
	void addReview(ActionEvent event) {
		if (Selectedproduct.getText() == null || Selectedproduct.getText().trim().isEmpty()) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Product is required!!!...Please select a product");

			a1.show();
		}
		else if (Selectedcustomer.getText() == null || Selectedcustomer.getText().trim().isEmpty()) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Customer is required!!!...Please select a customer");

			a1.show();
		}
		else {
		try {
			ArrayList<String> arr = new ArrayList<>(Arrays.asList(Selectedcustomer.getText().split(",")));
			String reqid = arr.get(0);
			Customer tempc = this.store.searchCustomer(Integer.parseInt(reqid));
			if (tempc != null) {
				Product tempP = this.store.searchProduct(Selectedproduct.getText());
				if (tempP != null) {

					if (review.getText() == null || review.getText().trim().isEmpty()) {
						Alert a1 = new Alert(AlertType.NONE);

						a1.setAlertType(AlertType.ERROR);

						a1.setHeaderText("Review is required!!!");

						a1.show();
					} else {

							Review r= new Review(tempP, tempc, review.getText());
							this.store.AddReview(r);
						
							Alert a1 = new Alert(AlertType.NONE);

							a1.setAlertType(AlertType.INFORMATION);

							a1.setHeaderText("Successfully added your review");

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

				} else {
					Alert a1 = new Alert(AlertType.NONE);

					a1.setAlertType(AlertType.ERROR);

					a1.setHeaderText("Please select a product!!!");

					a1.show();
				}
			}
		} catch (Exception e) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please select a customer!!!");

			a1.show();
		}
		}
	}

	Store store;

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

		MenuItem menuItem2 = new MenuItem("");
		menuItem2.setOnAction(this::HandlecustomerMenu);
		menuItem2.setStyle("-fx-pref-height: 30px");
		customerMenu.getItems().add(menuItem2);
		
		for (int i = 0; i < this.store.getCustomers().size(); i++) {
			MenuItem menuItem = new MenuItem(
					this.store.getCustomers().get(i).getId() + "," + this.store.getCustomers().get(i).getName() + "("
							+ this.store.getCustomers().get(i).getEmail() + ")");
			menuItem.setOnAction(this::HandlecustomerMenu);
			menuItem.setStyle("-fx-pref-height: 30px");
			customerMenu.getItems().add(menuItem);
		}
	}
}
