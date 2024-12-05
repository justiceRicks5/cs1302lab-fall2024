package edu.westga.cs1302.project3.test.taskManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestRemoveTask {

	private TaskManager taskManager;

	@BeforeEach
	void setUp() {
		this.taskManager = new TaskManager();
	}

	@Test
	void testRemoveExistingTask() {
		Task task = new Task("Buy groceries", "Get milk, eggs, and bread");
		this.taskManager.addTask(task);

		assertDoesNotThrow(() -> this.taskManager.removeTask(task));
		assertFalse(this.taskManager.getTasks().contains(task));
		assertEquals(0, this.taskManager.getTasks().size());
	}

	@Test
	void testRemoveTaskNotInList() {
		Task task1 = new Task("Workout", "Go for a 30-minute run");
		Task task2 = new Task("Study", "Prepare for math exam");

		this.taskManager.addTask(task1);

		assertDoesNotThrow(() -> this.taskManager.removeTask(task2));
		assertTrue(this.taskManager.getTasks().contains(task1));
		assertEquals(1, this.taskManager.getTasks().size());
	}

	@Test
	void testRemoveOneTaskFromMultiple() {
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");
		Task task3 = new Task("Task 3", "Description 3");

		this.taskManager.addTask(task1);
		this.taskManager.addTask(task2);
		this.taskManager.addTask(task3);

		assertDoesNotThrow(() -> this.taskManager.removeTask(task2));

		assertFalse(this.taskManager.getTasks().contains(task2));
		assertTrue(this.taskManager.getTasks().contains(task1));
		assertTrue(this.taskManager.getTasks().contains(task3));
		assertEquals(2, this.taskManager.getTasks().size());
	}

	@Test
	void testRemoveSameTaskTwice() {
		Task task = new Task("Repeat Task", "Do the same thing again");

		this.taskManager.addTask(task);
		this.taskManager.removeTask(task);

		assertDoesNotThrow(() -> this.taskManager.removeTask(task));
		assertFalse(this.taskManager.getTasks().contains(task));
		assertEquals(0, this.taskManager.getTasks().size());
	}
}
