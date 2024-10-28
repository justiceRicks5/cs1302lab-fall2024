package test.model.TypeComparator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.project2.model.TypeComparator;

class TestTypeToString {

	@Test
    void testToStringReturnsType() {
        // Arrange
        TypeComparator comparator = new TypeComparator();
        
        // Act
        String result = comparator.toString();
        
        // Assert
        assertEquals("Type", result, "The toString method should return 'Type'");
    }

}
