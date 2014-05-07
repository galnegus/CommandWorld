package kth.inda13.commandWorld.gui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
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
	private Label inputLabel;
	@FXML
	private Label responseLabel;

	@FXML
	private TextField inputTextField;

	@FXML
	private Button inputSubmitButton;

	/**
	 * This method is called when the window is initializing.
	 */
	@FXML
	private void initialize() {
		world = new World(outputImagePane);
		parser = new Parser(world);
	}
	
	/**
	 * helpButton is called when the "?" button is pressed.
	 * It opens a new window containing instructions for CommandWorld.
	 */
	@FXML
	private void helpButton() {
		Stage helpWindow = new Stage();
		helpWindow.setResizable(false);
		helpWindow.setTitle("CommandWorld Help Window");
		
		StackPane root = new StackPane();
		Label label = new Label("HELP");
		root.getChildren().add(label);

		Scene scene = new Scene(root, 300, 500);
		//scene.getStylesheets().add(getClass().getResource("help.css").toExternalForm());

		helpWindow.setScene(scene);
		helpWindow.show();
		
		// why not?
		inputTextField.requestFocus();
	}

	/**
	 * onSubmit is called either when enter is pressed while having the input text field focused,
	 * or when clicking the "Submit" button. It retrieves the input and passes it forward to the parser.
	 */
	@FXML
	private void onSubmit() {
		String input = inputTextField.getText();
		String response = parser.Parse(input);
		if (!input.isEmpty()) {
			// remove old input
			inputTextField.clear();

			// print response & input
			inputLabel.setText(input);
			responseLabel.setText(response);
			
		}
		
		// this will return focus to the textfield if the submit button was clicked
		inputTextField.requestFocus();
	}
}
