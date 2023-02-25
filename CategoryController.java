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

public class CategoryController implements Initializable {

    @FXML
    private Tab addCategoryTab;

    @FXML
    private Tab deleteCategoryTab;

    @FXML
    private Tab searchCategoryTab;

    @FXML
    private Tab updateCategoryTab;
    
    Store store;
	    
    
    public void initData(Store s) {
    	this.store=s;
    	
    	try {
	    	FXMLLoader loader1=new FXMLLoader(getClass().getResource("AddCategory.fxml"));
	    	Node node1=(Node) loader1.load();
			AddCategoryController controller1= loader1.getController();
			controller1.initData(this.store);
			
			FXMLLoader loader2=new FXMLLoader(getClass().getResource("UpdateCategory.fxml"));
			Node node2=(Node) loader2.load();
			updateCategoryController controller2= loader2.getController();
			controller2.initData(this.store);
			
			FXMLLoader loader3=new FXMLLoader(getClass().getResource("DeleteCategory.fxml"));
			Node node3=(Node) loader3.load();
			DeleteCategoryController controller3= loader3.getController();
			controller3.initData(this.store);
		
		
    	
			addCategoryTab.setContent(node1);
			deleteCategoryTab.setContent(node3);
			updateCategoryTab.setContent(node2);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
    	
    }

}
