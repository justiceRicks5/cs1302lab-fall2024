package edu.westga.cs1302.project3.model;

/**
 * a task implemented to storing task
 * 
 * @version Fall 2024
 * @author justice ricks
 * 
 */
public class StoringTask {

	private String title;
	private String description;

	public StoringTask(String title, String description) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty.");
		}
		if (description == null || description.isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or empty.");
		}
		this.title = title;
		this.description = description;
	}
}
