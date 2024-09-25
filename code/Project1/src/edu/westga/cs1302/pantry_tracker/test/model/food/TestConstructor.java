package edu.westga.cs1302.pantry_tracker.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;

class TestConstructor {

	@Test
	void testFoodInitialization() {

		Food food = new Food("Tomato", "Vegetable");

		assertEquals("Tomato", food.getName());
		assertEquals("Vegetable", food.getType());
		assertEquals(0, food.getQuantity());
	}

	@Test
	void setQuanity() {
		Food food = new Food("bacon", "pork");
		food.setQuantity(3);
		assertEquals(3, food.getQuantity());
	}

	@Test
	void testToStringWithDifferentNameAndType() {
		Food food = new Food("Apple", "Fruit");

		String result = food.toString();

		assertEquals("Apple – Fruit", result, "toString() should return the correct name and type.");

	}

}
