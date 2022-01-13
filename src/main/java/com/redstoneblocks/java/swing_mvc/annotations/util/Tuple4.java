package com.redstoneblocks.java.swing_mvc.annotations.util;

/**
 * A utility class for a 4-tuple (immutable)
 *
 * @param <T1> first type
 * @param <T2> second type
 * @param <T3> third type
 * @param <T4> fourth tuple
 */
public class Tuple4<T1, T2, T3, T4> {
    public final T1 first;
    public final T2 second;
    public final T3 third;
    public final T4 fourth;

    public Tuple4(T1 first, T2 second, T3 third, T4 fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
    }
}