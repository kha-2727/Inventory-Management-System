package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import buisnessLayer.Customer;
import buisnessLayer.Order;
import buisnessLayer.Product;
import buisnessLayer.Store;
import buisnessLayer.purchasedProducts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class PlaceOrderController {

    @FXML
    private Label Selectedcustomer;

    @FXML
    private GridPane allProducts;

    @FXML
    private MenuButton customerMenu;

    @FXML
    private DatePicker date1;

    @FXML
    private GridPane itemcustomer;

    @FXML
    private TextField totalamount;
    
    ArrayList<purchasedProducts> products= new ArrayList<purchasedProducts>();
    
    Store store;
    
    int nrows=1;
    
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
		
		totalamount.setText("0");
		
		//date1.setValue(LocalDate.now());
		
		
		date1.setConverter(new StringConverter<LocalDate>() {
			String pattern="dd-MM-yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                	try {
                		
                		return dateFormatter.format(date);
                	}
                	catch(Exception e) {
                		return "";
                	}
                } else {
                    return "";
                }
				
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                	try {
                		
            			return LocalDate.parse(string, dateFormatter);
            		}
            		catch(Exception e) {
            			
            			
            			return null;
            		}
                    
                } else {
                	
                    return null;
                }
            }
        });
		
		
		
		
		
	}
	 
	@FXML
	void checkDate(MouseEvent event) {
		try {
			String d = date1.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			System.out.println(d);
		}
		catch(Exception e) {
			System.out.println("Exception");
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.INFORMATION);

			a1.setHeaderText("Incorrect Date Format");

			a1.show();
		}
		
	}
	
	
	private boolean checkDateVal() {
		try {
			String d = date1.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			System.out.println(d);
		}
		catch(Exception e) {
			return false;
			
		}
		return true;
		
	}

    @FXML
    void addOrder(ActionEvent event) {
    	if(!this.checkDateVal()) {
    		Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.INFORMATION);

			a1.setHeaderText("Incorrect Date Format");

			a1.show();
    	}
    	else {
//    	for(int i=0; i<products.size(); i++) {
//    		System.out.println(products.get(i).getP().getName()+ "  "+products.get(i).getQuantity());
//    	}
    	
    	try {
    		ArrayList<String> arr=new ArrayList<>(Arrays.asList(Selectedcustomer.getText().split(",")));
    		String reqid=arr.get(0);
    		Customer tempc=this.store.searchCustomer(Integer.parseInt(reqid));
    		
    		if (tempc != null) {
    			
    			String res1=this.store.checkProducts(products);
    			
    			if(res1==null) {
    				Order o= new Order(tempc, products, date1.getValue());
    				
    				this.store.AddOrder(o);

					Alert a1 = new Alert(AlertType.NONE);

					a1.setAlertType(AlertType.INFORMATION);

					a1.setHeaderText("Successfully Added Order");

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

    				a1.setHeaderText(res1);

    				a1.show();
    			}
    		}
    	}
		catch(Exception e) {
			Alert a1 = new Alert(AlertType.NONE);

			a1.setAlertType(AlertType.ERROR);

			a1.setHeaderText("Please Select a customer");

			a1.show();
		}
    	}
    }
    
    @FXML
    void calculatetotalCost() {
    	Double total=this.store.calculateTotalCostofOrder(products);
    	totalamount.setText(Double.toString(total));
    	
    }

    @FXML
    void addNewRow(ActionEvent event) {
    	Product p1=null;
    	int q1=0;
    	purchasedProducts p = new purchasedProducts(p1, q1);
    	products.add(p);
    	nrows++;
    	Label templabel=new Label();
    	allProducts.add(templabel, 0, nrows);
    	nrows++;
    	
    	GridPane gridpane1= new GridPane();
    	gridpane1.setId("itemProduct");
    	Label label1=new Label();
    	label1.setId("SelectedProduct");
    	MenuButton menu1= new MenuButton();
    	gridpane1.add(label1, 0, 0);
    	gridpane1.add(menu1, 1, 0);
    	
    	
		
		TextField field1=new TextField();
		field1.setText("0");
    	TextField field2=new TextField();
    	field2.setEditable(false);
    	field2.setText("0.0");
    	TextField field3=new TextField();
    	field3.setEditable(false);
    	field3.setText("0.0");
    	Button removebutton=new Button();
    	removebutton.setText("X");
    	removebutton.setAlignment(Pos.CENTER);
    	
    	removebutton.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		 public void handle(ActionEvent event) {
    			int thisrow=GridPane.getRowIndex(removebutton);
    			products.remove(thisrow/2-1);
    			allProducts.getChildren().remove(templabel);
    			allProducts.getChildren().remove(gridpane1);
    			allProducts.getChildren().remove(field1);
    			allProducts.getChildren().remove(field2);
    			allProducts.getChildren().remove(field3);
    			allProducts.getChildren().remove(removebutton);
    			
    			 	int i = thisrow;
    			    for (Node child : allProducts.getChildren()) 
    			    {
    			    	int nodeRowIndex = GridPane.getRowIndex(child);

    			        if(nodeRowIndex > i)
    			        {
    			            GridPane.setRowIndex(child, nodeRowIndex-2);
    			            
    			        }

    
    			      
    			    }
    			
    			nrows-=2;
    			calculatetotalCost();
    		}
    	});
    	
    	
    	
    	
    	field1.setOnKeyReleased(new EventHandler<KeyEvent>() {
    	    @Override
    	    public void handle(KeyEvent event) {
    	    	int thisrow=GridPane.getRowIndex(removebutton)/2-1;
    	    	Product p=store.searchProduct(label1.getText());
    	    	if(isInt(field1.getText()) && p!=null) {
    	    		//System.out.println(field1.getText());
    	    		field3.setText(Double.toString(p.getSellingPrice()*Integer.parseInt(field1.getText())));
    	    		purchasedProducts pp = new purchasedProducts(p, Integer.parseInt(field1.getText()));
    	    		products.set(thisrow,pp);
    	    	}
    	    	else {
    	    		field3.setText("0");
    	    		purchasedProducts pp = new purchasedProducts(p, 0);
    	    		products.set(thisrow,pp);
    	    		
    	    	}
    	    	calculatetotalCost();
    	    }
    	    
    	});

    	
    	MenuItem menuItem1 = new MenuItem("");
    	menuItem1.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override
    	    public void handle(ActionEvent event) {
    	    	int thisrow=GridPane.getRowIndex(removebutton)/2-1;
    	    	Product p=store.searchProduct(((MenuItem) event.getSource()).getText());
    	    	
    	    	if(p!=null) {
    	    		field2.setText(Double.toString(p.getSellingPrice()));
    	    		purchasedProducts pp = new purchasedProducts(p, Integer.parseInt(field1.getText()));
    	    		products.set(thisrow,pp);
    	    	}
    	    	else {
    	    		field2.setText("0.0");
    	    		purchasedProducts pp = new purchasedProducts(p, 0);
    	    		products.set(thisrow,pp);
    	    	}
    	    	label1.setText(((MenuItem) event.getSource()).getText());
    	    	if(isInt(field1.getText()) && p!=null) {
    	    		field3.setText(Double.toString(p.getSellingPrice()*Integer.parseInt(field1.getText())));
    	    		purchasedProducts pp = new purchasedProducts(p, Integer.parseInt(field1.getText()));
    	    		products.set(thisrow,pp);
    	    	}
    	    	else {
    	    		field3.setText("0.0");
    	    		purchasedProducts pp = new purchasedProducts(p,0);
    	    		products.set(thisrow,pp);
    	    	}
    	    	calculatetotalCost();
    	    }
    	});
	//	menuItem1.setOnAction(this::HandleproductMenu);
		menuItem1.setStyle("-fx-pref-height: 30px");
		menu1.getItems().add(menuItem1);
		for (int i = 0; i < this.store.getProductCatalog().getProducts().size(); i++) {
			MenuItem menuItem = new MenuItem(this.store.getProductCatalog().getProducts().get(i).getName());
			menuItem.setOnAction(new EventHandler<ActionEvent>() {
	    	    

				@Override
	    	    public void handle(ActionEvent event) {
					int thisrow=GridPane.getRowIndex(removebutton)/2-1;
	    	    	Product p=store.searchProduct(((MenuItem) event.getSource()).getText());
	    	    	if(p!=null && isInt(field1.getText())) {
	    	    		field2.setText(Double.toString(p.getSellingPrice()));
	    	    		purchasedProducts pp = new purchasedProducts(p, Integer.parseInt(field1.getText()));
	    	    		products.set(thisrow,pp);
	    	    	}
	    	    	else {
	    	    		field2.setText("0.0");
	    	    		purchasedProducts pp = new purchasedProducts(p, 0);
	    	    		products.set(thisrow,pp);
	    	    	}
	    	    	label1.setText(((MenuItem) event.getSource()).getText());
	    	    	if(isInt(field1.getText()) && p!=null) {
	    	    		field3.setText(Double.toString(p.getSellingPrice()*Integer.parseInt(field1.getText())));
	    	    		purchasedProducts pp = new purchasedProducts(p, Integer.parseInt(field1.getText()));
	    	    		products.set(thisrow,pp);
	    	    	}
	    	    	else {
	    	    		field3.setText("0.0");
	    	    		purchasedProducts pp = new purchasedProducts(p, 0);
	    	    		products.set(thisrow,pp);
	    	    	}
	    	    	calculatetotalCost();
	    	    }
	    	});
		//	menuItem.setOnAction(this::HandleproductMenu);
			menuItem.setStyle("-fx-pref-height: 30px");
			menu1.getItems().add(menuItem);
		}
    	
    	allProducts.add(gridpane1, 0, nrows);
    	allProducts.add(field1, 1, nrows);
    	allProducts.add(field2, 2, nrows);
    	allProducts.add(field3, 3, nrows);
    	allProducts.add(removebutton, 4, nrows);
    	
    	GridPane.setHalignment(removebutton, HPos.CENTER);
    	
    
    	//System.out.println(allProducts.getChildren());
    }

    private static boolean isInt(String str) {
		try {
			int d = Integer.parseInt(str);
			if (d < 0) {
				return false;
			}

		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
