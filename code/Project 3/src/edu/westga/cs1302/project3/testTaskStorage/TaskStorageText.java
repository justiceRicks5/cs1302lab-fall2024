package edu.westga.cs1302.project3.testTaskStorage;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskStorage;

class TaskStorageText {

	/**
	 * Tests loading tasks from a valid file with properly formatted lines.
	 */
	@Test
	public void testLoadTasksFromValidFile() throws IOException {
		File tempFile = File.createTempFile("tasks", ".txt");
		try (FileWriter writer = new FileWriter(tempFile)) {
			writer.write("Task 1,Description 1\n");
			writer.write("Task 2,Description 2\n");
			writer.write("Task 3,Description 3\n");
		}

		Task[] tasks = TaskStorage.loadTasks(tempFile);

		assertNotNull(tasks);
		assertEquals(3, tasks.length);

		assertEquals("Task 1", tasks[0].getTitle());
		assertEquals("Description 1", tasks[0].getDescription());

		assertEquals("Task 2", tasks[1].getTitle());
		assertEquals("Description 2", tasks[1].getDescription());

		assertEquals("Task 3", tasks[2].getTitle());
		assertEquals("Description 3", tasks[2].getDescription());
	}

	/**
	 * Tests loading tasks from a file with an invalid format (e.g., missing a
	 * description).
	 */
	@Test
	public void testLoadTasksFromInvalidFileFormat() throws IOException {
		File tempFile = File.createTempFile("tasks_invalid", ".txt");
		try (FileWriter writer = new FileWriter(tempFile)) {
			writer.write("Task 1\n");
			writer.write("Task 2,Description 2\n");
		}

		IOException exception = assertThrows(IOException.class, () -> TaskStorage.loadTasks(tempFile));
		assertTrue(exception.getMessage().contains("Invalid format in file: Task 1"));
	}

	/**
	 * Tests loading tasks from an empty file.
	 */
	@Test
	public void testLoadTasksFromEmptyFile() throws IOException {
		File tempFile = File.createTempFile("tasks_empty", ".txt");

		Task[] tasks = TaskStorage.loadTasks(tempFile);

		assertNotNull(tasks);
		assertEquals(0, tasks.length);
	}

	/**
	 * Tests loading tasks from a file that does not exist.
	 */
	@Test
	public void testLoadTasksFromNonexistentFile() {
		File nonexistentFile = new File("nonexistent_tasks.txt");

		assertThrows(IOException.class, () -> TaskStorage.loadTasks(nonexistentFile));
	}

}
