package edu.westga.cs1302.project2.test.model.TypeComparator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.TypeComparator;

class TestComparator {

	@Test
	void testCompareTypesInAlphabeticalOrder() {
		TypeComparator comparator = new TypeComparator();
		Ingredient ingredient1 = new Ingredient("Salt", "Spice");
		Ingredient ingredient2 = new Ingredient("Sugar", "Sweetener");

		int result = comparator.compare(ingredient1, ingredient2);

		assertEquals(-1, Integer.signum(result));
	}

	@Test
	void testCompareTypesInReverseOrder() {
		TypeComparator comparator = new TypeComparator();
		Ingredient ingredient1 = new Ingredient("Sugar", "Sweetener");
		Ingredient ingredient2 = new Ingredient("Salt", "Spice");

		int result = comparator.compare(ingredient1, ingredient2);

		assertEquals(1, Integer.signum(result));
	}

	@Test
	void testCompareEqualTypes() {
		TypeComparator comparator = new TypeComparator();
		Ingredient ingredient1 = new Ingredient("Salt", "Spice");
		Ingredient ingredient2 = new Ingredient("Pepper", "Spice");

		int result = comparator.compare(ingredient1, ingredient2);

		assertEquals(0, result);
	}

	@Test
	void testSortListOfIngredientsByType() {
		TypeComparator comparator = new TypeComparator();
		Ingredient ingredient1 = new Ingredient("Sugar", "Sweetener");
		Ingredient ingredient2 = new Ingredient("Salt", "Spice");
		Ingredient ingredient3 = new Ingredient("Wheat", "Grain");

		List<Ingredient> ingredients = Arrays.asList(ingredient1, ingredient2, ingredient3);
		ingredients.sort(comparator);

		assertEquals("Grain", ingredients.get(0).getType());
		assertEquals("Spice", ingredients.get(1).getType());
		assertEquals("Sweetener", ingredients.get(2).getType());
	}
}
