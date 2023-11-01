package io.github.mjcro.tuples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TripletTest {
    @Test(expectedExceptions = NullPointerException.class)
    public void testOfFirstNull() {
        Triplet.of(null, "second", "third");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOfSecondNull() {
        Triplet.of("first", null, "third");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testOfThirdNull() {
        Triplet.of("first", "second", null);
    }

    @Test
    public void testGetters() {
        Triplet<String, String, String> t = Triplet.of("foo", "bar", "baz");

        Assert.assertEquals(t.getFirst(), "foo");
        Assert.assertEquals(t.getSecond(), "bar");
        Assert.assertEquals(t.getThird(), "baz");
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(Triplet.ofNullable(null, null, null).isEmpty());
        Assert.assertFalse(Triplet.ofNullable(null, null, null).isNotEmpty());

        Assert.assertFalse(Triplet.ofNullable("one", "two", null).isEmpty());
        Assert.assertTrue(Triplet.ofNullable("one", "two", null).isNotEmpty());

        Assert.assertFalse(Triplet.ofNullable("one", null, "three").isEmpty());
        Assert.assertTrue(Triplet.ofNullable("one", null, "three").isNotEmpty());

        Assert.assertFalse(Triplet.ofNullable(null, "two", "three").isEmpty());
        Assert.assertTrue(Triplet.ofNullable(null, "two", "three").isNotEmpty());

        Assert.assertFalse(Triplet.ofNullable("foo", "bar", "three").isEmpty());
        Assert.assertTrue(Triplet.ofNullable("foo", "bar", "three").isNotEmpty());
    }

    @Test
    public void testNullInNullable() {
        Triplet<Integer, String, Object> t = Triplet.ofNullable(null, null, null);

        Assert.assertNull(t.getFirst());
        Assert.assertNull(t.getSecond());
        Assert.assertNull(t.getThird());
    }

    @Test
    public void testEquals() {
        Assert.assertEquals(Triplet.of(1, "two", "xxx"), Triplet.of(1, "two", "xxx"));
        Assert.assertEquals(Triplet.of(1, 2L, 3.), Triplet.of(1, 2L, 3.));
        Assert.assertEquals(Triplet.ofNullable(1, null, "foo"), Triplet.ofNullable(1, null, "foo"));
        Assert.assertEquals(Triplet.ofNullable(null, null, null), Triplet.ofNullable(null, null, null));
    }

    @Test(dependsOnMethods = "testEquals")
    public void testMap() {
        Assert.assertEquals(
                Triplet.of(2, "foo", 5).map(String::valueOf, String::toUpperCase, $ -> $ * $),
                Triplet.of("2", "FOO", 25)
        );
    }

    @Test(dependsOnMethods = "testEquals")
    public void testMapFirst() {
        Assert.assertEquals(
                Triplet.of(2, "foo", 5).mapFirst(String::valueOf),
                Triplet.of("2", "foo", 5)
        );
    }

    @Test(dependsOnMethods = "testEquals")
    public void testMapSecond() {
        Assert.assertEquals(
                Triplet.of(2, "foo", 5).mapSecond(String::toUpperCase),
                Triplet.of(2, "FOO", 5)
        );
    }

    @Test(dependsOnMethods = "testEquals")
    public void testMapThird() {
        Assert.assertEquals(
                Triplet.of(2, "foo", 5).mapThird($ -> $ * $),
                Triplet.of(2, "foo", 25)
        );
    }

    @Test
    public void testWithout() {
        Assert.assertEquals(Triplet.of(1, 2L, 3.).withoutFirst(), Tuple.of(2L, 3.));
        Assert.assertEquals(Triplet.of(1, 2L, 3.).withoutSecond(), Tuple.of(1, 3.));
        Assert.assertEquals(Triplet.of(1, 2L, 3.).withoutThird(), Tuple.of(1, 2L));
    }
}