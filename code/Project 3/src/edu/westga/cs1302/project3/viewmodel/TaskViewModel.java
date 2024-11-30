package edu.westga.cs1302.project3.viewmodel;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;
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
