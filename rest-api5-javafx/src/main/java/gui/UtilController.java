package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UtilController {

	static Stage stage;
	
	public static void setStage(Stage stageOther) {
		stage = stageOther;
	}
	
	public static void changeScene(String fxmlFile) {
		Parent root = null;
		
		try {
			FXMLLoader loader = new FXMLLoader(UtilController.class.getResource(fxmlFile));
			root = loader.load();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		stage.setScene(new Scene(root, 600, 400));
		stage.show();
		
	}
}
