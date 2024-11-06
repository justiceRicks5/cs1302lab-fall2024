package edu.westga.cs1302.password_generator.view;

import edu.westga.cs1302.password_generator.ViewModel.PasswordGeneratorViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
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
	private TextArea output;

	private PasswordGeneratorViewModel viewModel;

	@FXML
	void generatePassword(ActionEvent event) {
		int minimumLength;
		try {
			minimumLength = Integer.parseInt(this.minimumLength.getText());
			this.viewModel.minimumLengthProperty().set(minimumLength); 
		} catch (NumberFormatException numberError) {
			this.showAlert("Invalid Minimum Length", "Minimum length must be a positive integer.");
			return;
		}

		this.viewModel.generatePassword();
	}

	@FXML
	void initialize() {
		this.viewModel = new PasswordGeneratorViewModel();

		this.mustIncludeDigits.selectedProperty().bindBidirectional(this.viewModel.mustHaveAtLeastOneDigitProperty());
		this.mustIncludeLowerCaseLetters.selectedProperty()
				.bindBidirectional(this.viewModel.mustHaveAtLeastOneLowerCaseLetterProperty());
		this.mustIncludeUpperCaseLetters.selectedProperty()
				.bindBidirectional(this.viewModel.mustHaveAtLeastOneUpperCaseLetterProperty());

		Bindings.bindBidirectional(this.minimumLength.textProperty(), this.viewModel.minimumLengthProperty(),
				new javafx.util.converter.NumberStringConverter());

		this.output.textProperty().bind(this.viewModel.generatedPasswordProperty());
	}

	/**
	 * Displays an alert with the specified title and content message.
	 *
	 * @param title   the title of the alert
	 * @param content the content message of the alert
	 */
	private void showAlert(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.show();
	}
}
