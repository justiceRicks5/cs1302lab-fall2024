package edu.westga.cs1302.project2.test.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import Utility.RecipeUtils;
import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

class TestRecipeListToString {

	 @Test
	    public void testRecipeListToStringWithEmptyList() {
	        List<Recipe> recipes = new ArrayList<>();
	        String result = RecipeUtils.recipeListToString(recipes);
	        
	        assertEquals("", result, "Expected an empty string for an empty recipe list.");
	    }

	    @Test
	    public void testRecipeListToStringWithSingleRecipe() {
	        List<Recipe> recipes = new ArrayList<>();
	        Recipe recipe = new Recipe("Salad");
	        recipe.addIngredient(new Ingredient("Lettuce", "Vegetable"));
	        recipe.addIngredient(new Ingredient("Tomato", "Vegetable"));
	        recipes.add(recipe);

	        String expected = "Salad\nLettuce, Tomato";
	        String result = RecipeUtils.recipeListToString(recipes);

	        assertEquals(expected, result, "Single recipe string format is incorrect.");
	    }

	    @Test
	    public void testRecipeListToStringWithMultipleRecipes() {
	        List<Recipe> recipes = new ArrayList<>();

	        Recipe recipe1 = new Recipe("Salad");
	        recipe1.addIngredient(new Ingredient("Lettuce", "Vegetable"));
	        recipe1.addIngredient(new Ingredient("Tomato", "Vegetable"));

	        Recipe recipe2 = new Recipe("Pasta");
	        recipe2.addIngredient(new Ingredient("Pasta", "Grain"));
	        recipe2.addIngredient(new Ingredient("Tomato Sauce", "Sauce"));

	        recipes.add(recipe1);
	        recipes.add(recipe2);

	        String expected = "Salad\nLettuce, Tomato\n\nPasta\nPasta, Tomato Sauce";
	        String result = RecipeUtils.recipeListToString(recipes);

	        assertEquals(expected, result, "Multiple recipe string format is incorrect.");
	    }
}
