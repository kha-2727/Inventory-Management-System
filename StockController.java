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

public class StockController implements Initializable {
	 @FXML
    private Tab addStockTab;

    @FXML
    private Tab availableStockTab;

    @FXML
    private Tab deleteStockTab;

	Store store;
	    
    
    public void initData(Store s) {
    	this.store=s;
    	
    	try {
    		FXMLLoader loader1=new FXMLLoader(getClass().getResource("AvailableStock.fxml"));
	    	Node node1=(Node) loader1.load();
			AvailableStockController controller1= loader1.getController();
			controller1.initData(this.store);
    	
			FXMLLoader loader2=new FXMLLoader(getClass().getResource("StockIn.fxml"));
	    	Node node2=(Node) loader2.load();
			StockInController controller2= loader2.getController();
			controller2.initData(this.store);
			
			FXMLLoader loader3=new FXMLLoader(getClass().getResource("StockOut.fxml"));
	    	Node node3=(Node) loader3.load();
			StockOutController controller3= loader3.getController();
			controller3.initData(this.store);
    	
    	
			availableStockTab.setContent(node1);			
			addStockTab.setContent(node2);
			deleteStockTab.setContent(node3);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
    	
    }

}
