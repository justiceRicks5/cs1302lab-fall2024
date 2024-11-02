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

class TestLoader {

	private static final String DATA_FILE = "recipesData.txt";

	@AfterEach
	public void cleanUp() {
		File file = new File(DATA_FILE);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	void testEmptyFile() throws IOException {
	    try (FileWriter writer = new FileWriter(DATA_FILE)) {
	        writer.write(""); 
	    }

	    RecipeLoader loader = new RecipeLoader(DATA_FILE);
	    List<Recipe> recipes = loader.loadRecipesFromFile();

	    assertEquals(0, recipes.size(), "Ain't no recipes here, file empty .");
	}

	@Test
	void testOneRecipeInFile() throws IOException {
	    try (FileWriter writer = new FileWriter(DATA_FILE)) {
	        writer.write("Salad\nLettuce, Tomato\n"); 
	    }

	    RecipeLoader loader = new RecipeLoader(DATA_FILE);
	    List<Recipe> recipes = loader.loadRecipesFromFile();

	    assertEquals(1, recipes.size(), "One recipe? Bet, that's what I was lookin' for.");
	    Recipe recipe = recipes.get(0);
	    assertEquals("Salad", recipe.getName(), "C'mon now, shoulda been Salad right here.");
	    assertEquals(2, recipe.getIngredients().size(), "Shoulda had two ingredients on deck.");
	    assertEquals("Lettuce", recipe.getIngredients().get(0).getName(), "First ingredient lettuced up.");
	    assertEquals("Tomato", recipe.getIngredients().get(1).getName(), "Second one? That's tomato, easy.");
	}

	@Test
	void testMultipleRecipesInFile() throws IOException {
	    try (FileWriter writer = new FileWriter(DATA_FILE)) {
	        writer.write("Salad\nLettuce, Tomato\n\nPasta\nPasta, Tomato Sauce\n"); 
	    }

	    RecipeLoader loader = new RecipeLoader(DATA_FILE);
	    List<Recipe> recipes = loader.loadRecipesFromFile();

	    assertEquals(2, recipes.size(), "We expectin two recipes here.");

	    Recipe recipe1 = recipes.get(0);
	    assertEquals("Salad", recipe1.getName(), " Salad supposed to be up first.");
	    assertEquals(2, recipe1.getIngredients().size(), "Salad gotta have two things.");
	    assertEquals("Lettuce", recipe1.getIngredients().get(0).getName(), " Lettuce.");
	    assertEquals("Tomato", recipe1.getIngredients().get(1).getName(), "Tomato.");

	    Recipe recipe2 = recipes.get(1);
	    assertEquals("Pasta", recipe2.getName(), "Second one’s gotta be Pasta.");
	    assertEquals(2, recipe2.getIngredients().size(), "Two ingredients for this Pasta.");
	    assertEquals("Pasta", recipe2.getIngredients().get(0).getName(), "Pasta be the first ingredient here.");
	    assertEquals("Tomato Sauce", recipe2.getIngredients().get(1).getName(), "Tomato Sauce bringin' it all together.");
	}


}
