package edu.westga.cs1302.project3.test.taskManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class TestRemoveTask {

	@Test
	public void testRemoveExistingTask() {
		TaskManager taskManager = new TaskManager();
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");
		taskManager.addTask(task1);
		taskManager.addTask(task2);

		taskManager.removeTask(task1);

		assertEquals(1, taskManager.getTasks().size());
		assertFalse(taskManager.getTasks().contains(task1));
		assertTrue(taskManager.getTasks().contains(task2));
	}

	@Test
	public void testRemoveNonExistentTaskDoesNothing() {
		TaskManager taskManager = new TaskManager();
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");
		taskManager.addTask(task1);

		taskManager.removeTask(task2); 
		assertEquals(1, taskManager.getTasks().size());
		assertTrue(taskManager.getTasks().contains(task1));
		assertFalse(taskManager.getTasks().contains(task2)); 
	}

	@Test
	public void testRemoveNullTaskDoesNothing() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Task 1", "Description 1");
		taskManager.addTask(task);

		taskManager.removeTask(null);

		assertEquals(1, taskManager.getTasks().size());
		assertTrue(taskManager.getTasks().contains(task));
	}

	@Test
	public void testRemoveLastTask() {
		TaskManager taskManager = new TaskManager();
		Task task = new Task("Task 1", "Description 1");
		taskManager.addTask(task);

		taskManager.removeTask(task);

		assertTrue(taskManager.getTasks().isEmpty());
	}
}
