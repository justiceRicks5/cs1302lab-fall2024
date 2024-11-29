package edu.westga.cs1302.project3.test.Task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;



public class TaskTest {

	@Test
	public void testValidTaskCreation() {
		String title = "Example Task";
		String description = "This is a valid task description.";

		Task task = new Task(title, description);

		assertEquals(title, task.getTitle());
		assertEquals(description, task.getDescription());
	}

	@Test
	public void testNullTitleThrowsException() {
		String title = null;
		String description = "Valid description";

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
		assertEquals("Title cannot be null or empty.", exception.getMessage());
	}

	@Test
	public void testEmptyTitleThrowsException() {
		String title = "";
		String description = "Valid description";

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
		assertEquals("Title cannot be null or empty.", exception.getMessage());
	}

	@Test
	public void testNullDescriptionThrowsException() {
		String title = "Valid Title";
		String description = null;

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
		assertEquals("Description cannot be null or empty.", exception.getMessage());
	}

	@Test
	public void testEmptyDescriptionThrowsException() {
		String title = "Valid Title";
		String description = "";

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Task(title, description);
		});
		assertEquals("Description cannot be null or empty.", exception.getMessage());
	}
}
