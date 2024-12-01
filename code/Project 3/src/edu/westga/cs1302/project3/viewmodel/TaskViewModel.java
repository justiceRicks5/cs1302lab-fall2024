package edu.westga.cs1302.project3.viewmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * view model that returns a list of task
 * 
 * @author justice ricks
 * 
 * @version Fall 2024
 * 
 * 
 */
public class TaskViewModel {
	private TaskManager taskManager;
	private ObservableList<Task> tasks;

	/**
	 * Creates a new ViewModel with default tasks.
	 * 
	 */
	public TaskViewModel() {
		this.taskManager = new TaskManager();
		this.tasks = FXCollections.observableArrayList(this.taskManager.getTasks());
		this.loadDefaultTasks();
	}

	private void loadDefaultTasks() {
		Task task1 = new Task("Buy groceries", "Get milk, eggs, and bread");
		Task task2 = new Task("Complete homework", "Finish the math assignment");
		Task task3 = new Task("Workout", "Go for a 30-minute run");

		this.taskManager.addTask(task1);
		this.taskManager.addTask(task2);
		this.taskManager.addTask(task3);

		this.tasks.setAll(this.taskManager.getTasks());
	}

	/**
	 * returns a list of task
	 * 
	 * 
	 * @return the observable list of task
	 */
	public ObservableList<Task> getTasks() {
		return this.tasks;
	}

	/**
	 * Loads tasks from the specified file and updates the task list.
	 *
	 * @param file the file to load tasks from
	 * @throws IOException if the file is not valid or cannot be read
	 */
	public void loadTasksFromFile(File file) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null.");
		}

		try {
			Task[] loadedTasks = TaskStorage.loadTasksFromFile(file);
			this.tasks.setAll(loadedTasks); 
		} catch (FileNotFoundException error) {
			throw new IOException("File not found: " + file.getAbsolutePath(), error);
		} catch (IOException error) {
			throw new IOException("Invalid file format: " + file.getAbsolutePath(), error);
		}
	}

	/**
	 * a method that adds task to the observable list
	 * 
	 * @param task the task to add
	 */
	public void addTask(Task task) {
		this.taskManager.addTask(task);
		this.tasks.setAll(this.taskManager.getTasks());
	}

	/**
	 * method that removes task from the observable list
	 * 
	 * @param task the task to remove
	 */
	public void removeTask(Task task) {
		this.taskManager.removeTask(task);
		this.tasks.setAll(this.taskManager.getTasks());
	}

}
