package edu.westga.cs1302.project3.test.taskManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.model.TaskManager;

class testAddTask {

	private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        this.taskManager = new TaskManager();
    }

    @Test
    void testAddValidTask() {
        Task task = new Task("Buy groceries", "Get milk, eggs, and bread");
        assertDoesNotThrow(() -> this.taskManager.addTask(task));
        assertEquals(1, this.taskManager.getTasks().size());
        assertTrue(this.taskManager.getTasks().contains(task));
    }

    @Test
    void testAddNullTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> this.taskManager.addTask(null));
    }

    @Test
    void testAddDuplicateTaskThrowsException() {
        Task task1 = new Task("Workout", "Go for a 30-minute run");
        Task task2 = new Task("Workout", "Go for a 30-minute run");

        this.taskManager.addTask(task1);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            this.taskManager.addTask(task2);
        });

        assertEquals("Duplicate task with the same title and description.", exception.getMessage());
        assertEquals(1, this.taskManager.getTasks().size());
    }

    @Test
    void testAddTaskWithSameTitleDifferentDescription() {
        Task task1 = new Task("Workout", "Go for a 30-minute run");
        Task task2 = new Task("Workout", "Do strength training");

        this.taskManager.addTask(task1);
        assertDoesNotThrow(() -> this.taskManager.addTask(task2));

        assertEquals(2, this.taskManager.getTasks().size());
        assertTrue(this.taskManager.getTasks().contains(task1));
        assertTrue(this.taskManager.getTasks().contains(task2));
    }

    @Test
    void testAddMultipleUniqueTasks() {
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        Task task3 = new Task("Task 3", "Description 3");

        assertDoesNotThrow(() -> this.taskManager.addTask(task1));
        assertDoesNotThrow(() -> this.taskManager.addTask(task2));
        assertDoesNotThrow(() -> this.taskManager.addTask(task3));

        assertEquals(3, this.taskManager.getTasks().size());
        assertTrue(this.taskManager.getTasks().contains(task1));
        assertTrue(this.taskManager.getTasks().contains(task2));
        assertTrue(this.taskManager.getTasks().contains(task3));
    }
}
