package edu.westga.cs1302.pantry_tracker.model;

import java.util.ArrayList;

/**
 * A class that Returns the sum quantity of all food items in the pantry.
 * 
 * @author justice ricks
 * @version Fall 2024
 */
public class PantryUtils {
	/**
	 * Returns the sum quantity of all food items in the pantry.
	 * 
	 * @param pantry the pantry object
	 * @return totalQuantity the total quantity of all food items
	 */
	public static int getTotalQuantity(Pantry pantry) {
		int totalQuantity = 0;
		ArrayList<Food> foodList = pantry.getFoodArray();

		for (Food food : foodList) {
			totalQuantity += food.getQuantity();
		}

		return totalQuantity;
	}
}