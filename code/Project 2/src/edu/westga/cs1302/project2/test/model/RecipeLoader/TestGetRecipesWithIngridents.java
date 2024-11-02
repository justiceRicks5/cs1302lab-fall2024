package edu.westga.cs1302.project2.test.model.RecipeLoader;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeLoader;

class TestGetRecipesWithIngridents {

	private static final String DATA_FILE = "recipesData.txt";
	
	@AfterEach
    public void cleanUp() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            file.delete();
        }
	}

	@Test
	public void testGetRecipesWithIngredientInEmptyFile() throws IOException {
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("");
		}

		RecipeLoader loader = new RecipeLoader(DATA_FILE);
		List<Recipe> recipes = loader.getRecipesWithIngredient("Tomato");

		assertTrue(recipes.isEmpty(), "Expected no recipes with Tomato in an empty file.");
	}

	@Test
	public void testGetRecipesWithIngredientSingleMatch() throws IOException {
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Salad\nLettuce, Tomato\n\nPasta\nPasta, Olive Oil\n");
		}

		RecipeLoader loader = new RecipeLoader(DATA_FILE);
		List<Recipe> recipesWithTomato = loader.getRecipesWithIngredient("Tomato");

		assertEquals(1, recipesWithTomato.size(), "Expected one recipe with Tomato.");
		assertEquals("Salad", recipesWithTomato.get(0).getName(),
				"Recipe name should match the expected recipe containing Tomato.");
	}

	@Test
	public void testGetRecipesWithIngredientNoMatch() throws IOException {
		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Salad\nLettuce, Cucumber\n\nPasta\nOlive Oil, Garlic\n");
		}

		RecipeLoader loader = new RecipeLoader(DATA_FILE);
		List<Recipe> recipesWithTomato = loader.getRecipesWithIngredient("Tomato");

		assertTrue(recipesWithTomato.isEmpty(), "Expected no recipes with Tomato.");
	}

}
