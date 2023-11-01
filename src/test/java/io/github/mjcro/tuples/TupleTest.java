package io.github.mjcro.tuples;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.AbstractMap;

public class TupleTest {
    @Test(expectedExceptions = NullPointerException.class)
    public void testOfFirstNull() {
        Tuple.of(null, "second");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOfSecondNull() {
        Tuple.of("first", null);
    }

    @Test
    public void testGetters() {
        Tuple<String, String> t = Tuple.of("foo", "bar");

        Assert.assertEquals(t.getFirst(), "foo");
        Assert.assertEquals(t.getSecond(), "bar");
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(Tuple.ofNullable(null, null).isEmpty());
        Assert.assertFalse(Tuple.ofNullable(null, null).isNotEmpty());

        Assert.assertFalse(Tuple.ofNullable("one", null).isEmpty());
        Assert.assertTrue(Tuple.ofNullable("one", null).isNotEmpty());

        Assert.assertFalse(Tuple.ofNullable(null, "two").isEmpty());
        Assert.assertTrue(Tuple.ofNullable(null, "two").isNotEmpty());

        Assert.assertFalse(Tuple.ofNullable("foo", "bar").isEmpty());
        Assert.assertTrue(Tuple.ofNullable("foo", "bar").isNotEmpty());
    }

    @Test
    public void testNullInNullable() {
        Tuple<Integer, String> t = Tuple.ofNullable(null, null);

        Assert.assertNull(t.getFirst());
        Assert.assertNull(t.getSecond());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(Tuple.of(1, "two"), Tuple.of(1, "two"));
        Assert.assertEquals(Tuple.of(1, 2L), Tuple.of(1, 2L));
        Assert.assertEquals(Tuple.ofNullable(1, null), Tuple.ofNullable(1, null));
        Assert.assertEquals(Tuple.ofNullable(null, 2L), Tuple.ofNullable(null, 2L));
        Assert.assertEquals(Tuple.ofNullable(null, null), Tuple.ofNullable(null, null));
    }

    @Test(dependsOnMethods = "testEquals")
    public void testRotate() {
        Assert.assertEquals(Tuple.of(1, "two").rotate(), Tuple.of("two", 1));
    }

    @Test(dependsOnMethods = "testEquals")
    public void testMap() {
        Assert.assertEquals(
                Tuple.of(2, "foo").map(String::valueOf, String::toUpperCase),
                Tuple.of("2", "FOO")
        );
    }

    @Test(dependsOnMethods = "testEquals")
    public void testMapFirst() {
        Assert.assertEquals(
                Tuple.of(2, "foo").mapFirst(String::valueOf),
                Tuple.of("2", "foo")
        );
    }

    @Test(dependsOnMethods = "testEquals")
    public void testMapSecond() {
        Assert.assertEquals(
                Tuple.of(2, "foo").mapSecond(String::toUpperCase),
                Tuple.of(2, "FOO")
        );
    }

    @Test
    public void testToMapEntry() {
        Assert.assertEquals(
                Tuple.of(1, 1L).toMapEntry(),
                new AbstractMap.SimpleImmutableEntry<>(1, 1L)
        );
    }
}