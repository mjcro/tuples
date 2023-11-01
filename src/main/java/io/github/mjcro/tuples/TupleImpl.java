package io.github.mjcro.tuples;

import java.util.Objects;

class TupleImpl<First, Second> implements Tuple<First, Second> {
    private final First first;
    private final Second second;

    TupleImpl(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public First getFirst() {
        return first;
    }

    @Override
    public Second getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Tuple)) {
            return false;
        }

        Tuple<?, ?> that = (Tuple<?, ?>) obj;
        return Objects.equals(getFirst(), that.getFirst()) && Objects.equals(getSecond(), that.getSecond());
    }

    @Override
    public String toString() {
        return "[" + getFirst() + "," + getSecond() + "]";
    }
}
