package edu.westga.cs1302.pantry_tracker.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;

class testDecrement {

	@Test
	void testDecrementquantitys() {

		Food food = new Food("Tomato", "Vegetable",0);
		food.incrementQuantity();

		food.decrementQuantity();

		assertEquals(0, food.getQuantity(), "The quantity should be 0 after decrementing once.");

	}

	@Test
	public void testDecrementQuantityMultipleTimes() {

		Food food = new Food("Apple", "Fruit", 0);
		food.incrementQuantity();
		food.incrementQuantity();

		food.decrementQuantity();
		food.decrementQuantity();

		assertEquals(0, food.getQuantity(), "The quantity should be 0 after decrementing twice.");
	}

	@Test
	public void testDecrementQuantityDoesNotGoBelowZero() {

		Food food = new Food("Bread", "Grain",0);

		food.decrementQuantity();

		assertEquals(0, food.getQuantity(), "The quantity should remain 0 and not go below zero.");
	}

	@Test
	public void testDecrementFromCustomValue() {

		Food food = new Food("Milk", "Dairy",0);
		food.incrementQuantity();
		food.incrementQuantity();
		food.incrementQuantity();

		food.decrementQuantity();

		assertEquals(2, food.getQuantity(), "The quantity should be 2 after decrementing from 3.");
	}

}
