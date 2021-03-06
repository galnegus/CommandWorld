package kth.inda13.commandWorld.gui;

import java.io.IOException;
import java.util.Arrays;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kth.inda13.commandWorld.data.Word;
import kth.inda13.commandWorld.logic.Parser;
import kth.inda13.commandWorld.logic.World;

public class MainWindowController {
	
	private World world;
	private Parser parser;

	@FXML
	private StackPane outputImagePane;
	
	@FXML
	private ImageView backgroundImage;
	
	@FXML
	private Label inputLabel;
	
	@FXML
	private Label responseLabel;

	@FXML
	private TextField inputTextField;

	@FXML
	private Button inputSubmitButton;
	
	@FXML
	private Button clearButton;

	/**
	 * This method is called when the window is initializing.
	 */
	@FXML
	private void initialize() {
		// Bind background image size to size of outputImagePane
		backgroundImage.fitWidthProperty().bind(outputImagePane.widthProperty());
		backgroundImage.fitHeightProperty().bind(outputImagePane.heightProperty());
		
		//Create world
		world = new World(outputImagePane);
		parser = new Parser(world);
		
		
		// Start with the text field focused
		Platform.runLater(new Runnable() {
	        @Override
	        public void run() {
	        	inputTextField.requestFocus();
	        }
	    });
	}
	
	/**
	 * helpButton is called when the "?" button is pressed.
	 * It opens a new window containing instructions for CommandWorld.
	 * @throws IOException 
	 */
	@FXML
	private void helpButton() {
		try {
			Stage helpWindow = new Stage();
			helpWindow.setResizable(false);
			helpWindow.setTitle("CommandWorld Help Window");
			
			VBox root;
			
			root = (VBox) FXMLLoader.load(getClass().getResource("HelpWindow.fxml"));
			
	
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("help.css").toExternalForm());
			helpWindow.setResizable(true);
	
			helpWindow.setScene(scene);
			helpWindow.show();
			
			// why not? 
			inputTextField.requestFocus();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * onSubmit is called either when enter is pressed while having the input text field focused,
	 * or when clicking the "Submit" button. It retrieves the input and passes it forward to the parser.
	 */
	@FXML
	private void onSubmit() {
		String input = inputTextField.getText();
		String response;
		if (!input.isEmpty()) {
			// Quit command
			if(input.equalsIgnoreCase("quit")) System.exit(0);
			if(input.equalsIgnoreCase("help")) helpButton();
			

			// remove old input
			inputTextField.clear();

			//Parse text and get reply
			response = parser.Parse(input);

			// print response & input
			inputLabel.setText(input);
			responseLabel.setText(response);

		}

		// this will return focus to the textfield if the submit button was used rather than text area + ENTER
		inputTextField.requestFocus();
	}
	
	/**
	 * onClear removes all entities from the world.
	 */
	@FXML
	private void onClear() {
		world.clear();
		
		inputTextField.requestFocus();
	}
}
