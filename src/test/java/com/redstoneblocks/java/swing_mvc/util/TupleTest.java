package com.redstoneblocks.java.swing_mvc.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TupleTest {
    @Test
    void whenCreatedWithValues_Tuple2ShouldReadTheSameValues() {
        Tuple2<String, String> tuple = new Tuple2<>("Bob", "Alice");
        assertEquals("Bob", tuple.first);
        assertEquals("Alice", tuple.second);
    }

    @Test
    void whenCreatedWithValues_Tuple4ShouldReadTheSameValues() {
        Tuple4<String, String, String, String> tuple = new Tuple4<>("Bob", "Alice", "Charlie", "Dave");
        assertEquals("Bob", tuple.first);
        assertEquals("Alice", tuple.second);
        assertEquals("Charlie", tuple.third);
        assertEquals("Dave", tuple.fourth);
    }

}