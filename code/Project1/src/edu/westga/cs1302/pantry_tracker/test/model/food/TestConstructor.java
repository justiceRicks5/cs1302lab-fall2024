package edu.westga.cs1302.pantry_tracker.test.model.food;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;

class TestConstructor {

	@Test
	void testFoodInitialization() {

		Food food = new Food("Tomato", "Vegetable",12);

		assertEquals("Tomato", food.getName());
		assertEquals("Vegetable", food.getType());
		assertEquals(12 , food.getQuantity());
	}

	@Test
	void setQuanity() {
		Food food = new Food("bacon", "pork",1);
		food.setQuantity(3);
		assertEquals(3, food.getQuantity());
	}

	

}
