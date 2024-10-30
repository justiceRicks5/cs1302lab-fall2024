package edu.westga.cs1302.project2.view;

import java.util.Collections;
import java.util.Comparator;

import edu.westga.cs1302.project2.model.NameComparator;
import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.TypeComparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Code behind for the Main Window of the application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	@FXML
	private ComboBox<String> ingredientType;
	@FXML
	private ListView<Ingredient> ingredientsList;
	@FXML
	private TextField ingredientName;
	@FXML
	private ComboBox<Comparator<Ingredient>> sortBy;

	@FXML
	private ListView<?> ingriendentsForRecipe;

	@FXML
	private TextField recipeName;

	@FXML
	void addIngredient(ActionEvent event) {
		try {
			this.ingredientsList.getItems()
					.add(new Ingredient(this.ingredientName.getText(), this.ingredientType.getValue()));
			this.ingredientName.clear();
			this.ingredientType.getSelectionModel().clearSelection();
			this.sortIngredients();
		} catch (IllegalArgumentException error) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Unable to add ingredient");
			alert.setContentText(error.getMessage());
			alert.showAndWait();
		}
	}

	@FXML
	void removeIngredient(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			this.ingredientsList.getItems().remove(selectedIngredient);
			this.sortIngredients();
		}
	}

	@SuppressWarnings("unchecked")
	@FXML
	void initialize() {
		this.ingredientType.getItems().add("Vegetable");
		this.ingredientType.getItems().add("Meat");
		this.ingredientType.getItems().add("Bread");
		this.ingredientType.getItems().add("Fruit");
		this.ingredientType.getItems().add("Spice");

		this.sortBy.getItems().addAll(new TypeComparator(), new NameComparator());
		this.sortBy.setPromptText("Sort By...");
		this.sortBy.getSelectionModel().selectFirst();
		this.sortBy.setOnAction(event -> this.sortIngredients());
	}

	private void sortIngredients() {
		Comparator<Ingredient> selectedComparator = this.sortBy.getSelectionModel().getSelectedItem();

		ObservableList<Ingredient> sortedList = FXCollections.observableArrayList(this.ingredientsList.getItems());
		Collections.sort(sortedList, selectedComparator);

		this.ingredientsList.setItems(sortedList);
	}
}
