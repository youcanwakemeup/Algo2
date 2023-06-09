import exceptions.NoItemFoundInArrayException;
import exceptions.OutOfRangeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class IntegerArrayImplTest {
    private IntegerArray integerArray;

    @Before
    public void setUp() {
        integerArray = new IntegerArrayImpl(0);
    }
    @Test
    public void testAddAndGet() {
        integerArray.add(1);
        integerArray.add(2);
        integerArray.add(3);


        Assert.assertEquals(Optional.of(3), integerArray.get(2));
    }

    @Test
    public void testAddAtIndex() {
        integerArray.add(1);
        integerArray.add(2);

        integerArray.add(1, 3);


        Assert.assertEquals(Optional.of(3), integerArray.get(1));
    }

    @Test
    public void testSet() {
        integerArray.add(1);
        integerArray.add(2);
        integerArray.add(3);

        integerArray.set(1,4);


        Assert.assertEquals(Optional.of(4), integerArray.get(2));
    }


    @Test
    public void testRemoveByItem() {
        integerArray.add(1);
        integerArray.add(2);
        integerArray.add(3);

        integerArray.remove(2);

        Assert.assertEquals(Optional.of(1), integerArray.get(0));
        Assert.assertEquals(Optional.of(2), integerArray.get(1));
        Assert.assertEquals(2, integerArray.size());

        Assert.assertFalse(integerArray.contains(3));
    }

    @Test
    public void testRemoveByIndex() {
        integerArray.add(2);
        integerArray.add(1);
        integerArray.add(3);

        integerArray.remove(1);


        Assert.assertEquals(2, integerArray.size());
    }

    @Test
    public void testContains() {
        integerArray.add(2);
        integerArray.add(1);
        integerArray.add(3);

        Assert.assertTrue(integerArray.contains(2));
        Assert.assertFalse(integerArray.contains(5));
    }

    @Test
    public void testIndexOf() {
        integerArray.add(2);
        integerArray.add(1);
        integerArray.add(3);

        Assert.assertEquals(1, integerArray.indexOf(1));
        Assert.assertEquals(-1, integerArray.indexOf(6));
    }

    @Test
    public void testLastIndexOf() {
        integerArray.add(2);
        integerArray.add(1);
        integerArray.add(3);

        Assert.assertEquals(2, integerArray.lastIndexOf(3));
        Assert.assertEquals(-1, integerArray.lastIndexOf(6));
    }

    @Test
    public void testSize() {
        integerArray.add(3);
        integerArray.add(3);

        Assert.assertEquals(2, integerArray.size());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(integerArray.isEmpty());

        integerArray.add(3);
        Assert.assertFalse(integerArray.isEmpty());
    }

    @Test
    public void testClear() {
        integerArray.add(2);
        integerArray.add(3);

        integerArray.clear();

        Assert.assertEquals(0, integerArray.size());
        Assert.assertTrue(integerArray.isEmpty());
    }
    @Test
    public void testToArray() {

        integerArray.add(2);

        Integer[] arr = integerArray.toArray();

        Assert.assertArrayEquals(new Integer[]{2}, arr);
    }

    @Test
    public void testExtendArray() {
        integerArray.add(1);
        integerArray.add(2);

        int newSize = integerArray.extendArray(5);

        Assert.assertEquals(5, newSize);
        Assert.assertEquals(5, integerArray.getArr().length);
    }

    @Test
    public void testEquals() {
        IntegerArray otherList = new IntegerArrayImpl(0);
        otherList.add(1);
        otherList.add(2);
        otherList.add(1);
        otherList.add(2);

        Assert.assertTrue(otherList.equals(otherList));
    }
    @Test(expected = OutOfRangeException.class)
    public void testAddAtIndexOutOfRange() {
        integerArray.add(1);

        integerArray.add(2, 1);
    }

    @Test(expected = NoItemFoundInArrayException.class)
    public void testRemoveByItemItemNotFound() {
        integerArray.add(1);
        integerArray.add(2);

        integerArray.remove(3);
    }

    @Test(expected = OutOfRangeException.class)
    public void testRemoveByIndexOutOfRange() {
        integerArray.add(3);
        integerArray.add(2);

        integerArray.remove(2);
    }

    @Test(expected = OutOfRangeException.class)
    public void testGetOutOfRange() {
        integerArray.add(1);
        integerArray.add(3);
        integerArray.get(2);
    }
}
