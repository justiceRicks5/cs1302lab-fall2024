package test.model.Pantry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;
import edu.westga.cs1302.pantry_tracker.model.Pantry;

class testIncrementFoodPantry {

	@Test
	void testIncrementFood() {
		Pantry pantry = new Pantry();
		Food apple = new Food("Apple", "Fruit", 5);

		pantry.addFood(apple);

		pantry.incrementFood(apple);
		assertEquals(6, apple.getQuantity(), "Quantity should increase by 1.");
	}

}
