package edu.westga.cs1302.password_generator.test.ViewModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.ViewModel.PasswordGeneratorViewModel;

class TestConstrtor {

	 @Test
	    void testInitialProperties() {
		  PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();

		    assertEquals(1, viewModel.minimumLengthProperty().get());
		    assertFalse(viewModel.mustHaveAtLeastOneDigitProperty().get());
		    assertFalse(viewModel.mustHaveAtLeastOneUpperCaseLetterProperty().get());
		    assertFalse(viewModel.mustHaveAtLeastOneLowerCaseLetterProperty().get());
		    assertTrue(viewModel.generatedPasswordProperty().get() == null || viewModel.generatedPasswordProperty().get().isEmpty());
	    }

}
