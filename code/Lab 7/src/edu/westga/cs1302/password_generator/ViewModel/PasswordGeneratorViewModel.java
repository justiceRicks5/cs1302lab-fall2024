package edu.westga.cs1302.password_generator.ViewModel;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel in the MVVM pattern for the PasswordGenerator model.
 * 
 * @author justice ricks
 * @version fall 2024
 * 
 */
public class PasswordGeneratorViewModel {

	private PasswordGenerator passwordGenerator;

	private IntegerProperty minimumLengthProperty;
	private BooleanProperty mustHaveAtLeastOneDigitProperty;
	private BooleanProperty mustHaveAtLeastOneUpperCaseLetterProperty;
	private BooleanProperty mustHaveAtLeastOneLowerCaseLetterProperty;
	private StringProperty generatedPasswordProperty;

	/**
	 * Instantiates a new PasswordGeneratorViewModel.
	 * 
	 * @precondition none
	 * @postcondition minimumLengthProperty().get() == 1 &&
	 *                mustHaveAtLeastOneDigitProperty().get() == false &&
	 *                mustHaveAtLeastOneUpperCaseLetterProperty().get() == false &&
	 *                mustHaveAtLeastOneLowerCaseLetterProperty().get() == false &&
	 *                generatedPasswordProperty().get() == ""
	 */
	public PasswordGeneratorViewModel() {
		this.passwordGenerator = new PasswordGenerator(12345);

		this.minimumLengthProperty = new SimpleIntegerProperty(this.passwordGenerator.getMinimumLength());
		this.mustHaveAtLeastOneDigitProperty = new SimpleBooleanProperty(
				this.passwordGenerator.getMustHaveAtLeastOneDigit());
		this.mustHaveAtLeastOneUpperCaseLetterProperty = new SimpleBooleanProperty(
				this.passwordGenerator.getMustHaveAtLeastOneUpperCaseLetter());
		this.mustHaveAtLeastOneLowerCaseLetterProperty = new SimpleBooleanProperty(
				this.passwordGenerator.getMustHaveAtLeastOneLowerCaseLetter());
		this.generatedPasswordProperty = new SimpleStringProperty();
	}

	/**
	 * Gets the minimum length property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the minimum length property, which represents the minimum length for
	 *         the password.
	 */
	public IntegerProperty minimumLengthProperty() {
		return this.minimumLengthProperty;
	}

	/**
	 * Gets the must-have-at-least-one-digit property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the BooleanProperty indicating if the password must have at least one
	 *         digit.
	 */
	public BooleanProperty mustHaveAtLeastOneDigitProperty() {
		return this.mustHaveAtLeastOneDigitProperty;
	}

	/**
	 * Gets the must-have-at-least-one-uppercase-letter property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the BooleanProperty indicating if the password must have at least one
	 *         uppercase letter.
	 */
	public BooleanProperty mustHaveAtLeastOneUpperCaseLetterProperty() {
		return this.mustHaveAtLeastOneUpperCaseLetterProperty;
	}

	/**
	 * Gets the must-have-at-least-one-lowercase-letter property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the BooleanProperty indicating if the password must have at least one
	 *         lowercase letter.
	 */
	public BooleanProperty mustHaveAtLeastOneLowerCaseLetterProperty() {
		return this.mustHaveAtLeastOneLowerCaseLetterProperty;
	}

	/**
	 * Gets the generated password property.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the StringProperty containing the last generated password.
	 */
	public StringProperty generatedPasswordProperty() {
		return this.generatedPasswordProperty;
	}

	/**
	 * Updates the PasswordGenerator model with the current settings from the
	 * ViewModel properties.
	 * 
	 * @precondition none
	 * @postcondition PasswordGenerator model is updated with the settings from the
	 *                ViewModel properties.
	 */
	public void updatePasswordGeneratorSettings() {
		this.passwordGenerator.setMinimumLength(this.minimumLengthProperty.get());
		this.passwordGenerator.setMustHaveAtLeastOneDigit(this.mustHaveAtLeastOneDigitProperty.get());
		this.passwordGenerator
				.setMustHaveAtLeastOneUpperCaseLetter(this.mustHaveAtLeastOneUpperCaseLetterProperty.get());
		this.passwordGenerator
				.setMustHaveAtLeastOneLowerCaseLetter(this.mustHaveAtLeastOneLowerCaseLetterProperty.get());
	}

	/**
	 * Generates a password based on the current settings in the PasswordGenerator
	 * model and updates the generatedPasswordProperty with the new password.
	 * 
	 * @precondition none
	 * @postcondition generatedPasswordProperty().get() contains the new generated
	 *                password according to the current settings in the
	 *                PasswordGenerator model.
	 */
	public void generatePassword() {
		this.updatePasswordGeneratorSettings();
		String generatedPassword = this.passwordGenerator.generatePassword();
		this.generatedPasswordProperty.set(generatedPassword);
	}

}
