package edu.westga.cs1302.bill.test.model.bil_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestSaveBillData {

	@Test
	void testSaveBillWithNoItems() throws IOException {
		Bill bill = new Bill();
		bill.setServerName("Server1");

		BillPersistenceManager.saveBillData(bill);

		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			assertEquals("Server1", reader.nextLine(), "checking server name is saved");
			assertFalse(reader.hasNextLine(), "checking that no items are saved in the bill");
		}
	}

	@Test
	void testSaveBillWithOneItem() throws IOException {
		Bill bill = new Bill();
		bill.setServerName("Server1");
		BillItem item1 = new BillItem("Item1", 10.50);
		bill.addItem(item1);

		BillPersistenceManager.saveBillData(bill);

		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			assertEquals("Server1", reader.nextLine(), "checking server name is saved");
			assertEquals("Item1,10.5", reader.nextLine(), "checking item is saved correctly");
			assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
		}
	}

	@Test
	void testSaveBillWithMultipleItems() throws IOException {
		Bill bill = new Bill();
		bill.setServerName("Server2");
		BillItem item1 = new BillItem("Item1", 12.75);
		BillItem item2 = new BillItem("Item2", 5.00);
		bill.addItem(item1);
		bill.addItem(item2);

		BillPersistenceManager.saveBillData(bill);

		File inputFile = new File(BillPersistenceManager.DATA_FILE);
		try (Scanner reader = new Scanner(inputFile)) {
			assertEquals("Server2", reader.nextLine(), "checking server name is saved");
			assertEquals("Item1,12.75", reader.nextLine(), "checking first item is saved correctly");
			assertEquals("Item2,5.0", reader.nextLine(), "checking second item is saved correctly");
			assertFalse(reader.hasNextLine(), "checking that we are at the end of the file");
		}
	}

	@Test
	void testSaveBillWithEmptyServerName() {
		Bill bill = new Bill();

		assertThrows(IllegalArgumentException.class, () -> {
			bill.setServerName("");
		});
	}

	@Test
	void testSaveBillWithNullServerName() {
		Bill bill = new Bill();

		assertThrows(IllegalArgumentException.class, () -> {
			bill.setServerName(null);
		});
	}

	@Test
	void testAddingNullItemThrowsException() {
	    Bill bill = new Bill();
	    
	    assertThrows(IllegalArgumentException.class, () -> {
	        bill.addItem(null);
	    }, "Expected an IllegalArgumentException to be thrown when adding a null item.");
	}

}
