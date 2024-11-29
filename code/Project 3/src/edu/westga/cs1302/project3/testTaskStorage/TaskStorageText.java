package edu.westga.cs1302.project3.testTaskStorage;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskStorage;

class TaskStorageText {

	private static final String DATA_FILE = "tasks.txt";

	@Test
	public void testSaveTasksSuccessfully() throws IOException {
		Task[] tasks = { new Task("Task 1", "Description 1"), new Task("Task 2", "Description 2"), null };

		TaskStorage.saveTasks(tasks);

		Path path = Path.of(DATA_FILE);
		assertTrue(Files.exists(path), "File should be created.");
		String fileContent = Files.readString(path);

		assertEquals("Task 1,Description 1" + System.lineSeparator() + "Task 2,Description 2" + System.lineSeparator(),
				fileContent, "File content should match the expected output.");

		Files.deleteIfExists(path);
	}

	@Test
	public void testSaveEmptyTaskArrayCreatesEmptyFile() throws IOException {
		Task[] tasks = new Task[0];

		TaskStorage.saveTasks(tasks);

		Path path = Path.of(DATA_FILE);
		assertTrue(Files.exists(path), "File should be created.");
		String fileContent = Files.readString(path);

		assertEquals("", fileContent, "File should be empty when no tasks are provided.");

		Files.deleteIfExists(path);
	}

	@Test
	public void testSaveTasksWithNullArrayThrowsException() {
		Task[] tasks = null;

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			TaskStorage.saveTasks(tasks);
		});
		assertEquals("Must provide an array of tasks", exception.getMessage());
	}

	@Test
	public void testLoadTasksSuccessfully() throws IOException {
		Path path = Path.of(DATA_FILE);
		Files.writeString(path,
				"Task 1,Description 1" + System.lineSeparator() + "Task 2,Description 2" + System.lineSeparator());

		Task[] loadedTasks = TaskStorage.loadTasks();

		assertEquals(2, loadedTasks.length, "Loaded task array should have 2 tasks.");
		assertEquals("Task 1", loadedTasks[0].getTitle());
		assertEquals("Description 1", loadedTasks[0].getDescription());
		assertEquals("Task 2", loadedTasks[1].getTitle());
		assertEquals("Description 2", loadedTasks[1].getDescription());

		Files.deleteIfExists(path);
	}

	@Test
	public void testLoadTasksWithInvalidFormatThrowsException() throws IOException {
		Path path = Path.of(DATA_FILE);
		Files.writeString(path, "InvalidTaskFormatWithoutComma");

		Exception exception = assertThrows(IOException.class, TaskStorage::loadTasks);
		assertTrue(exception.getMessage().contains("Missing or invalid title/description"),
				"Error message should indicate invalid format.");

		Files.deleteIfExists(path);
	}

	@Test
	public void testLoadTasksFromNonexistentFileThrowsException() {
		File file = new File(DATA_FILE);
		if (file.exists()) {
			file.delete();
		}

		Exception exception = assertThrows(FileNotFoundException.class, TaskStorage::loadTasks);
		assertTrue(exception.getMessage().contains(DATA_FILE), "Error message should include file name.");
	}

}
