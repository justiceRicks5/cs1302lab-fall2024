package edu.westga.cs1302.pantry_tracker.model;

/**
 * a class that adds food new food items to the pantry
 * 
 * 
 * @author Justice Ricks
 * @version Fall 2024
 * 
 */
public class Food {

	private final String name;
	private final String type;
	private int quantity;

	/**
	 * a two param consuctor that adds the name and type of food
	 * 
	 * @precondtion none
	 * @postcondtion none
	 * 
	 * @param name the name of the food
	 * @param type the type off the food
	 */
	public Food(String name, String type) {
		this.name = name;
		this.type = type;
		this.quantity = 0;
	}

	/**
	 * a simple getter for the name of the food
	 * 
	 * @return name the name of the food
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * a simple getter that gets the type of food it is
	 * 
	 * @return type the type of food we are adding
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * a getter for the quantity of food we have
	 * 
	 * @return quantity the number of food we have added to the pantry
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * sets the quanity amount
	 * 
	 * @param quantity the quantity inputed
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * overrides the toString() method
	 * 
	 * @return name and quantity of the food added
	 */
	@Override
	public String toString() {
		return this.name + " – " + this.type;
	}

}
