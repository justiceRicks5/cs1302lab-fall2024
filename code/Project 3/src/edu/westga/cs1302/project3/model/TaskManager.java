package edu.westga.cs1302.project3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * stores a list of task and manages when you ad dor remover one
 * 
 * @author justice ricks
 * @version Fall 2024
 * 
 */
public class TaskManager {
	private List<Task> tasks;
	private Map<String, Task> taskLookup;

	/**
	 * intilazes the arrayList
	 */
	public TaskManager() {
		this.tasks = new ArrayList<Task>();
		   this.taskLookup = new HashMap<>();
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
		    Task existingTask = this.taskLookup.get(task.getTitle());
		    if (existingTask != null && existingTask.getDescription().equals(task.getDescription())) {
		        throw new IllegalArgumentException("Duplicate task with the same title and description.");
		    }
		    this.tasks.add(task);
		    this.taskLookup.put(task.getTitle(), task);
	}

	/**
	 * removes task from the task list
	 * 
	 * @param task task a description and title of a assignment someone is assigned
	 *             to do
	 */
	public void removeTask(Task task) {
		if (task == null) {
	        throw new IllegalArgumentException();
		}
		this.tasks.remove(task);
	    this.taskLookup.remove(task.getTitle());
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
