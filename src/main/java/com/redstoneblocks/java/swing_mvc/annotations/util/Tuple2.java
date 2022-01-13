package com.redstoneblocks.java.swing_mvc.annotations.util;

/**
 * A utility class for a 2-tuple (immutable)
 *
 * @param <T1> first type
 * @param <T2> second type
 */
public class Tuple2<T1, T2> {
    public final T1 first;
    public final T2 second;

    public Tuple2(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}