package edu.westga.cs1302.password_generator.test.ViewModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.ViewModel.PasswordGeneratorViewModel;

class TestUpdatePasswordGenrator {

	 @Test
	    void testUpdatePasswordGeneratorSettingsUpdatesModelCorrectly() {
	        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();

	        viewModel.minimumLengthProperty().set(12);
	        viewModel.mustHaveAtLeastOneDigitProperty().set(true);
	        viewModel.mustHaveAtLeastOneUpperCaseLetterProperty().set(true);
	        viewModel.mustHaveAtLeastOneLowerCaseLetterProperty().set(true);

	        viewModel.updatePasswordGeneratorSettings();

	        assertEquals(12, viewModel.minimumLengthProperty().get());
	        assertTrue(viewModel.mustHaveAtLeastOneDigitProperty().get());
	        assertTrue(viewModel.mustHaveAtLeastOneUpperCaseLetterProperty().get());
	        assertTrue(viewModel.mustHaveAtLeastOneLowerCaseLetterProperty().get());
	    }

	    @Test
	    void testUpdatePasswordGeneratorSettingsWithMinimumSettings() {
	        PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();

	        viewModel.updatePasswordGeneratorSettings();

	        assertEquals(1, viewModel.minimumLengthProperty().get());
	        assertFalse(viewModel.mustHaveAtLeastOneDigitProperty().get());
	        assertFalse(viewModel.mustHaveAtLeastOneUpperCaseLetterProperty().get());
	        assertFalse(viewModel.mustHaveAtLeastOneLowerCaseLetterProperty().get());
	    }

}
