package edu.westga.cs1302.pantry_tracker.model;

import java.util.ArrayList;

/**
 * wraper class of food
 * 
 * @author justice Ricks
 * @version Fall 2024
 * 
 */
public class Pantry {
	private ArrayList<Food> foodArray;

	/**
	 * consurtor for the wrapper class
	 * 
	 */
	public Pantry() {
		this.foodArray = new ArrayList<>();
	}

	/**
	 * removes food from the array
	 * 
	 * @param food food item
	 */
	public void removeFood(Food food) {
		this.foodArray.remove(food);
	}

	/**
	 * adds food to the pantry
	 * 
	 * @param food food item
	 */
	public void addFood(Food food) {
		this.foodArray.add(food);
	}

	/**
	 * get the array of food items
	 * 
	 * @return foodArray a array of Food items
	 */
	public ArrayList<Food> getFoodArray() {
		return this.foodArray;
	}

	/**
	 * increments the food array
	 * 
	 * @param food a food obeject
	 */
	public void incrementFood(Food food) {
		food.incrementQuantity();
	}

	/**
	 * decrements the food
	 * 
	 * @param food a food object
	 */
	public void decrementFood(Food food) {
		food.decrementQuantity();
	}

	/**
	 * sets the quanitity of food for the array
	 * 
	 * @param food    food object
	 * @param numFood number of food set
	 */
	public void setFoodQuantity(Food food, int numFood) {
		food.setQuantity(numFood);
	}
}
