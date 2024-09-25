package edu.westga.cs1302.pantry_tracker.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;

class testIncrementQuantitys {

	@Test
	public void testIncrementQuantityOnce() {

		Food food = new Food("Tomato", "Vegetable",0);

		food.incrementQuantity();

		assertEquals(1, food.getQuantity(), "The quantity should be 1 after incrementing once.");
	}

	@Test
	public void testIncrementQuantityMultipleTimes() {

		Food food = new Food("Apple", "Fruit",0);

		food.incrementQuantity();
		food.incrementQuantity();
		food.incrementQuantity();

		assertEquals(3, food.getQuantity(), "The quantity should be 3 after incrementing three times.");
	}

	@Test
	public void testIncrementQuantityOnCustomStartingValue() {

		Food food = new Food("Bread", "Grain",0);
		food.incrementQuantity();

		food.incrementQuantity();

		assertEquals(2, food.getQuantity(), "The quantity should be 2 after two increments.");
	}
}
