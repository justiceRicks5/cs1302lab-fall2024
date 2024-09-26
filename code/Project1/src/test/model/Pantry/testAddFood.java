package test.model.Pantry;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.pantry_tracker.model.Food;
import edu.westga.cs1302.pantry_tracker.model.Pantry;

class testAddFood {

	@Test
    void testAddFoodToPantry() {
        Pantry pantry = new Pantry();  
        Food apple = new Food("Apple", "Fruit", 5);  

        pantry.addFood(apple); 
        ArrayList<Food> foodList = pantry.getFoodArray();  

        assertTrue(foodList.contains(apple), "Pantry should contain the added food item.");
        assertEquals(1, foodList.size(), "Pantry should contain exactly one item.");
    }

}
