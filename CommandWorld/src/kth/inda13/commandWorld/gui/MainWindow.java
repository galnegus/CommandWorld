package kth.inda13.commandWorld.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try{

			VBox vBox = new VBox(); //Root
			Scene scene = new Scene(vBox);
			
			HBox hBox0 = new HBox();
			Pane pane1 = new Pane();
			HBox hBox2 = new HBox();

			TextField input20 = new TextField();
			Button enter21 = new Button("Enter");
			
			
			//Place controls
			vBox.getChildren().addAll(hBox0, pane1, hBox2);
			hBox0.getChildren().addAll(new Label("This is where answers will be"));
			hBox2.getChildren().addAll(input20, enter21);
			HBox.setHgrow(input20, Priority.ALWAYS);
			
			//Setup - probably will be moved to a .css file later on.
			hBox0.setAlignment(Pos.CENTER);
			hBox2.setAlignment(Pos.CENTER);
			pane1.setPrefSize(800,400);
			
			primaryStage.setScene(scene);
			
			primaryStage.show();
		} catch(Exception e) { //Something has gone wrong making the window. 
			e.printStackTrace();
		}
	}

}
