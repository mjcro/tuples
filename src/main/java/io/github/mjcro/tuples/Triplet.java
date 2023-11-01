package io.github.mjcro.tuples;

import io.github.mjcro.interfaces.booleans.WithEmpty;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents immutable thread-safe triplet of values.
 */
public interface Triplet<First, Second, Third> extends WithEmpty {
    /**
     * Constructs triplet with no null values.
     *
     * @param first  First triplet value.
     * @param second Second triplet value.
     * @param third  Third triplet value.
     * @return Triplet instance.
     * @throws NullPointerException If null given as one of arguments.
     */
    static <First, Second, Third> Triplet<First, Second, Third> of(First first, Second second, Third third) {
        return ofNullable(
                Objects.requireNonNull(first, "first"),
                Objects.requireNonNull(second, "second"),
                Objects.requireNonNull(third, "third")
        );
    }

    /**
     * Constructs triplet with no null values.
     *
     * @param first  First triplet value.
     * @param second Second triplet value.
     * @param third  Third triplet value.
     * @return Triplet instance.
     */
    static <First, Second, Third> Triplet<First, Second, Third> ofNullable(First first, Second second, Third third) {
        if (first == null && second == null) {
            //noinspection unchecked
            return (Triplet<First, Second, Third>) EmptyTriplet.INSTANCE;
        }
        return new TripletImpl<>(first, second, third);
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
     * @return Third value.
     */
    Third getThird();

    /**
     * @return True if all first, second and third values are null;
     */
    @Override
    default boolean isEmpty() {
        return getFirst() == null && getSecond() == null && getThird() == null;
    }

    /**
     * Constructs tuple from triplet discarding first triplet value.
     *
     * @return Tuple.
     */
    default Tuple<Second, Third> withoutFirst() {
        return Tuple.ofNullable(getSecond(), getThird());
    }

    /**
     * Constructs tuple from triplet discarding second triplet value.
     *
     * @return Tuple.
     */
    default Tuple<First, Third> withoutSecond() {
        return Tuple.ofNullable(getFirst(), getThird());
    }

    /**
     * Constructs tuple from triplet discarding third triplet value.
     *
     * @return Tuple.
     */
    default Tuple<First, Second> withoutThird() {
        return Tuple.ofNullable(getFirst(), getSecond());
    }

    /**
     * Constructs new triplet with mapped values.
     *
     * @param firstMapper  Mapper function to use on first triplet value.
     * @param secondMapper Mapper function to use on second triplet value.
     * @param thirdMapper  Mapper function to use on third triplet value.
     * @return Triplet instance.
     */
    default <A, B, C> Triplet<A, B, C> map(
            Function<? super First, ? extends A> firstMapper,
            Function<? super Second, ? extends B> secondMapper,
            Function<? super Third, ? extends C> thirdMapper
    ) {
        return Triplet.ofNullable(
                firstMapper.apply(getFirst()),
                secondMapper.apply(getSecond()),
                thirdMapper.apply(getThird())
        );
    }

    /**
     * Constructs new triplet with mapped first value.
     *
     * @param mapper Mapper function.
     * @return Triplet instance.
     */
    default <A> Triplet<A, Second, Third> mapFirst(Function<? super First, ? extends A> mapper) {
        return map(mapper, Function.identity(), Function.identity());
    }

    /**
     * Constructs new triplet with mapped second value.
     *
     * @param mapper Mapper function.
     * @return Triplet instance.
     */
    default <B> Triplet<First, B, Third> mapSecond(Function<? super Second, ? extends B> mapper) {
        return map(Function.identity(), mapper, Function.identity());
    }

    /**
     * Constructs new triplet with mapped third value.
     *
     * @param mapper Mapper function.
     * @return Triplet instance.
     */
    default <C> Triplet<First, Second, C> mapThird(Function<? super Third, ? extends C> mapper) {
        return map(Function.identity(), Function.identity(), mapper);
    }
}
