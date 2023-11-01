package io.github.mjcro.tuples;

class EmptyTuple implements Tuple<Object, Object> {
    static final EmptyTuple INSTANCE = new EmptyTuple();

    @Override
    public Object getFirst() {
        return null;
    }

    @Override
    public Object getSecond() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Tuple<Object, Object> rotate() {
        return INSTANCE;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof Tuple)) {
            return false;
        }

        Tuple<?, ?> that = (Tuple<?, ?>) obj;
        return that.getFirst() == null && that.getSecond() == null;
    }

    @Override
    public String toString() {
        return "[null,null]";
    }
}
