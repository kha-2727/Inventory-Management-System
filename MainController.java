package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import buisnessLayer.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainController implements Initializable {

	@FXML
	private Tab categoryTab;

	@FXML
	private Tab customerTab;

	@FXML
	private Tab employeeTab;

	@FXML
	private Tab faqTab;

	@FXML
	private Tab homeTab;

	@FXML
	private Tab orderTab;

	@FXML
	private Tab productTab;

	@FXML
	private Tab reviewTab;

	@FXML
	private Tab stockTab;

	@FXML
	private Tab supplierTab;

	@FXML
	private TabPane tabpane;

	Store store;

	public void initData(Store s) {

		this.store = s;

		try {

			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Product.fxml"));
			Node node1 = (Node) loader1.load();
			ProductController controller1 = loader1.getController();
			controller1.initData(this.store);

			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Category.fxml"));
			Node node2 = (Node) loader2.load();
			CategoryController controller2 = loader2.getController();
			controller2.initData(this.store);

			FXMLLoader loader4 = new FXMLLoader(getClass().getResource("Supplier.fxml"));
			Node node4 = (Node) loader4.load();
			SupplierController controller4 = loader4.getController();
			controller4.initData(this.store);

			FXMLLoader loader5 = new FXMLLoader(getClass().getResource("Customer.fxml"));
			Node node5 = (Node) loader5.load();
			CustomerController controller5 = loader5.getController();
			controller5.initData(this.store);

			FXMLLoader loader3 = new FXMLLoader(getClass().getResource("Stock.fxml"));
			Node node3 = (Node) loader3.load();
			StockController controller3 = loader3.getController();
			controller3.initData(this.store);
			
			FXMLLoader loader6 = new FXMLLoader(getClass().getResource("Review.fxml"));
			Node node6 = (Node) loader6.load();
			ReviewController controller6 = loader6.getController();
			controller6.initData(this.store);
			
			FXMLLoader loader7 = new FXMLLoader(getClass().getResource("PlaceOrder.fxml"));
			Node node7 = (Node) loader7.load();
			PlaceOrderController controller7 = loader7.getController();
			controller7.initData(this.store);
			
			productTab.setContent(node1);
			categoryTab.setContent(node2);
			stockTab.setContent(node3);
			supplierTab.setContent(node4);
			customerTab.setContent(node5);
			reviewTab.setContent(node6);
			orderTab.setContent(node7);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

	@FXML
	void handleProductButton(ActionEvent event) {
		tabpane.getSelectionModel().select(2);
	}

	@FXML
	void handleCategoryButton(ActionEvent event) {
		tabpane.getSelectionModel().select(1);
	}

	@FXML
	void handleStockButton(ActionEvent event) {
		tabpane.getSelectionModel().select(3);
	}

	@FXML
	void handleCustomerButton(ActionEvent event) {
		tabpane.getSelectionModel().select(5);
	}

	@FXML
	void handleSupplierButton(ActionEvent event) {
		tabpane.getSelectionModel().select(4);
	}

	@FXML
	void handleOrderButton(ActionEvent event) {
		tabpane.getSelectionModel().select(6);
	}

	

	@FXML
	void handleReviewButton(ActionEvent event) {
		tabpane.getSelectionModel().select(7);
	}

	
}
