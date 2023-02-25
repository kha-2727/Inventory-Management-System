package application;


import buisnessLayer.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;

public class ReviewController {

	Store store;
	@FXML
	private Tab addReviewTab;
	
	@FXML
	private Tab viewReviewTab;
    
    public void initData(Store s) {
    	this.store=s;
    	
    	try {
    		FXMLLoader loader1=new FXMLLoader(getClass().getResource("AddReview.fxml"));
	    	Node node1=(Node) loader1.load();
			addReviewController controller1= loader1.getController();
			controller1.initData(this.store);
    	
			FXMLLoader loader2=new FXMLLoader(getClass().getResource("ViewReview.fxml"));
	    	Node node2=(Node) loader2.load();
			ViewReviewController controller2= loader2.getController();
			controller2.initData(this.store);
			
    	
			addReviewTab.setContent(node1);			
			viewReviewTab.setContent(node2);
			
    		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
