package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * this class compares the type of Ingredients in the class Ingredient
 * 
 * @author justice Ricks
 * @version Fall 2024
 * 
 */
public class TypeComparator implements Comparator<Ingredient> {
	/**
	 * Compares two Ingredient objects by their type field.
	 *
	 * @param o1 the first ingredient to compare
	 * @param o2 the second ingredient to compare
	 * @return a negative integer if the type of o1 is lexicographically less than
	 *         the type of o2; zero if the types are equal; a positive integer if
	 *         the type of o1 is lexicographically greater than the type of o2
	 */
	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getType().compareTo(o2.getType());
	}

	/**
	 * Returns a string representation of this comparator.
	 * 
	 * @return "Type" indicating that this comparator sorts by the type field of
	 *         Ingredient
	 */
	@Override
	public String toString() {
		return "Type";

	}

}