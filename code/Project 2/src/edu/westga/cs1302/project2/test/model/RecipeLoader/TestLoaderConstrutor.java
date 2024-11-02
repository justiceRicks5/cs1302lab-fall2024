package edu.westga.cs1302.project2.test.model.RecipeLoader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.RecipeLoader;

class TestLoaderConstrutor {
	

	  @Test
	    public void testConstructorWithValidFilePath() {
	        String validPath = "recipes.txt";
	        RecipeLoader loader = new RecipeLoader(validPath);
	        
	        assertEquals(validPath, loader.getFilePath(), "File path should match the valid path provided.");
	    }

	    @Test
	    public void testConstructorWithEmptyFilePath() {
	        String emptyPath = "";
	        RecipeLoader loader = new RecipeLoader(emptyPath);
	        
	        assertEquals(emptyPath, loader.getFilePath(), "File path should be an empty string if passed as such.");
	    }


}
