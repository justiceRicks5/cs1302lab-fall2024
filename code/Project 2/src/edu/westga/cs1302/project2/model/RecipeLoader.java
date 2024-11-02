package edu.westga.cs1302.project2.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * class that loads recipes from files
 * 
 * 
 * @author justice Ricks
 * @version Fall 2024
 */

public class RecipeLoader {
	private String filePath;

	/**
	 * intilaize file path
	 * 
	 * @param filePath string that is supposed to represent out filepath /**
	 */
	public RecipeLoader(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * a simple getter for filePath
	 * 
	 * @return filePath the filePath to where we will write the file
	 */

	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * Loads all recipes from a file.
	 * 
	 * @return a list of Recipe objects, or an empty list if the file is not found
	 *         or empty
	 */
	public List<Recipe> loadRecipesFromFile() {
		List<Recipe> recipes = new ArrayList<>();
		File file = new File(this.filePath);

		if (!file.exists() || file.length() == 0) {
			return recipes;
		}

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String recipeName = scanner.nextLine();

				if (!scanner.hasNextLine()) {
					System.out.println("Warning: Missing ingredients line for recipe: " + recipeName);
					break;
				}
				String ingredientsLine = scanner.nextLine();

				Recipe recipe = new Recipe(recipeName);
				String[] ingredients = ingredientsLine.split(", ");
				for (String ingredientName : ingredients) {
					recipe.addIngredient(new Ingredient(ingredientName, "Unknown"));
				}
				recipes.add(recipe);

				if (scanner.hasNextLine()) {
					scanner.nextLine();
				}
			}
		} catch (IOException error) {
			error.printStackTrace();
		}

		return recipes;
	}

	/**
	 * Gets a list of recipes that contain the specified ingredient.
	 * 
	 * @param ingredientName the name of the ingredient to search for
	 * @return a list of recipes containing the specified ingredient, or an empty
	 *         list if none found
	 */
	public List<Recipe> getRecipesWithIngredient(String ingredientName) {
		List<Recipe> allRecipes = this.loadRecipesFromFile();
		List<Recipe> filteredRecipes = new ArrayList<>();

		for (Recipe recipe : allRecipes) {
			for (Ingredient ingredient : recipe.getIngredients()) {
				if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
					filteredRecipes.add(recipe);
					break;
				}
			}
		}

		return filteredRecipes;
	}

}
