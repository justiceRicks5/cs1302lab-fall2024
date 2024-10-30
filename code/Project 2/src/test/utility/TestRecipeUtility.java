package test.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Utility.RecipeUtils;
import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

class TestRecipeUtility {

	@Test
	void testRecipeToString() {
		Recipe recipe = new Recipe("Pasta");
		recipe.addIngredient(new Ingredient("Tomato", "Vegetable"));
		recipe.addIngredient(new Ingredient("Pasta", "Grain"));

		String expectedOutput = "Pasta\nTomato, Pasta";
		String actualOutput = RecipeUtils.recipeToString(recipe);

		assertEquals(expectedOutput, actualOutput, "The recipe string format is incorrect.");
	}

	@Test
	void testEmptyRecipe() {
		Recipe recipe = new Recipe("Empty Recipe");
		String expectedOutput = "Empty Recipe\n";

		String actualOutput = RecipeUtils.recipeToString(recipe);
		assertEquals(expectedOutput, actualOutput, "The recipe string format for an empty recipe is incorrect.");
	}
}
