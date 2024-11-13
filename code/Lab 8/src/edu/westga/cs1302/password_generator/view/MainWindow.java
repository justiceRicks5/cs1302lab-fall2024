package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * MainWindow class manages the UI interactions for the PasswordGenerator
 * application. It binds UI components to properties in the ViewModel and
 * handles user actions.
 * 
 * @author jr00381
 * @version Fall 2024
 */
public class MainWindow {

	@FXML
	private CheckBox mustIncludeDigits;
	@FXML
	private CheckBox mustIncludeLowerCaseLetters;
	@FXML
	private CheckBox mustIncludeUpperCaseLetters;
	@FXML
	private TextField minimumLength;
	@FXML
	private ListView<String> passwordListView;
	@FXML
	private Label errorTextLabel;
	@FXML
	private Button generatePasswordButton;

	private ViewModel vm;

	/**
	 * Initializes the ViewModel, binds UI components to ViewModel properties, and
	 * sets up event handling for user interactions.
	 */
	@FXML
	void initialize() {
		this.vm = new ViewModel();

		this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
		this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
		this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());

		this.minimumLength.textProperty().bindBidirectional(this.vm.getMinimumLength());

		this.errorTextLabel.textProperty().bind(this.vm.getErrorText());

		this.passwordListView.setItems(this.vm.getPasswordList());

		this.generatePasswordButton.setOnAction(event -> this.vm.generatePassword());
	}
}
