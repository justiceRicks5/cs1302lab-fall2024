package edu.westga.cs1302.project2.test.model.Recipe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

class testAddIngridentToRecipe {

	@Test
	public void testAddIngredient() {
		Recipe recipe = new Recipe("Salad");
		Ingredient ingredient = new Ingredient("Lettuce", "Vegetable");
		recipe.addIngredient(ingredient);

		assertTrue(recipe.getIngredients().contains(ingredient), "Ingredient was not added to the recipe.");
	}

	@Test
	public void testEmptyIngredients() {
		Recipe recipe = new Recipe("Salad");
		assertTrue(recipe.getIngredients().isEmpty(), "Ingredients list should be empty initially.");
	}

}
