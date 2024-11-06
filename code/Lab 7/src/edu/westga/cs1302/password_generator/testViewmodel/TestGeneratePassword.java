package edu.westga.cs1302.password_generator.testViewmodel;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.ViewModel.PasswordGeneratorViewModel;

class TestGeneratePassword {

	@Test
	void testGeneratePasswordWithBasicSettings() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();

		viewModel.minimumLengthProperty().set(6);
		viewModel.updatePasswordGeneratorSettings();
		viewModel.generatePassword();

		String generatedPassword = viewModel.generatedPasswordProperty().get();
		assertNotNull(generatedPassword);
		assertTrue(generatedPassword.length() >= 6);
	}

	@Test
	void testGeneratePasswordWithAllRequirements() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();

		viewModel.minimumLengthProperty().set(10);
		viewModel.mustHaveAtLeastOneDigitProperty().set(true);
		viewModel.mustHaveAtLeastOneUpperCaseLetterProperty().set(true);
		viewModel.mustHaveAtLeastOneLowerCaseLetterProperty().set(true);

		viewModel.updatePasswordGeneratorSettings();
		viewModel.generatePassword();

		String password = viewModel.generatedPasswordProperty().get();
		assertNotNull(password);
		assertTrue(password.length() >= 10);
		assertTrue(password.matches(".*\\d.*"));
		assertTrue(password.matches(".*[A-Z].*"));
		assertTrue(password.matches(".*[a-z].*"));
	}

	@Test
	void testGeneratePasswordReflectsUpdatedSettings() {
		PasswordGeneratorViewModel viewModel = new PasswordGeneratorViewModel();

		viewModel.minimumLengthProperty().set(8);
		viewModel.generatePassword();

		String password = viewModel.generatedPasswordProperty().get();
		assertNotNull(password);
		assertTrue(password.length() >= 8);

		viewModel.mustHaveAtLeastOneDigitProperty().set(true);
		viewModel.generatePassword();

		String newPassword = viewModel.generatedPasswordProperty().get();
		assertNotEquals(password, newPassword);
		assertTrue(newPassword.matches(".*\\d.*"));
	}
}
