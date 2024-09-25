package edu.westga.cs1302.pantry_tracker.view;

import edu.westga.cs1302.pantry_tracker.model.Food;
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
	void initialize() {

		assert this.foodName != null : "fx:id=\"foodName\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.selectFood != null
				: "fx:id=\"selectFood\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.typeOfFood != null
				: "fx:id=\"typeOfFood\" was not injected: check your FXML file 'MainWindow.fxml'.";
		assert this.quantity != null : "fx:id=\"quantity\" was not injected: check your FXML file 'MainWindow.fxml'.";

		this.typeOfFood.setItems(
				FXCollections.observableArrayList("Vegetable", "Meat", "Bread", "Fruit", "Dessert", "Ingredient"));

	}

	@FXML
	void addFoodToPantry() {
		try {

			String name = this.foodName.getText();
			String type = this.typeOfFood.getValue();

			if (name == null || name.trim().isEmpty() || type == null) {
				throw new IllegalArgumentException("Please enter both a food name and select a type.");
			}

			Food newFood = new Food(name, type);
			this.selectFood.getItems().add(newFood);

			this.foodName.clear();
			this.typeOfFood.getSelectionModel().clearSelection();
		} catch (Exception error) {

			this.showAlert(AlertType.ERROR, "Error", error.getMessage());
		}
	}

	@FXML
	void incrementSelectedFoodQuantity() {
		Food selectedFood = this.selectFood.getSelectionModel().getSelectedItem();
		if (selectedFood != null) {
			selectedFood.incrementQuantity();
			this.selectFood.refresh();
		} else {
			this.showAlert(AlertType.WARNING, "No Selection", "Please select a food item to increment.");
		}
	}

	@FXML
	void decrementSelectedFoodQuantity() {
		Food selectedFood = this.selectFood.getSelectionModel().getSelectedItem();
		if (selectedFood != null) {
			selectedFood.decrementQuantity();
			this.selectFood.refresh();
		} else {
			this.showAlert(AlertType.WARNING, "No Selection", "Please select a food item to decrement.");
		}
	}

	@FXML
	void setSelectedFoodQuantity() {
	    Food selectedFood = this.selectFood.getSelectionModel().getSelectedItem();
	    if (selectedFood != null) {
	        selectedFood.setQuantity(5);  // Hardcoded value for testing
	        System.out.println("Quantity set to 5");
	        this.selectFood.refresh();
	    } else {
	        this.showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a food item to set the quantity.");
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
