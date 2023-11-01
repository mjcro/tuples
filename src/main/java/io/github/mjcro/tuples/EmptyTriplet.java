package io.github.mjcro.tuples;

import java.util.Objects;

class EmptyTriplet implements Triplet<Object, Object, Object> {
    static final EmptyTriplet INSTANCE = new EmptyTriplet();

    @Override
    public Object getFirst() {
        return null;
    }

    @Override
    public Object getSecond() {
        return null;
    }

    @Override
    public Object getThird() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond(), getThird());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Triplet)) {
            return false;
        }

        Triplet<?, ?, ?> that = (Triplet<?, ?, ?>) obj;
        return that.getFirst() == null && that.getSecond() == null && that.getThird() == null;
    }

    @Override
    public String toString() {
        return "[null,null,null]";
    }
}
