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

public class SupplierController implements Initializable {


	 @FXML
    private Tab addSupplierTab;

    @FXML
    private Tab deleteSupplierTab;

    @FXML
    private Tab searchSupplierTab;

    @FXML
    private Tab updateSupplierTab;
    
	
	Store store;
	    
    
    public void initData(Store s) {
    	this.store=s;
    	
    	try {
	    	FXMLLoader loader1=new FXMLLoader(getClass().getResource("AddSupplier.fxml"));
	    	Node node1=(Node) loader1.load();
			AddSupplierController controller1= loader1.getController();
			controller1.initData(this.store);
			
			FXMLLoader loader2=new FXMLLoader(getClass().getResource("UpdateSupplier.fxml"));
			Node node2=(Node) loader2.load();
			UpdateSupplierController controller2= loader2.getController();
			controller2.initData(this.store);
			
			FXMLLoader loader3=new FXMLLoader(getClass().getResource("DeleteSupplier.fxml"));
			Node node3=(Node) loader3.load();
			DeleteSupplierController controller3= loader3.getController();
			controller3.initData(this.store);
		
		
    	
			addSupplierTab.setContent(node1);
			deleteSupplierTab.setContent(node3);
			updateSupplierTab.setContent(node2);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
    }


 
}

