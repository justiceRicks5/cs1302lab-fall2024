package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;

/**
 * Codebehind for the Main Window of the application.
 * 
 * @version Fall 2024
 * @author justice ricks
 */
public class MainWindow {

	private TaskViewModel viewModel;

	@FXML
	private ListView<Task> taskListView;

	/**
	 * Initializes the Main Window.
	 */
	@FXML
	public void initialize() {
		this.viewModel = new TaskViewModel();

		this.taskListView.setItems(this.viewModel.getTasks());

		this.taskListView.setCellFactory(listView -> new javafx.scene.control.ListCell<>() {
			@Override
			protected void updateItem(Task task, boolean empty) {
				super.updateItem(task, empty);
				if (empty || task == null) {
					setText(null);
				} else {
					setText(task.getTitle());
				}
			}
		});
	}

	/**
	 * Handles the "Load Tasks" menu item.
	 */
	@FXML
	public void handleLoadTasks() {
		File defaultFile = new File("tasks.txt");

		if (!defaultFile.exists()) {
			this.showErrorDialog("Error Loading Tasks", "Default task file not found: tasks.txt");
			return;
		}

		try {
			this.viewModel.loadTasksFromFile(defaultFile);
		} catch (IOException error) {
			this.showErrorDialog("Error Loading Tasks", "Failed to load tasks from file: " + error.getMessage());
		}
	}

	/**
	 * Displays an error dialog with the given title and message.
	 *
	 * @param title   the title of the error dialog
	 * @param message the error message
	 */
	private void showErrorDialog(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
