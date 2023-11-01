package io.github.mjcro.tuples;

import java.util.Objects;

class TripletImpl<First, Second, Third> implements Triplet<First, Second, Third> {
    private final First first;
    private final Second second;
    private final Third third;

    TripletImpl(First first, Second second, Third third) {
        this.first = first;
        this.second = second;
        this.third = third;
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
    public Third getThird() {
        return third;
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
        return Objects.equals(getFirst(), that.getFirst())
                && Objects.equals(getSecond(), that.getSecond())
                && Objects.equals(getThird(), that.getThird());
    }

    @Override
    public String toString() {
        return "[" + getFirst() + "," + getSecond() + "," + getThird() + "]";
    }
}
