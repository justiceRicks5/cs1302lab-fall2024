package edu.westga.cs1302.password_generator.viewmodel;

import java.util.Random;
import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * view model to bind to the Ui
 * 
 * @author jr00381
 * @version Fall 2024
 * 
 */
public class ViewModel {
	private StringProperty minimumLength;
	private BooleanProperty requireDigits;
	private BooleanProperty requireLowercase;
	private BooleanProperty requireUppercase;

	private StringProperty password;
	private StringProperty errorText;
	private ObservableList<String> passwordList;

	private PasswordGenerator generator;

	/**
	 * constror that is initalizing everything in the program
	 * 
	 */
	public ViewModel() {
		this.minimumLength = new SimpleStringProperty("1");
		this.requireDigits = new SimpleBooleanProperty(false);
		this.requireLowercase = new SimpleBooleanProperty(false);
		this.requireUppercase = new SimpleBooleanProperty(false);

		this.password = new SimpleStringProperty("");
		this.errorText = new SimpleStringProperty("");
		this.passwordList = FXCollections.observableArrayList();

		Random randomNumberGenerator = new Random();
		this.generator = new PasswordGenerator(randomNumberGenerator.nextLong());

		this.minimumLength.addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d+")) {
				this.errorText.set("Minimum length must be a positive integer.");
			} else {
				this.errorText.set("");
			}
		});
	}

	/**
	 * returns mininmum length
	 * 
	 * @return minimumLenth the min length
	 */
	public StringProperty getMinimumLength() {
		return this.minimumLength;
	}

	/**
	 * Returns the requireDigits property,
	 * 
	 * @return requireDigits property, allowing UI binding for the digit requirement
	 *         checkbox.
	 */
	public BooleanProperty getRequireDigits() {
		return this.requireDigits;
	}

	/**
	 * Returns the requireLowercase
	 * 
	 * @return requireUppercase property, allowing UI binding for the uppercase
	 *         requirement checkbox.
	 */
	public BooleanProperty getRequireLowercase() {
		return this.requireLowercase;
	}

	/**
	 * : Returns the requireUppercase
	 * 
	 * @return requireUppercase property, allowing UI binding for the uppercase
	 *         requirement checkbox.
	 */
	public BooleanProperty getRequireUppercase() {
		return this.requireUppercase;
	}

	/**
	 * Returns the password
	 * 
	 * @return password property, allowing UI binding to display the latest
	 *         generated password.
	 */
	public StringProperty getPassword() {
		return this.password;
	}

	/**
	 * Returns the errorText
	 * 
	 * @return errorText property, allowing UI binding to display validation or
	 *         error messages.
	 */
	public StringProperty getErrorText() {
		return this.errorText;
	}

	/**
	 * Returns passwordList,
	 * 
	 * @return passwordList, allowing the UI to bind to a ListView and display all
	 *         generated passwords.
	 */
	public ObservableList<String> getPasswordList() {
		return this.passwordList;
	}

	/**
	 * Core method that validates minimumLength, applies user-specified requirements
	 * to PasswordGenerator, and generates a password if the input is valid.
	 * 
	 * @precondtion minimum length cannot be less than 1
	 * 
	 */
	public void generatePassword() {
		int minimumLength;

		this.password.setValue("");

		try {
			minimumLength = Integer.parseInt(this.minimumLength.getValue());
			if (minimumLength < 1) {
				throw new IllegalArgumentException("Minimum length must be at least 1.");
			}
			this.generator.setMinimumLength(minimumLength);
		} catch (NumberFormatException numberError) {
			this.errorText.set("Invalid Minimum Length: must be a positive integer.");
			return;
		} catch (IllegalArgumentException invalidLengthError) {
			this.errorText.set("Invalid Minimum Length: " + invalidLengthError.getMessage());
			return;
		}

		this.generator.setMustHaveAtLeastOneDigit(this.requireDigits.getValue());
		this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.requireLowercase.getValue());
		this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.requireUppercase.getValue());

		String newPassword = this.generator.generatePassword();
		this.password.setValue(newPassword);
		this.passwordList.add(newPassword);
	}
}
