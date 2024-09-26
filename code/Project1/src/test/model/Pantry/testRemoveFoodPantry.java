package test.model.Pantry;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;
import edu.westga.cs1302.pantry_tracker.model.Pantry;

class testRemoveFoodPantry {

	 @Test
	    void testRemoveFood() {
	        Pantry pantry = new Pantry();  
	        Food apple = new Food("Apple", "Fruit", 5);  
	        Food banana = new Food("Banana", "Fruit", 2);

	        pantry.addFood(apple);  
	        pantry.addFood(banana);

	        pantry.removeFood(apple);  

	        ArrayList<Food> foodList = pantry.getFoodArray();

	        assertFalse(foodList.contains(apple), "Pantry should not contain the removed food item.");
	        assertEquals(1, foodList.size(), "Pantry should have one item after removing one.");
	    }
}
