package edu.westga.cs1302.project3.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

/**
 * Codebehind for the Add Task Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class AddTaskWindow {
	private TaskViewModel viewModel;

	@FXML
	private TextField taskTitleField;
	@FXML
	private TextArea taskDescriptionField;

	/**
	 * intilazes the binding
	 * 
	 * @param viewModel the view model text
	 */
	public void setViewModel(TaskViewModel viewModel) {
		this.viewModel = viewModel;

		this.taskTitleField.textProperty().bindBidirectional(this.viewModel.taskTitleProperty());
		this.taskDescriptionField.textProperty().bindBidirectional(this.viewModel.taskDescriptionProperty());
	}

	/**
	 * Handles the "Add Task" button click event.
	 *
	 * @throws IllegalArgumentException if the task title or description is invalid
	 */
	@FXML
	public void handleAddTask() {
		try {
			this.viewModel.addTask();

			Stage stage = (Stage) this.taskTitleField.getScene().getWindow();
			stage.close();
		} catch (IllegalArgumentException error) {
			System.out.println(error.getMessage());
		}
	}

	/**
	 * Handles the "Cancel" button click event.
	 * 
	 */
	@FXML
	public void handleCancel() {
		Stage stage = (Stage) this.taskTitleField.getScene().getWindow();
		stage.close();
	}

}
