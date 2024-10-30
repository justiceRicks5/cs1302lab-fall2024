package edu.westga.cs1302.project2.test.model.NameComparator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.NameComparator;

class TestComparator {

	@Test
	void testCompareNamesInAlphabeticalOrder() {
		NameComparator comparator = new NameComparator();
		Ingredient ingredient1 = new Ingredient("Salt", "Spice");
		Ingredient ingredient2 = new Ingredient("Sugar", "Sweetener");

		int result = comparator.compare(ingredient1, ingredient2);

		assertEquals(-1, Integer.signum(result));
	}

	@Test
	void testCompareNamesInReverseOrder() {
		NameComparator comparator = new NameComparator();
		Ingredient ingredient1 = new Ingredient("Sugar", "Sweetener");
		Ingredient ingredient2 = new Ingredient("Salt", "Spice");

		int result = comparator.compare(ingredient1, ingredient2);

		assertEquals(1, Integer.signum(result));
	}

	@Test
	void testCompareEqualNames() {
		NameComparator comparator = new NameComparator();
		Ingredient ingredient1 = new Ingredient("Sugar", "Sweetener");
		Ingredient ingredient2 = new Ingredient("Sugar", "Sweetener");

		int result = comparator.compare(ingredient1, ingredient2);

		assertEquals(0, result);
	}

	@Test
	void testSortListOfIngredients() {
		NameComparator comparator = new NameComparator();
		Ingredient ingredient1 = new Ingredient("Salt", "Spice");
		Ingredient ingredient2 = new Ingredient("Sugar", "Sweetener");
		Ingredient ingredient3 = new Ingredient("Flour", "Grain");

		List<Ingredient> ingredients = Arrays.asList(ingredient1, ingredient2, ingredient3);
		ingredients.sort(comparator);

		assertEquals("Flour", ingredients.get(0).getName());
		assertEquals("Salt", ingredients.get(1).getName());
		assertEquals("Sugar", ingredients.get(2).getName());
	}
}
