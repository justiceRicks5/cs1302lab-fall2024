package edu.westga.cs1302.bill.model;

import java.io.FileWriter;
import java.io.IOException;

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
	public static void saveBillData(Bill[] bill) throws IOException {
		try (FileWriter file = new FileWriter(DATA_FILE)) {
			for (Bill currentBill : bill) {
				file.write(currentBill.getItems() + ", " + currentBill.getServerName() + "," + currentBill.getSize()); //forgot to make a seperate branch 
			}
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
	 */
	public static Bill loadBillData() {
		return null;
	}

}
