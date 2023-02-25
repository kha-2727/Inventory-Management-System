package application;
	
import buisnessLayer.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
				Store s=new Store();
				FXMLLoader loader=new FXMLLoader(getClass().getResource("Main.fxml"));
				Parent root = (Parent) loader.load();
				MainController controller= loader.getController();
				controller.initData(s);
		        stage.setTitle("Inventory App");
		        stage.setScene(new Scene(root, 900, 600));
		        stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Application.launch(Main.class, args);

	}
}
