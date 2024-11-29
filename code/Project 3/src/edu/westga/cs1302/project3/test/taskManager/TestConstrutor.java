package edu.westga.cs1302.project3.test.taskManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.TaskManager;

class TestConstrutor {

	  @Test
	    public void testConstructorInitializesEmptyTaskList() {
	        TaskManager taskManager = new TaskManager();

	        assertNotNull(taskManager.getTasks(), "Task list should not be null.");
	        assertTrue(taskManager.getTasks().isEmpty(), "Task list should be empty upon initialization.");
	    }

}
