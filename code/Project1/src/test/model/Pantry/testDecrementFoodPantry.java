package test.model.Pantry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;
import edu.westga.cs1302.pantry_tracker.model.Pantry;

class testDecrementFoodPantry {

	@Test
	void testDecrementFood() {
		Pantry pantry = new Pantry();
		Food banana = new Food("Banana", "Fruit", 2);

		pantry.addFood(banana);
		pantry.decrementFood(banana);
		assertEquals(1, banana.getQuantity(), "Quantity should decrease by 1.");

		pantry.decrementFood(banana);
		assertEquals(0, banana.getQuantity(), "Quantity should be zero after second decrement.");
	}

	@Test
	void testDecrementFoodNotBelowZero() {
		Pantry pantry = new Pantry();
		Food banana = new Food("Banana", "Fruit", 2);

		pantry.addFood(banana);

		pantry.decrementFood(banana);
		pantry.decrementFood(banana);
		pantry.decrementFood(banana);
		assertEquals(0, banana.getQuantity(), "Quantity should not go below zero.");
	}

}
