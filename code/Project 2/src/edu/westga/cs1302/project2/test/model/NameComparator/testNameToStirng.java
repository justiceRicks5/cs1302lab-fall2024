package edu.westga.cs1302.project2.test.model.NameComparator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.NameComparator;

class testNameToStirng {

	@Test
    void testToStringReturnsName() {
        // Arrange
        NameComparator comparator = new NameComparator();
        
        // Act
        String result = comparator.toString();
        
        // Assert
        assertEquals("Name", result, "The toString method should return 'Name'");
    }
}
