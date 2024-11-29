package edu.westga.cs1302.project3.test.taskManager;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class testAddTask {

	@Test
	public void testAddValidTask() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Example Task", "This is an example task.");

		taskManager.addTask(task);

		assertEquals(1, taskManager.getTasks().size());
		assertTrue(taskManager.getTasks().contains(task));
	}

	@Test
	public void testAddNullTaskThrowsException() {
		TaskManager taskManager = new TaskManager();

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			taskManager.addTask(null);
		});
		assertEquals("Task cannot be null.", exception.getMessage());
	}

	@Test
	public void testAddMultipleTasks() {
		TaskManager taskManager = new TaskManager();
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");

		taskManager.addTask(task1);
		taskManager.addTask(task2);

		assertEquals(2, taskManager.getTasks().size());
		assertTrue(taskManager.getTasks().contains(task1));
		assertTrue(taskManager.getTasks().contains(task2));
	}
}
