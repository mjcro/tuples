package io.github.mjcro.tuples;

import io.github.mjcro.interfaces.booleans.WithEmpty;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents immutable thread-safe pair of values.
 */
public interface Tuple<First, Second> extends WithEmpty {
    /**
     * Constructs tuple with no null values.
     *
     * @param first  First tuple value.
     * @param second Second tuple value.
     * @return Tuple instance.
     * @throws NullPointerException If null given as one of arguments.
     */
    static <First, Second> Tuple<First, Second> of(First first, Second second) {
        return ofNullable(
                Objects.requireNonNull(first, "first"),
                Objects.requireNonNull(second, "second")
        );
    }

    /**
     * Constructs tuple able to contain null values.
     *
     * @param first  First tuple value.
     * @param second Second tuple value.
     * @return Tuple instance.
     */
    static <First, Second> Tuple<First, Second> ofNullable(First first, Second second) {
        if (first == null && second == null) {
            //noinspection unchecked
            return (Tuple<First, Second>) EmptyTuple.INSTANCE;
        }
        return new TupleImpl<>(first, second);
    }

    /**
     * Constructs tuple able to contain null values.
     *
     * @param entry Map entry.
     * @return Tuple instance.
     */
    static <First, Second> Tuple<First, Second> ofNullable(Map.Entry<First, Second> entry) {
        if (entry == null) {
            return ofNullable(null, null);
        }
        return ofNullable(entry.getKey(), entry.getValue());
    }

    /**
     * @return First value.
     */
    First getFirst();

    /**
     * @return Second value.
     */
    Second getSecond();

    /**
     * @return True if both first and second values are null;
     */
    @Override
    default boolean isEmpty() {
        return getFirst() == null && getSecond() == null;
    }

    /**
     * @return Map.Entry value filled with tuple data.
     */
    default Map.Entry<First, Second> toMapEntry() {
        return new AbstractMap.SimpleImmutableEntry<>(getFirst(), getSecond());
    }

    /**
     * Constructs new tuple with mapped values.
     *
     * @param firstMapper  Mapper function to use on first tuple value.
     * @param secondMapper Mapper function to use on second tuple value.
     * @return Tuple instance.
     */
    default <A, B> Tuple<A, B> map(
            Function<? super First, ? extends A> firstMapper,
            Function<? super Second, ? extends B> secondMapper
    ) {
        return Tuple.ofNullable(
                firstMapper.apply(getFirst()),
                secondMapper.apply(getSecond())
        );
    }

    /**
     * Constructs new tuple with mapped first value.
     *
     * @param mapper Mapper function.
     * @return Tuple instance.
     */
    default <T> Tuple<T, Second> mapFirst(Function<? super First, ? extends T> mapper) {
        return this.map(mapper, Function.identity());
    }

    /**
     * Constructs new tuple with mapped second value.
     *
     * @param mapper Mapper function.
     * @return Tuple instance.
     */
    default <T> Tuple<First, T> mapSecond(Function<? super Second, ? extends T> mapper) {
        return this.map(Function.identity(), mapper);
    }

    /**
     * @return Tuple with switched values.
     */
    default Tuple<Second, First> rotate() {
        return Tuple.ofNullable(getSecond(), getFirst());
    }
}
