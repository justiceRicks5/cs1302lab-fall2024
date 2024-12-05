package edu.westga.cs1302.project3.test.ViewModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

class TestAddTask {

	private TaskViewModel viewModel;

	@BeforeEach
	public void setUp() {
		this.viewModel = new TaskViewModel();
		 this.viewModel.getTasks().clear();
	}

	/**
	 * Tests adding a valid task.
	 */
	@Test
	public void testAddValidTask() {
		this.viewModel.taskTitleProperty().set("New Task");
		this.viewModel.taskDescriptionProperty().set("New Task Description");

		this.viewModel.addTask();

		assertEquals(1, this.viewModel.getTasks().size());
		Task addedTask = this.viewModel.getTasks().get(0);
		assertEquals("New Task", addedTask.getTitle());
		assertEquals("New Task Description", addedTask.getDescription());

		assertEquals("", this.viewModel.taskTitleProperty().get());
		assertEquals("", this.viewModel.taskDescriptionProperty().get());
	}

	/**
	 * Tests adding a task with a null title.
	 */
	@Test
	public void testAddTaskWithNullTitle() {
		this.viewModel.taskTitleProperty().set(null);
		this.viewModel.taskDescriptionProperty().set("Valid Description");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> this.viewModel.addTask());
		assertEquals("Task title cannot be empty.", exception.getMessage());
	}

	/**
	 * Tests adding a task with an empty title.
	 */
	@Test
	public void testAddTaskWithEmptyTitle() {
		this.viewModel.taskTitleProperty().set("");
		this.viewModel.taskDescriptionProperty().set("Valid Description");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> this.viewModel.addTask());
		assertEquals("Task title cannot be empty.", exception.getMessage());
	}

	/**
	 * Tests adding a task with a null description.
	 */
	@Test
	public void testAddTaskWithNullDescription() {
		this.viewModel.taskTitleProperty().set("Valid Title");
		this.viewModel.taskDescriptionProperty().set(null);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> this.viewModel.addTask());
		assertEquals("Task description cannot be empty.", exception.getMessage());
	}

	/**
	 * Tests adding a task with an empty description.
	 */
	@Test
	public void testAddTaskWithEmptyDescription() {
		this.viewModel.taskTitleProperty().set("Valid Title");
		this.viewModel.taskDescriptionProperty().set("");

		Exception exception = assertThrows(IllegalArgumentException.class, () -> this.viewModel.addTask());
		assertEquals("Task description cannot be empty.", exception.getMessage());
	}
}
