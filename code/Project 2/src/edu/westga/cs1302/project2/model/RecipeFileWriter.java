package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Utility.RecipeUtils;

/**
 * writes recipe to a file
 * 
 * @author justice ricks
 * @version Fall 2024
 */
public class RecipeFileWriter {
	/**
	 * Writes a recipe to the file if a recipe with the same name does not already
	 * exist.
	 * 
	 * @param recipe   the Recipe object to write to the file
	 * @param filePath the path to the file where recipes are stored
	 * @throws IllegalStateException if a recipe with the same name already exists
	 *                               in the file
	 * @throws NullPointerException  if the recipe or its name is null
	 */
	public static void writeRecipeToFile(Recipe recipe, String filePath)
			throws NullPointerException, IllegalStateException {
		if (recipe == null || recipe.getName() == null) {
			throw new NullPointerException("Recipe or recipe name cannot be null");
		}

		File file = new File(filePath);

		try {
			if (file.exists()) {
				try (Scanner scanner = new Scanner(file)) {
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						if (line.equals(recipe.getName())) {
							throw new IllegalStateException("Recipe already exists");
						}
					}
				}
			}

			try (FileWriter writer = new FileWriter(file, true)) {
				writer.write(recipe.getName() + "\n");
				writer.write(RecipeUtils.recipeToString(recipe) + "\n");
			}

		} catch (IOException error) {
			System.out.println("An error occurred while writing the recipe to the file: " + error.getMessage());
			error.printStackTrace();
		}
	}
}