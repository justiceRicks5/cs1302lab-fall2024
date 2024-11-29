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

	/**
	 * returns the title
	 * 
	 * @return title title summarizing the purpose of the task
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * sets the title
	 * 
	 * @param title title summarizing the purpose of the task
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * returns the description of the task
	 * 
	 * @return description giving detail about what is required for the task
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * sets the description of the task
	 * 
	 * @param  description giving detail about what is required for the
	 *                    task
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
