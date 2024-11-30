package edu.westga.cs1302.project3.view;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;
import javafx.fxml.FXML;
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
}
