package edu.westga.cs1302.project2.model;

import java.util.Comparator;

/**
 * this class compares the Name of Ingredients in the class Ingredient
 * 
 * @author justice Ricks
 * @version Fall 2024
 * 
 */
public class NameComparator implements Comparator<Ingredient> {

	/**
	 * Compares two Ingredient objects by their name field.
	 *
	 * @param o1 the first ingredient to compare
	 * @param o2 the second ingredient to compare
	 * @return a negative integer if the name of o1 is lexicographically less than
	 *         the name of o2; zero if the names are equal; a positive integer if
	 *         the name of o1 is lexicographically greater than the name of o2
	 */
	@Override
	public int compare(Ingredient o1, Ingredient o2) {
		return o1.getName().compareTo(o2.getName());
	}

	/**
	 * Returns a string representation of this comparator.
	 * 
	 * @return "Name" indicating that this comparator sorts by the name field of
	 *         Ingredient
	 */
	@Override
	public String toString() {
		return "Name";
	}

}
