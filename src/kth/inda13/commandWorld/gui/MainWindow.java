package kth.inda13.commandWorld.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			primaryStage.setTitle("CommandWorld");
			VBox root = (VBox) FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(true);
			
			primaryStage.show();
		} catch(Exception e) { //Something has gone wrong making the window. 
			e.printStackTrace();
		}
	}

}
