package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import buisnessLayer.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Tab;

public class CustomerController implements Initializable {


	 @FXML
    private Tab addCustomerTab;

    @FXML
    private Tab deleteCustomerTab;

    @FXML
    private Tab searchCustomerTab;

    @FXML
    private Tab updateCustomerTab;
    
    Store store;
	    
    
    public void initData(Store s) {
    	this.store=s;
    	
    	try {
	    	FXMLLoader loader1=new FXMLLoader(getClass().getResource("AddCustomer.fxml"));
	    	Node node1=(Node) loader1.load();
			AddCustomerController controller1= loader1.getController();
			controller1.initData(this.store);
			
			FXMLLoader loader2=new FXMLLoader(getClass().getResource("UpdateCustomer.fxml"));
			Node node2=(Node) loader2.load();
			UpdateCustomerController controller2= loader2.getController();
			controller2.initData(this.store);
			
			FXMLLoader loader3=new FXMLLoader(getClass().getResource("DeleteCustomer.fxml"));
			Node node3=(Node) loader3.load();
			DeleteCustomerController controller3= loader3.getController();
			controller3.initData(this.store);
		
		
    	
			addCustomerTab.setContent(node1);
			deleteCustomerTab.setContent(node3);
			updateCustomerTab.setContent(node2);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
    }


 
}

