package testPantryUtils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;
import edu.westga.cs1302.pantry_tracker.model.Pantry;
import edu.westga.cs1302.pantry_tracker.model.PantryUtils;

class TestPantryutils {

	@Test
	void testGetTotalQuantity() {
		Pantry pantry = new Pantry();
		pantry.addFood(new Food("Apple", "Fruit", 5));
		pantry.addFood(new Food("Banana", "Fruit", 3));

		int totalQuantity = PantryUtils.getTotalQuantity(pantry);

		assertEquals(8, totalQuantity, "Total quantity should be 8.");
	}

	@Test
	void testGetTotalQuantityEmptyPantry() {
		Pantry pantry = new Pantry();

		int totalQuantity = PantryUtils.getTotalQuantity(pantry);

		assertEquals(0, totalQuantity, "Total quantity should be 0 for an empty pantry.");
	}

	@Test
	void testGetTotalQuantitySingleItem() {
		Pantry pantry = new Pantry();
		pantry.addFood(new Food("Orange", "Fruit", 7));

		int totalQuantity = PantryUtils.getTotalQuantity(pantry);

		assertEquals(7, totalQuantity, "Total quantity should be 7.");
	}
}
