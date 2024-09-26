package test.model.Pantry;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;
import edu.westga.cs1302.pantry_tracker.model.Pantry;

class testSetFoodQuantityPantry {

	@Test
	void testSetFoodQuantity() {
		Pantry pantry = new Pantry();
		Food apple = new Food("Apple", "Fruit", 5);
		pantry.addFood(apple);

		pantry.setFoodQuantity(apple, 10);
		assertEquals(10, apple.getQuantity(), "Quantity should be set to 10.");
	}

}
