package edu.westga.cs1302.project3.view;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	@FXML
	private Button addTaskButton;
	@FXML
	private Button removeTaskButton;

	/**
	 * Initializes the Main Window.
	 */
	@FXML
	public void initialize() {
		this.viewModel = new TaskViewModel();

		this.taskListView.setItems(this.viewModel.getTasks());

		this.removeTaskButton.disableProperty().bind(this.viewModel.selectedTaskProperty().isNull());

		this.addTaskButton.setOnAction(event -> this.handleAddTaskWindow());
		this.removeTaskButton.setOnAction(event -> this.handleRemoveTask());

		this.taskListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.viewModel.selectedTaskProperty().set(newValue);
			} else {
				this.viewModel.selectedTaskProperty().set(null);
			}
		});

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

		this.viewModel.selectedTaskProperty().set(null);

		this.taskListView.requestFocus();
	}

	/**
	 * Opens the Add Task window.
	 */
	@FXML
	public void handleAddTaskWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTaskWindow.fxml"));
			Scene scene = new Scene(loader.load());

			AddTaskWindow addTaskWindow = loader.getController();
			addTaskWindow.setViewModel(this.viewModel);

			Stage addTaskStage = new Stage();
			addTaskStage.setTitle("Add Task");
			addTaskStage.initModality(Modality.APPLICATION_MODAL);
			addTaskStage.setScene(scene);
			addTaskStage.showAndWait();
		} catch (IOException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Opening Add Task Window");
			alert.setHeaderText("An error occurred while opening the Add Task window.");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * removes task from the list
	 */
	@FXML
	public void handleRemoveTask() {
		this.viewModel.removeSelectedTask();
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
	 * Handles the "Save Tasks" menu item.
	 */
	@FXML
	public void handleSaveTasks() {
		File defaultFile = new File("tasks.txt");

		try {
			this.viewModel.saveTasksToFile(defaultFile);
			this.showInfoDialog("Save Successful",
					"Tasks were successfully saved to: " + defaultFile.getAbsolutePath());
		} catch (IOException error) {
			this.showErrorDialog("Error Saving Tasks", "Failed to save tasks: " + error.getMessage());
		}
	}

	/**
	 * Displays an information dialog with the given title and message.
	 *
	 * @param title   the title of the information dialog
	 * @param message the information message
	 */
	private void showInfoDialog(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
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
