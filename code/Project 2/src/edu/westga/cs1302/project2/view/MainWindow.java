package edu.westga.cs1302.project2.view;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import edu.westga.cs1302.project2.model.NameComparator;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileWriter;
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
	private ListView<Ingredient> ingriendentsForRecipe;

	@FXML
	private TextField recipeName;

	private ObservableList<Ingredient> recipeIngredients = FXCollections.observableArrayList();

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

	@FXML
	void addIngredientToRecipe(ActionEvent event) {
		Ingredient selectedIngredient = this.ingredientsList.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null && !this.recipeIngredients.contains(selectedIngredient)) {
			this.recipeIngredients.add(selectedIngredient);
		}
	}

	@FXML
	void saveRecipe(ActionEvent event) throws IOException {
		String name = this.recipeName.getText().trim();
		if (name.isEmpty()) {
			this.showAlert("Invalid Input", "Recipe name cannot be empty.");
			return;
		}

		Recipe recipe = new Recipe(name);
		recipe.getIngredients().addAll(this.recipeIngredients);

		String filePath = "recipes.txt";
		try {
			RecipeFileWriter.writeRecipeToFile(recipe, filePath);
			this.showAlert("Success", "Recipe saved successfully.");
		} catch (IllegalStateException error) {
			this.showAlert("Duplicate Recipe", "A recipe with this name already exists.");
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
		this.ingriendentsForRecipe.setItems(this.recipeIngredients);
	}

	private void sortIngredients() {
		Comparator<Ingredient> selectedComparator = this.sortBy.getSelectionModel().getSelectedItem();

		ObservableList<Ingredient> sortedList = FXCollections.observableArrayList(this.ingredientsList.getItems());
		Collections.sort(sortedList, selectedComparator);

		this.ingredientsList.setItems(sortedList);
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
