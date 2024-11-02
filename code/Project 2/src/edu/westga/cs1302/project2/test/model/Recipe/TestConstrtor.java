package edu.westga.cs1302.project2.test.model.Recipe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Recipe;

class TestConstrtor {

	@Test
	void testConstructorWithName() {
		String recipeName = "Spaghetti";

		Recipe recipe = new Recipe(recipeName);

		// Check if the name is correctly assigned
		assertEquals(recipeName, recipe.getName(), "Recipe name should match the provided name.");

		// Check if the ingredients list is initialized and empty
		assertNotNull(recipe.getIngredients(), "Ingredients list should be initialized.");
		assertTrue(recipe.getIngredients().isEmpty(), "Ingredients list should be empty initially.");
	}

	@Test
	void testConstructorWithEmptyName() {
		Recipe recipe = new Recipe("");

		// Check if the name is set as an empty string
		assertEquals("", recipe.getName(), "Recipe name should be an empty string.");

		// Check if the ingredients list is initialized and empty
		assertNotNull(recipe.getIngredients(), "Ingredients list should be initialized.");
		assertTrue(recipe.getIngredients().isEmpty(), "Ingredients list should be empty initially.");
	}

	@Test
	void testConstructorWithNullName() {
		assertThrows(NullPointerException.class, () -> new Recipe(null),
				"Constructor should throw NullPointerException for null name.");
	}

}
