package edu.westga.cs1302.project2.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.Ingredient;
import edu.westga.cs1302.project2.model.Recipe;
import edu.westga.cs1302.project2.model.RecipeFileWriter;

class TestRecipeFileWriter {

	@Test
    public void testWriteRecipeToFile() throws IOException {
        Path tempFile = Files.createTempFile("testRecipes", ".txt");

        try {
            Recipe recipe = new Recipe("Soup");
            recipe.addIngredient(new Ingredient("Water", "Liquid"));
            recipe.addIngredient(new Ingredient("Carrot", "Vegetable"));

            RecipeFileWriter.writeRecipeToFile(recipe, tempFile.toString());

            String fileContent = Files.readString(tempFile);
            assertTrue(fileContent.contains("Soup"), "Recipe name not written to file.");
            assertTrue(fileContent.contains("Water"), "Ingredient not written to file.");
            assertTrue(fileContent.contains("Carrot"), "Ingredient not written to file.");
        } finally {
            Files.deleteIfExists(tempFile);
        }
    }

    @Test
    public void testDuplicateRecipeThrowsException() throws IOException {
        Path tempFile = Files.createTempFile("testRecipes", ".txt");

        try {
            Recipe recipe = new Recipe("Duplicate Recipe");
            recipe.addIngredient(new Ingredient("Salt", "Spice"));

   
            RecipeFileWriter.writeRecipeToFile(recipe, tempFile.toString());

           
            assertThrows(IllegalStateException.class, () -> {
                RecipeFileWriter.writeRecipeToFile(recipe, tempFile.toString());
            }, "Duplicate recipe did not throw an exception.");
        } finally {
            Files.deleteIfExists(tempFile);
        }

}
}