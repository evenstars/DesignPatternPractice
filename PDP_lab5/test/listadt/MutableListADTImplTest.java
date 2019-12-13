package listadt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for the MutableListADT.
 */
public class MutableListADTImplTest {

  /**
   * Mutable list object for testing.
   */
  private MutableListADTImpl<Integer> mutableListADT;

  /**
   * Test for empty constructor.
   */
  @Test
  public void testEmptyConstructor() {
    mutableListADT = new MutableListADTImpl<>();
    assertEquals(0, mutableListADT.getSize());
  }

  /**
   * Test for constructor with null as given argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNull() {
    mutableListADT = new MutableListADTImpl<>(null);
  }

  /**
   * Test for constructor with given empty node.
   */
  @Test
  public void testConstructorWithEmptyNode() {
    mutableListADT = new MutableListADTImpl<>(new ListADTImpl<>());
    assertEquals(0, mutableListADT.getSize());
  }

  /**
   * Test for constructor with multiple node as given data.
   */
  @Test
  public void testConstructorWithMultipleNode() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    assertEquals(1, (int) mutableListADT.get(0));
    assertEquals(2, (int) mutableListADT.get(1));
  }

  /**
   * Test for converting this immutable list to mutable list when data is empty.
   */
  @Test
  public void testConvertWhenDataIsEmpty() {
    mutableListADT = new MutableListADTImpl<>();
    ImmutableListADTImpl<Integer> immutableListADT = (ImmutableListADTImpl<Integer>) mutableListADT.
            convertToImmutable();
    assertEquals(0, immutableListADT.getSize());
  }

  /**
   * Test for converting this immutable list to mutable list when data is not empty.
   */
  @Test
  public void testConvertNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    ImmutableListADTImpl<Integer> immutableListADT = (ImmutableListADTImpl<Integer>) mutableListADT.
            convertToImmutable();
    assertEquals(2, immutableListADT.getSize());
  }

  /**
   * Test for get size function when data is empty.
   */
  @Test
  public void testGetSizeEmpty() {
    mutableListADT = new MutableListADTImpl<>();
    assertEquals(0, mutableListADT.getSize());
  }

  /**
   * Test for get size function when data is not empty.
   */
  @Test
  public void testGetSizeNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    assertEquals(2, mutableListADT.getSize());
  }

  /**
   * Test for get function when giving index is negative number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetWhenNeg() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.get(-2);
  }

  /**
   * Test for get function when giving index exceeds the max range number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetWhenExceedMax() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    mutableListADT.get(5);
  }

  /**
   * Test for get function when data is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetWhenEmpty() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.get(0);
  }

  /**
   * Test for get function when data is not empty.
   */
  @Test
  public void testGetWhenNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    assertEquals(1, (int) mutableListADT.get(0));
  }

  /**
   * Test for map function.
   */
  @Test
  public void testMap() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    MutableListADTImpl<Integer> temp = (MutableListADTImpl) mutableListADT.map(a -> a * a);
    assertEquals(1, (int) temp.get(0));
    assertEquals(4, (int) temp.get(1));
  }

  /**
   * Test for addFront function when argument is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddFrontWhenNull() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.addFront(null);
  }

  /**
   * Test for addFront function when data is empty.
   */
  @Test
  public void testAddFrontWhenEmpty() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.addFront(1);
    assertEquals(1, (int) mutableListADT.get(0));
  }

  /**
   * Test for addFront function when data is not empty.
   */
  @Test
  public void testAddFrontWhenNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    mutableListADT.addFront(1);
    assertEquals(1, (int) mutableListADT.get(0));
  }

  /**
   * Test for addBack function when argument is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddBackWhenNull() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.addBack(null);
  }

  /**
   * Test for addBack function when data is empty..
   */
  @Test
  public void testAddBackWhenEmpty() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.addBack(1);
    assertEquals(1, (int) mutableListADT.get(0));
  }

  /**
   * Test for addBack function when data is not empty.
   */
  @Test
  public void testAddBackWhenNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    mutableListADT.addBack(3);
    assertEquals(3, (int) mutableListADT.get(2));
  }

  /**
   * Test for add function when argument data is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddWhenNull() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.add(0, null);
  }

  /**
   * Test for add function when data is empty.
   */
  @Test
  public void testAddWhenEmpty() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.add(0, 1);
    assertEquals(1, (int) mutableListADT.get(0));
  }

  /**
   * Test for add function when argument data is not empty.
   */
  @Test
  public void testAddWhenNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    mutableListADT.add(1, 3);
    assertEquals(3, (int) mutableListADT.get(1));
  }

  /**
   * Test for add function when argument index is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddWhenIndexNegative() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.add(-5, 2);
  }

  /**
   * Test for add function when argument index is zero.
   */
  @Test
  public void testAddWhenIndexIsZero() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    mutableListADT.add(0, 2);
    assertEquals(2, (int) mutableListADT.get(0));
  }

  /**
   * Test for add function when argument index is more than max range value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddWhenIndexExceedMax() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    mutableListADT.add(5, 3);
  }

  /**
   * Test for remove function when argument data is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveWhenNull() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.remove(null);
  }

  /**
   * Test for remove function when data is empty.
   */
  @Test
  public void testRemoveWhenEmpty() {
    mutableListADT = new MutableListADTImpl<>();
    mutableListADT.remove(5);
    assertEquals(0, mutableListADT.getSize());
  }

  /**
   * Test for remove function when data is not empty.
   */
  @Test
  public void testRemoveWhenNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    mutableListADT = new MutableListADTImpl<>(args);
    mutableListADT.remove(2);
    assertEquals(1, (int) mutableListADT.get(0));
    assertEquals(1, mutableListADT.getSize());
  }
}