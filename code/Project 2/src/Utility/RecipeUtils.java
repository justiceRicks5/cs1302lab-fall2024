package Utility;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;

/**
 * utilty class that converts format to string
 * 
 * @author justice ricks
 * @version Fall 2024
 */

public class RecipeUtils {
	/**
	 * Converts a Recipe object to a formatted String. Format: Recipe Name
	 * ingredient1, ingredient2, ingredient3, ...
	 * 
	 * @param recipe the Recipe object to convert
	 * @return a formatted String representation of the Recipe
	 */
	public static String recipeToString(Recipe recipe) {
		String result = recipe.getName() + "\n";

		boolean first = true;
		for (Ingredient ingredient : recipe.getIngredients()) {
			if (!first) {
				result += ", ";
			}
			result += ingredient.getName();
			first = false;
		}

		return result;
	}

}
