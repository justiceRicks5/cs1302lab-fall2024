package edu.westga.cs1302.bill.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Supports saving and loading bill data in CSV format.
 * @author justice ricks 
 * @version Fall 2024
 */
public class CSVBillDataPersistenceManager extends BillPersistenceManager {

	public static final String DATA_FILE = "data.txt";
	private static final String DELIMITER = ",";

	/**
	 * Save the bill items in CSV format.
	 * 
	 * Writes each bill item to DATA_FILE. Each item is written on a separate line
	 * in the following format: serverName,description,amount.
	 * 
	 * @precondition bill != null
	 * @postcondition none
	 * 
	 * @param bill the bill to save
	 * @throws IOException
	 */
	@Override
	public void saveBillData(Bill bill) throws IOException {
		if (bill == null) {
			throw new IllegalArgumentException("Bill cannot be null");
		}

		try (FileWriter writer = new FileWriter(DATA_FILE)) {
			writer.write("Server: " + bill.getServerName() + System.lineSeparator());
			for (BillItem item : bill.getItems()) {
				writer.write(item.getName() + DELIMITER + item.getAmount() + System.lineSeparator());
			}
		}
	}

	/**
	 * Load the bill items from CSV format.
	 * 
	 * Reads from DATA_FILE and creates BillItems accordingly. File is assumed to
	 * use the same format as saveBillData.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the bill with items loaded from the file, or an empty bill if the
	 *         file does not exist or is improperly formatted.
	 * @throws FileNotFoundException if the file at DATA_FILE location does not
	 *                               exist.
	 * @throws IOException           if data in the file is invalid.
	 */
	@Override
	public Bill loadBillData() throws FileNotFoundException, IOException {
		File inputFile = new File(DATA_FILE);
		Bill bill = new Bill();

		if (!inputFile.exists()) {
			return bill;
		}

		try (Scanner reader = new Scanner(inputFile)) {
			if (reader.hasNextLine()) {
				String serverLine = reader.nextLine();
				String[] serverParts = serverLine.split(": ");
				if (serverParts.length == 2) {
					bill.setServerName(serverParts[1]);
				}
			}

			while (reader.hasNextLine()) {
				String baseLine = reader.nextLine();
				String[] parts = baseLine.split(DELIMITER);
				try {
					String name = parts[0];
					double amount = Double.parseDouble(parts[1]);
					BillItem item = new BillItem(name, amount);
					bill.addItem(item);
				} catch (NumberFormatException error) {
					throw new IOException("Invalid amount format on line: " + baseLine);
				} catch (IndexOutOfBoundsException error) {
					throw new IOException("Missing data on line: " + baseLine);
				}
			}
		}
		return bill;
	}

	@Override
	public String toString() {
		return "CSV";
	}
}
