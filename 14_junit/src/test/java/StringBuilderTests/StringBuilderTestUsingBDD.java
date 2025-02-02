package StringBuilderTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StringBuilder BDD Tests")
public class StringBuilderTestUsingBDD {
    private StringBuilder builder;


    @BeforeEach
    void setUp() {
        // Given
        builder = new StringBuilder("Hello");
    }

    @Test
    void given_emptyStringBuilder_when_appendIsCalled_then_textIsadded() {
        // Given
        StringBuilder emptyStringBuilder = new StringBuilder();

        // When
        emptyStringBuilder.append("World");

        // Then
        Assertions.assertEquals("World", emptyStringBuilder.toString());
    }

    @Test
    void given_stringBuilder_when_deleteIsCalled_then_textIsRemoved() {
        // When
        builder.delete(1, 3);

        // Then
        Assertions.assertEquals("Hlo", builder.toString());
    }

    @Test
    void given_stringBuilder_when_insertIsCalled_then_textIsInserted() {
        // When
        builder.insert(5, " World");

        // Then
        Assertions.assertEquals("Hello World", builder.toString());
    }

    @Test
    void given_stringBuilder_when_reverseIsCalled_then_reverseIsCalled() {
        // When
        builder.reverse();

        // Then
        Assertions.assertEquals("olleH", builder.toString());
    }

    @Test
    void given_stringBuilder_when_replaceIsCalled_then_textIsReplaced() {
        // When
        builder.replace(0, 5, "Hi");

        // Then
        Assertions.assertEquals("Hi", builder.toString());
    }

    @Test
    void given_stringBuilderWithCapacity_when_capacityIsChecked_then_capacityIsReturned() {
        // Given
        builder = new StringBuilder(50);

        // When
        int capacity = builder.capacity();

        // Then
        Assertions.assertEquals(50, capacity);
    }
}
