package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
			String nameAndSize = bill.getServerName() + ", \n" + bill.getSize();

			for (BillItem currItems : bill.getItems()) {
				nameAndSize += ", \n" + currItems.getName();
				double amount = currItems.getAmount();
				String.valueOf(amount);
				nameAndSize += ", \n" + amount;
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
	public static Bill[] loadBillData() throws FileNotFoundException {
		List<Bill> bills = new ArrayList<Bill>();
		File inputFile = new File(DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			for (int lineNumber = 1; reader.hasNextLine(); lineNumber++) {
				String[] parts = reader.nextLine().strip().split(" , ");
				String name = parts[0];

			}
		}

		return null;

	}

}
