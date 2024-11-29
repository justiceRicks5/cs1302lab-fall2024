package edu.westga.cs1302.project3.model;

/**
 * a task implemented to storing task
 * 
 * @version Fall 2024
 * @author justice ricks
 * 
 */
public class Task {

	private String title;
	private String description;

	/**
	 * a two parameter constrtor that intializes the title and description
	 * 
	 * @param title       title summarizing the purpose of the task
	 * @param description description giving detail about what is required for the
	 *                    task
	 */
	public Task(String title, String description) {
		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Title cannot be null or empty.");
		}
		if (description == null || description.isEmpty()) {
			throw new IllegalArgumentException("Description cannot be null or empty.");
		}
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
