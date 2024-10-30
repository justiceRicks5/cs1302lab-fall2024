package test.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

class testRecipe {

	  @Test
	    public void testAddIngredient() {
	        Recipe recipe = new Recipe("Salad");
	        Ingredient ingredient = new Ingredient("Lettuce", "Vegetable");
	        recipe.addIngredient(ingredient);
	        
	        assertTrue(recipe.getIngredients().contains(ingredient), "Ingredient was not added to the recipe.");
	    }

	    @Test
	    public void testRecipeName() {
	        Recipe recipe = new Recipe("Salad");
	        assertEquals("Salad", recipe.getName(), "Recipe name is incorrect.");
	    }

	    @Test
	    public void testEmptyIngredients() {
	        Recipe recipe = new Recipe("Salad");
	        assertTrue(recipe.getIngredients().isEmpty(), "Ingredients list should be empty initially.");
	    }

}
