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

public class ProductController implements Initializable {


	 @FXML
    private Tab addProductTab;

    @FXML
    private Tab deleteProductTab;

    @FXML
    private Tab searchProductTab;

    @FXML
    private Tab updateProductTab;
    
    Store store;
    
    
    public void initData(Store s) {
    	this.store=s;
    	
    	for(int i=0; i<this.store.getProductCatalog().getCategories().size(); i++) {
    		System.out.println(this.store.getProductCatalog().getCategories().get(i).getId()+": "+this.store.getProductCatalog().getCategories().get(i).getName());
    	}
    	
    	try {
	    	FXMLLoader loader1=new FXMLLoader(getClass().getResource("AddProduct.fxml"));
	    	Node node1=(Node) loader1.load();
			AddProductController controller1= loader1.getController();
			controller1.initData(this.store);
			
			FXMLLoader loader2=new FXMLLoader(getClass().getResource("UpdateProduct.fxml"));
			Node node2=(Node) loader2.load();
			UpdateProductController controller2= loader2.getController();
			controller2.initData(this.store);
			
			FXMLLoader loader3=new FXMLLoader(getClass().getResource("DeleteProduct.fxml"));
			Node node3=(Node) loader3.load();
			DeleteProductController controller3= loader3.getController();
			controller3.initData(this.store);
		
			FXMLLoader loader4=new FXMLLoader(getClass().getResource("SearchProduct.fxml"));
			Node node4=(Node) loader4.load();
			SearchProductController controller4= loader4.getController();
			controller4.initData(this.store);
		
    	
			addProductTab.setContent(node1);
			deleteProductTab.setContent(node3);
			updateProductTab.setContent(node2);
			searchProductTab.setContent(node4);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
    }


 
}

