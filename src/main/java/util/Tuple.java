package util;

/**
 * A utility class for a 3-tuple (immutable)
 *
 * @param <T1> first type
 * @param <T2> second type
 * @param <T3> third type
 */
public class Tuple<T1, T2, T3> {
    public final T1 first;
    public final T2 second;
    public final T3 third;

    public Tuple(T1 first, T2 second, T3 third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}