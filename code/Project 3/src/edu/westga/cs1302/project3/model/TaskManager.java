package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.List;

/**
 * stores a list of task and manages when you ad dor remover one
 * 
 * @author justice ricks
 * @version Fall 2024
 * 
 */
public class TaskManager {
	private List<Task> tasks;

	/**
	 * intilazes the arrayList
	 */
	public TaskManager() {
		this.tasks = new ArrayList<Task>();
	}

	/**
	 * adds task onto the task List
	 * 
	 * @param task task a description and title of a assignment someone is assigned
	 *             to do
	 */
	public void addTask(Task task) {
		if (task == null) {
			throw new IllegalArgumentException("Task cannot be null.");
		}
		this.tasks.add(task);
	}

	/**
	 * removes task from the task list
	 * 
	 * @param task task a description and title of a assignment someone is assigned
	 *             to do
	 */
	public void removeTask(Task task) {
		this.tasks.remove(task);
	}

	/**
	 * a getter for a task
	 * 
	 * @return a list of task
	 */
	public List<Task> getTasks() {
		return this.tasks;
	}
}
