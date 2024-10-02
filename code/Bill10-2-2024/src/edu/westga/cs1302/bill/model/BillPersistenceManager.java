package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Supports saving and loading bill data,
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class BillPersistenceManager {

	public static final String DATA_FILE = "data.txt";

	/**
	 * Save the bill!
	 * 
	 * Writes all bill data to DATA_FILE
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException
	 */
	public static void saveBillData(Bill bill) throws IOException {
		try (FileWriter file = new FileWriter(DATA_FILE)) {
			String nameAndSize = bill.getServerName() + System.lineSeparator();

			for (BillItem currItems : bill.getItems()) {
				nameAndSize += currItems.getName();
				double amount = currItems.getAmount();
				String.valueOf(amount);
				nameAndSize += "," + amount + System.lineSeparator();
			}

			file.write(nameAndSize);

		}

	}

	/**
	 * Load the bill!
	 * 
	 * Reads from DATA_FILE File is assumed to use the same format as saveBillData
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill loaded
	 * @throws FileNotFoundException
	 */
	public static Bill loadBillData() throws FileNotFoundException, IOException {
		Bill bill = new Bill();
		File inputFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			String name = reader.nextLine();
			bill.setServerName(name);

			for (int lineNumber = 1; reader.hasNextLine(); lineNumber++) {
				String baseLine = reader.nextLine();
				String strippedLine = baseLine.strip();
				String[] parts = strippedLine.split(",");

				try {
					BillItem item;
					String name1 = parts[0];
					double amount = Double.parseDouble(parts[1]);
					item = new BillItem(name1, amount);
					bill.addItem(item);
				} catch (NumberFormatException numError) {
					throw new IOException(
							"Unable to read bill (was not a valid double) on line " + lineNumber + " : " + strippedLine);
				} catch (IllegalArgumentException dataError) {
					throw new IOException(
							"Unable to create bill, bad name/amount on line " + lineNumber + " : " + strippedLine);
				} catch (IndexOutOfBoundsException dataError) {
					throw new IOException(
							"Missing either name and/or amount on line " + lineNumber + " : " + strippedLine);
				}
			}

		}

		return bill;

	}

}
