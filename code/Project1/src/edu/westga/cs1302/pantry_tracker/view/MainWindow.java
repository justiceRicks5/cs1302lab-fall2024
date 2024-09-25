package edu.westga.cs1302.pantry_tracker.view;

import edu.westga.cs1302.pantry_tracker.model.Food;
import edu.westga.cs1302.pantry_tracker.model.Pantry;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Codebehind for the Main Window of the application using an ObservableList for
 * managing food items.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

	private Pantry foodArray;
	@FXML
	private TextField foodName;

	@FXML
	private ListView<Food> selectFood;

	@FXML
	private ComboBox<String> typeOfFood;
	@FXML
	private Button decrement;
	@FXML
	private Button increment;
	@FXML
	private TextField quantity;
	@FXML
	private Label settingTheQuantity;
	@FXML
	private Button removeButton;

	@FXML
	void initialize() {

		assert this.foodName != null : "fx:id=\"foodName\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.selectFood != null
				: "fx:id=\"selectFood\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.typeOfFood != null
				: "fx:id=\"typeOfFood\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.quantity != null : "fx:id=\"quantity\" was not injected: check your FXML file 'MainWindow.fxml'.";

		this.typeOfFood.setItems(
				FXCollections.observableArrayList("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient"));
		this.foodArray = new Pantry();
	}

	@FXML
	void addFoodToPantry() {
		try {

			String name = this.foodName.getText();
			String type = this.typeOfFood.getValue();
			int quantity = Integer.parseInt(this.quantity.getText());
			if (name == null || name.trim().isEmpty() || type == null) {
				throw new IllegalArgumentException("Please enter both a food name and select a type.");
			}

			Food newFood = new Food(name, type, quantity);
			this.foodArray.addFood(newFood);
			this.selectFood.getItems().add(newFood);

			this.foodName.clear();
			this.typeOfFood.getSelectionModel().clearSelection();
			this.selectFood.refresh();
		} catch (Exception error) {

			this.showAlert(AlertType.ERROR, "Error", error.getMessage());
		}
	}

	@FXML
	void incrementSelectedFoodQuantity() {
		Food selectedFood = this.selectFood.getSelectionModel().getSelectedItem();
		if (selectedFood != null) {
			this.foodArray.incrementFood(selectedFood);
			this.selectFood.refresh();
		} else {
			this.showAlert(AlertType.WARNING, "No Selection", "Please select a food item to increment.");
		}
	}

	@FXML
	void decrementSelectedFoodQuantity() {
		Food selectedFood = this.selectFood.getSelectionModel().getSelectedItem();
		if (selectedFood != null) {
			this.foodArray.decrementFood(selectedFood);
			this.selectFood.refresh();
		} else {
			this.showAlert(AlertType.WARNING, "No Selection", "Please select a food item to decrement.");
		}
	}

	@FXML
	void setSelectedFoodQuantity() {
		Food selectedFood = this.selectFood.getSelectionModel().getSelectedItem();
		if (selectedFood != null) {
			try {
				System.out.println("TextField input: " + this.quantity.getText());

				int newQuantity = Integer.parseInt(this.quantity.getText());
				System.out.println("Setting quantity to: " + newQuantity);

				selectedFood.setQuantity(newQuantity);
				System.out.println("Updated food: " + selectedFood);

				this.foodArray.setFoodQuantity(selectedFood, newQuantity);
				this.selectFood.refresh();
			} catch (NumberFormatException error) {
				this.showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number for the quantity.");
			}
		} else {
			this.showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a food item to set the quantity.");
		}
	}

	@FXML
	void removeFoodToPantry() {

		Food selectedFood = this.selectFood.getSelectionModel().getSelectedItem();

		if (selectedFood != null) {

			this.foodArray.removeFood(selectedFood);

			this.selectFood.getItems().remove(selectedFood);

			this.foodName.clear();
			this.typeOfFood.getSelectionModel().clearSelection();
			this.quantity.clear();

			this.selectFood.refresh();
		} else {
			this.showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a food item to remove.");
		}
	}

	private void showAlert(AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
