package edu.westga.cs1302.project3.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

/**
 * Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {
	@FXML
	private TextArea taskDescriptionField;

	@FXML
	private TextField taskTitleField;

	@FXML
	void handleAddTask(ActionEvent event) {
		String title = this.taskTitleField.getText();
		String description = this.taskDescriptionField.getText();

		// Validate input
		if (title == null || title.isBlank() || description == null || description.isBlank()) {
			System.out.println("Task title and description cannot be empty.");
			return;
		}

		System.out.printf("Task Added: %s - %s%n", title, description);

		// Close the AddTaskWindow
		Stage stage = (Stage) this.taskTitleField.getScene().getWindow();
		stage.close();
	}

	@FXML
	void handleCancel(ActionEvent event) {
		Stage stage = (Stage) this.taskTitleField.getScene().getWindow();
		stage.close();
	}

}
