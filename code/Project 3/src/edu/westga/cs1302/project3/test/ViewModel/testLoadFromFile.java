package edu.westga.cs1302.project3.test.ViewModel;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project3.model.Task;
import edu.westga.cs1302.project3.viewmodel.TaskViewModel;

class testLoadFromFile {

	private TaskViewModel viewModel;

    @BeforeEach
    public void setUp() {
        this.viewModel = new TaskViewModel();
    }

    @Test
    public void testLoadValidFile() throws IOException {
        File tempFile = File.createTempFile("tasks", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Task 1,Description 1\n");
            writer.write("Task 2,Description 2\n");
        }

        this.viewModel.loadTasksFromFile(tempFile);

        List<Task> tasks = this.viewModel.getTasks();
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Description 1", tasks.get(0).getDescription());
    }

    @Test
    public void testLoadInvalidFile() {
        File invalidFile = new File("nonexistent.txt");

        assertThrows(IOException.class, () -> this.viewModel.loadTasksFromFile(invalidFile));
    }
}
