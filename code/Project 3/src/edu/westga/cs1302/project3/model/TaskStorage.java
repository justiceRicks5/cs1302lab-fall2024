package edu.westga.cs1302.project3.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Loads and saves task from a file
 * 
 * @author justice ricks
 * @version fall 2024
 * 
 */
public class TaskStorage {
	private static final String DATA_FILE = "tasks.txt";

	/**
	 * Save the tasks!
	 * 
	 * Writes each task to DATA_FILE. Each task is written on a separate line. Each
	 * line uses the following format: title,description
	 * 
	 * @precondition tasks != null
	 * @postcondition none
	 * 
	 * @param tasks the set of tasks to save
	 * @throws IOException if an I/O error occurs
	 */
	public static void saveTasks(Task[] tasks) throws IOException {
		if (tasks == null) {
			throw new IllegalArgumentException("Must provide an array of tasks");
		}
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			if (tasks.length == 0) {
				writer.write("");
			} else {
				for (Task currTask : tasks) {
					if (currTask != null) {
						writer.write(currTask.getTitle().trim() + "," + currTask.getDescription().trim()
								+ System.lineSeparator());
					}
				}
			}
		}
	}

	/**
	 * Load the tasks!
	 * 
	 * Reads from DATA_FILE. File is assumed to use the same format described by
	 * saveTasks.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the set of tasks loaded
	 * @throws FileNotFoundException if the file at DATA_FILE location does not
	 *                               exist
	 * @throws IOException           if invalid or missing title/description is
	 *                               found when trying to create a task
	 */
	public static Task[] loadTasks() throws FileNotFoundException, IOException {
		List<Task> tasks = new ArrayList<>();
		File inputFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			for (int lineNumber = 1; reader.hasNextLine(); lineNumber++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",", 2);
				try {
					String title = parts[0];
					String description = parts[1];
					Task nextTask = new Task(title, description);
					tasks.add(nextTask);
				} catch (IndexOutOfBoundsException | IllegalArgumentException taskDataError) {
					throw new IOException(
							"Missing or invalid title/description on line " + lineNumber + " : " + strippedLine);
				}
			}
		}
		return tasks.toArray(new Task[0]);
	}

	/**
	 * Loads tasks from the specified file.
	 *
	 * @param file the file to load tasks from
	 * @return an array of tasks loaded from the file
	 * @throws IOException if the file cannot be read or is invalid
	 */
	public static Task[] loadTasksFromFile(File file) throws IOException {
		List<Task> tasks = new ArrayList<>();
		try (Scanner reader = new Scanner(file)) {
			for (int lineNumber = 1; reader.hasNextLine(); lineNumber++) {
				String line = reader.nextLine();
				String[] parts = line.split(",", 2);

				if (parts.length != 2) {
					throw new IOException("Invalid format on line " + lineNumber + ": " + line);
				}

				String title = parts[0].trim();
				String description = parts[1].trim();

				tasks.add(new Task(title, description));
			}
		}

		return tasks.toArray(new Task[0]);
	}

}
