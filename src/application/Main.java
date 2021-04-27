package application;
	
import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
	        Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/connectScreen.fxml"));
	        primaryStage.setTitle("Political Database");
	        Scene s1 = new Scene(root);
	        primaryStage.setScene(s1);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
