package edu.westga.cs1302.project2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * a wrapper class for the ingrindents class
 * 
 * @author justice ricks
 * @version Fall 2024
 */
public class Recipe {
	private String name;
	private List<Ingredient> ingredients;

	/**
	 * constrtor initilazize the name and the list
	 * 
	 * @param name a name variable
	 */
	public Recipe(String name) {
		this.name = name;
		this.ingredients = new ArrayList<>();
	}

	/**
	 * getter for the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * getter for the list
	 * 
	 * @return the list of ingrindets
	 */
	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	/**
	 * add Ingrindents to the ingredient method
	 * 
	 * @param ingredient a ingrinedt variable
	 */
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}

}
