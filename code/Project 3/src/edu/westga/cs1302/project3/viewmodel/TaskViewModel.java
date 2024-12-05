package edu.westga.cs1302.project3.viewmodel;

import java.io.File;
import java.io.IOException;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
import edu.westga.cs1302.project3.model.TaskStorage;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
	private ObservableList<Task> tasks;
	private ObjectProperty<Task> selectedTask;
	private StringProperty taskTitle;
	private StringProperty taskDescription;
	private TaskManager taskManager;

	/**
	 * Creates a new TaskViewModel with default tasks.
	 */
	public TaskViewModel() {
		this.taskManager = new TaskManager();
		this.tasks = FXCollections.observableArrayList();
		this.selectedTask = new SimpleObjectProperty<>();
		this.taskTitle = new SimpleStringProperty();
		this.taskDescription = new SimpleStringProperty();
		this.loadDefaultTasks();
	}

	private void loadDefaultTasks() {
		this.tasks.addAll(new Task("Buy groceries", "Get milk, eggs, and bread"),
				new Task("Complete homework", "Finish the math assignment"),
				new Task("Workout", "Go for a 30-minute run"));
	}

	/**
	 * gets the observable list of task
	 * 
	 * @return tasks a list of task
	 */
	public ObservableList<Task> getTasks() {
		return this.tasks;
	}

	/**
	 * Gets the selected task property.
	 * 
	 * 
	 * @return the property representing the currently selected task
	 */
	public ObjectProperty<Task> selectedTaskProperty() {
		return this.selectedTask;
	}

	/**
	 * Gets the task title property.
	 * 
	 * 
	 * @return the property representing the task title
	 */
	public StringProperty taskTitleProperty() {
		return this.taskTitle;
	}

	/**
	 * Gets the task description property.
	 * 
	 * 
	 * @return the property representing the task description
	 */
	public StringProperty taskDescriptionProperty() {
		return this.taskDescription;
	}

	/**
	 * Adds a new task to the task list using the current title and description
	 * properties.
	 */
	public void addTask() {
		if (this.taskTitle.get() == null || this.taskTitle.get().isEmpty()) {
			throw new IllegalArgumentException("Task title cannot be empty.");
		}
		if (this.taskDescription.get() == null || this.taskDescription.get().isEmpty()) {
			throw new IllegalArgumentException("Task description cannot be empty.");
		}

		Task newTask = new Task(this.taskTitle.get(), this.taskDescription.get());
		this.taskManager.addTask(newTask);
		this.tasks.add(newTask);

		this.taskTitle.set("");
		this.taskDescription.set("");
	}

	/**
	 * Removes the currently selected task from the task list.
	 */
	public void removeSelectedTask() {
		Task taskToRemove = this.selectedTask.get();
		if (taskToRemove != null) {
			this.taskManager.removeTask(taskToRemove);
			this.tasks.remove(taskToRemove);
			this.selectedTask.set(null);
		}
	}

	/**
	 * Loads tasks from the specified file and updates the task list.
	 *
	 * @param file the file to load tasks from
	 * @throws IOException if the file is invalid or cannot be read
	 */
	public void loadTasksFromFile(File file) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null.");
		}

		Task[] loadedTasks = TaskStorage.loadTasks(file);
		this.tasks.setAll(loadedTasks);
	}

	/**
	 * Saves the current tasks to the specified file.
	 *
	 * @param file the file to save tasks to
	 * @throws IOException if an error occurs while saving tasks
	 */
	public void saveTasksToFile(File file) throws IOException {
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null.");
		}

		Task[] taskArray = this.tasks.toArray(new Task[0]);
		TaskStorage.saveTasks(taskArray, file);
	}

}
