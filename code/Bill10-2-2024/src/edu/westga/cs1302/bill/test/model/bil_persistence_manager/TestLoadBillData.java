package edu.westga.cs1302.bill.test.model.bil_persistence_manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;

class TestLoadBillData {

	@Test
	void testLoadEmptyFile() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server1" + System.lineSeparator());
		}

		Bill bill = BillPersistenceManager.loadBillData();

		assertEquals("Server1", bill.getServerName(), "checking server name is loaded correctly");
		assertEquals(0, bill.getSize(), "checking number of items loaded");
	}

	@Test
	void testLoadOneItemInFile() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server1" + System.lineSeparator());
			writer.write("Item1,10.50" + System.lineSeparator());
		}

		Bill bill = BillPersistenceManager.loadBillData();

		assertEquals("Server1", bill.getServerName(), "checking server name is loaded correctly");
		assertEquals(1, bill.getSize(), "checking number of items loaded");
		BillItem item = bill.getItems()[0];
		assertEquals("Item1", item.getName(), "checking name of the item");
		assertEquals(10.50, item.getAmount(), "checking amount of the item");
	}

	@Test
	void testLoadMultipleItemsInFile() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server2" + System.lineSeparator());
			writer.write("Item1,12.75" + System.lineSeparator());
			writer.write("Item2,5.00" + System.lineSeparator());
		}

		Bill bill = BillPersistenceManager.loadBillData();

		assertEquals("Server2", bill.getServerName(), "checking server name is loaded correctly");
		assertEquals(2, bill.getSize(), "checking number of items loaded");

		BillItem item1 = bill.getItems()[0];
		BillItem item2 = bill.getItems()[1];

		assertEquals("Item1", item1.getName(), "checking name of the first item");
		assertEquals(12.75, item1.getAmount(), "checking amount of the first item");

		assertEquals("Item2", item2.getName(), "checking name of the second item");
		assertEquals(5.00, item2.getAmount(), "checking amount of the second item");
	}

	@Test
	void testLoadFileWithInvalidAmount() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server1" + System.lineSeparator());
			writer.write("Item1,invalidAmount" + System.lineSeparator());
		}

		assertThrows(IOException.class, () -> {
			BillPersistenceManager.loadBillData();
		}, "Expected IOException when encountering invalid amount");
	}

	@Test
	void testLoadFileWithMissingItemFields() throws IOException {
		try (FileWriter writer = new FileWriter(BillPersistenceManager.DATA_FILE)) {
			writer.write("Server1" + System.lineSeparator());
			writer.write("Item1" + System.lineSeparator());
		}

		assertThrows(IOException.class, () -> {
			BillPersistenceManager.loadBillData();
		}, "Expected IOException when encountering missing item fields");
	}

}
