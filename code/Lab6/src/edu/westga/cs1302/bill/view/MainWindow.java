package edu.westga.cs1302.bill.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillAscendindingBillitemCompartor;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillItemDescendingComparator;
import edu.westga.cs1302.bill.model.CSVBillDataPersistenceManager;
import edu.westga.cs1302.bill.model.TSVBillDataPersistenceManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The codebehind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	public static final String DATA_FILE = "data.txt";
	private Bill bill;

	@FXML
	private TextField name;
	@FXML
	private TextField amount;
	@FXML
	private TextArea receiptArea;
	@FXML
	private ComboBox<String> serverName;
	@FXML
	private ComboBox<String> format;
	@FXML
	private ComboBox<Comparator<BillItem>> order;

	@FXML
	void addItem(ActionEvent event) {
		try {
			BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
			this.bill.addItem(item);
			this.name.clear();
			this.amount.clear();
			this.updateReceipt();
		} catch (NumberFormatException numError) {
			this.displayErrorPopup("Invalid amount provided, please correct and try again.");
		} catch (IllegalArgumentException argError) {
			this.displayErrorPopup("Unable to add new bill item");
		}
	}

	private void updateReceipt() {
		this.receiptArea.setText(BillTextifier.getText(this.bill));
	}

	@FXML
	void selectServer(ActionEvent event) {
		String name = this.serverName.getValue();
		if (name != null) {
			this.bill.setServerName(name);
			this.updateReceipt();
		}
	}

	@FXML
	void saveBillData(ActionEvent event) {
		try {
			if (this.format.getSelectionModel().getSelectedItem().equals("TSV")) {
				TSVBillDataPersistenceManager tsvBill = new TSVBillDataPersistenceManager();
				tsvBill.saveBillData(this.bill);
			}
			if (this.format.getSelectionModel().getSelectedItem().equals("CSV")) {
				CSVBillDataPersistenceManager csvBill = new CSVBillDataPersistenceManager();
				csvBill.saveBillData(this.bill);
			}
		} catch (IOException writeError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Unable to save data to file!");
			alert.showAndWait();
		}

	}

	@FXML
	void loadBillData() {
		File file = new File(DATA_FILE);
		try (Scanner reader = new Scanner(file)) {
			reader.nextLine();
			if (reader.nextLine().contains(",")) {
				CSVBillDataPersistenceManager csvBill = new CSVBillDataPersistenceManager();
				this.bill = csvBill.loadBillData();
				this.updateReceipt();
			}
			if (reader.nextLine().contains("\t")) {
				TSVBillDataPersistenceManager tsvBill = new TSVBillDataPersistenceManager();
				this.bill = tsvBill.loadBillData();
				this.updateReceipt();
			}
		} catch (FileNotFoundException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("file doesn't exist");
			alert.showAndWait();
		} catch (IOException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Unable to load file!");
			alert.showAndWait();
		}
	}

	private void displayErrorPopup(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	void changeFormat(ActionEvent event) {

	}

	@FXML
	void sortBill(ActionEvent event) {
		this.bill.sortBill(this.order.getSelectionModel().getSelectedItem());
		this.updateReceipt();
	}

	@FXML
	void initialize() {
		this.bill = new Bill();
		this.serverName.getItems().add("Bob");
		this.serverName.getItems().add("Alice");
		this.serverName.getItems().add("Trudy");
		this.order.getItems().add(new BillAscendindingBillitemCompartor());
		this.order.getItems().add(new BillItemDescendingComparator());
		this.format.getItems().add("TSV");
		this.format.getItems().add("CSV");
		this.loadBillData();
		
		this.updateReceipt();
	}
}
