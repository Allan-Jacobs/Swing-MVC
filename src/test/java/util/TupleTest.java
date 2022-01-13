package util;

import com.redstoneblocks.java.swing_mvc.annotations.util.Tuple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TupleTest {
    @Test
    void whenCreatedWithValues_itShouldReadTheSameValues() {
        Tuple<String, String, String> tuple = new Tuple<>("Bob", "Alice", "Charlie");
        assertEquals("Bob", tuple.first);
        assertEquals("Alice", tuple.second);
        assertEquals("Charlie", tuple.third);
    }
}