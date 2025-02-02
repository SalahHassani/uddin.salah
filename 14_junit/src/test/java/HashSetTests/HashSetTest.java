package HashSetTests;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class HashSetTest {

    private HashSet<String> set;

    @BeforeEach
    public void setUp() {
        set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
    }

    // Method 1: Testing add method
    @ParameterizedTest
    @ValueSource(strings = {"d", "e", "f"})
    public void testAddMethodInHashSet(String value) {
        Assertions.assertTrue(set.add(value));
        // Since no duplicates are allowed...
        Assertions.assertFalse(set.add(value));
    }

    // Method 2: Testing remove method
    @Test
    public void testRemoveMethodInHashSet() {
        Assertions.assertTrue(set.remove("a"));
        Assertions.assertFalse(set.remove("a"));
        Assertions.assertFalse(set.remove("z"));
    }

    // Method 3: Testing contains method
    @Test
    public void testContainsMethodInHashSet() {
        Assertions.assertTrue(set.contains("b"));
        Assertions.assertFalse(set.contains("z"));
    }

    // Method 4: Testing size method
    @Test
    public void testSizeMethodInHashSet() {
        Assertions.assertEquals(3, set.size());
        set.add("d");
        Assertions.assertEquals(4, set.size());
        set.add("d");
        Assertions.assertEquals(4, set.size());
    }

    // Method 5: Testing clear method
    @Test
    public void testClearMethodInHashSet() {
        set.clear();
        Assertions.assertTrue(set.isEmpty());
        Assertions.assertEquals(0, set.size());
    }

    // Method 6: Testing isEmpty method
    @Test
    public void testIsEmptyMethodInHashSet() {
        Assertions.assertFalse(set.isEmpty());
        set.clear();
        Assertions.assertTrue(set.isEmpty());
        set.add("x");
        Assertions.assertFalse(set.isEmpty());
    }
}
