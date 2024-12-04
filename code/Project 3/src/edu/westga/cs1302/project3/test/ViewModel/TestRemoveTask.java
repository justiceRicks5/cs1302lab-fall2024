package edu.westga.cs1302.project3.test.ViewModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

class TestRemoveTask {

	private TaskViewModel viewModel;

	@BeforeEach
	public void setUp() {
		this.viewModel = new TaskViewModel();
		this.viewModel.getTasks().clear();
	}

	/**
	 * Tests removing a selected task when a valid task is selected.
	 */
	@Test
	public void testRemoveSelectedTaskValid() {
		Task task1 = new Task("Task 1", "Description 1");
		Task task2 = new Task("Task 2", "Description 2");
		this.viewModel.getTasks().addAll(task1, task2);

		this.viewModel.selectedTaskProperty().set(task1);
		this.viewModel.removeSelectedTask();

		assertEquals(1, this.viewModel.getTasks().size());
		assertFalse(this.viewModel.getTasks().contains(task1));
		assertTrue(this.viewModel.getTasks().contains(task2));
	}

	/**
	 * Tests removing a task when no task is selected.
	 */
	@Test
	public void testRemoveSelectedTaskWhenNoneSelected() {
		Task task1 = new Task("Task 1", "Description 1");
		this.viewModel.getTasks().add(task1);

		this.viewModel.selectedTaskProperty().set(null);
		this.viewModel.removeSelectedTask();

		assertEquals(1, this.viewModel.getTasks().size());
		assertTrue(this.viewModel.getTasks().contains(task1));
	}

	/**
	 * Tests removing a task when the selected task is not in the list.
	 */
	@Test
	public void testRemoveSelectedTaskNotInList() {
		Task task1 = new Task("Task 1", "Description 1");
		Task taskNotInList = new Task("Nonexistent Task", "Nonexistent Description");
		this.viewModel.getTasks().add(task1);

		this.viewModel.selectedTaskProperty().set(taskNotInList);
		this.viewModel.removeSelectedTask();

		assertEquals(1, this.viewModel.getTasks().size());
		assertTrue(this.viewModel.getTasks().contains(task1));
	}

}
