package edu.westga.cs1302.project3.test.ViewModel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

class TestSaveFile {

	 private TaskViewModel viewModel;

	    @BeforeEach
	    public void setUp() {
	        this.viewModel = new TaskViewModel();
	    }

	    @Test
	    public void testSaveToFile() throws IOException {
	        File tempFile = File.createTempFile("tasks", ".txt");
	        this.viewModel.getTasks().clear();


	        this.viewModel.getTasks().add(new Task("Task 1", "Description 1"));
	        this.viewModel.getTasks().add(new Task("Task 2", "Description 2"));

	        this.viewModel.saveTasksToFile(tempFile);

	        List<String> lines = Files.readAllLines(tempFile.toPath());
	        assertEquals(2, lines.size());
	        assertTrue(lines.contains("Task 1,Description 1"));
	        assertTrue(lines.contains("Task 2,Description 2"));
	    }

	    @Test
	    public void testSaveToLockedFile() throws IOException {
	        File lockedFile = File.createTempFile("locked_tasks", ".txt");
	        assertTrue(lockedFile.setReadOnly(), "Failed to make the file read-only");

	        this.viewModel.getTasks().clear();
	        this.viewModel.getTasks().add(new Task("Task 1", "Description 1"));

	        Exception exception = assertThrows(IOException.class, () -> this.viewModel.saveTasksToFile(lockedFile));
	        assertTrue(exception.getMessage().contains("Permission denied") || exception.getMessage().contains("Access is denied"));

	        lockedFile.setWritable(true);
	        lockedFile.delete();
	    }

}
