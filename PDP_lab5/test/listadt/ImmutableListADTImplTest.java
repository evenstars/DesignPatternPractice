package listadt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for the ImmutableListADT.
 */
public class ImmutableListADTImplTest {

  /**
   * Data used for testing.
   */
  private ImmutableListADTImpl<Integer> immutableListADT;

  /**
   * Test for empty constructor.
   */
  @Test
  public void testEmptyConstructor() {
    immutableListADT = new ImmutableListADTImpl<>();
    assertEquals(0, immutableListADT.getSize());
  }

  /**
   * Test for not null constructor with null argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNull() {
    immutableListADT = new ImmutableListADTImpl<>(null);
  }

  /**
   * Test for constructor giving empty node as data.
   */
  @Test
  public void testConstructorWithEmptyNode() {
    immutableListADT = new ImmutableListADTImpl<>(new ListADTImpl<>());
    assertEquals(0, immutableListADT.getSize());
  }

  /**
   * Test for Constructor with multiple node.
   */
  @Test
  public void testConstructorWithMultipleNode() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    immutableListADT = new ImmutableListADTImpl<>(args);
    assertEquals(1, (int) immutableListADT.get(0));
    assertEquals(2, (int) immutableListADT.get(1));
  }

  /**
   * Test for converting to immutable data when data is empty.
   */
  @Test
  public void testConvertWhenDataIsEmpty() {
    immutableListADT = new ImmutableListADTImpl<>();
    MutableListADTImpl<Integer> mutableListADT = (MutableListADTImpl) immutableListADT.
            convertToMutable();
    assertEquals(0, mutableListADT.getSize());
  }

  /**
   * Test for converting to immutable data when data is not empty.
   */
  @Test
  public void testConvertNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    immutableListADT = new ImmutableListADTImpl<>(args);
    MutableListADTImpl<Integer> mutableListADT = (MutableListADTImpl) immutableListADT.
            convertToMutable();
    assertEquals(2, mutableListADT.getSize());
  }

  /**
   * Test for getting the size when data is empty node.
   */
  @Test
  public void testGetSizeEmpty() {
    immutableListADT = new ImmutableListADTImpl<>();
    assertEquals(0, immutableListADT.getSize());
  }

  /**
   * Test for getting the size when data is not empty node.
   */
  @Test
  public void testGetSizeNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    immutableListADT = new ImmutableListADTImpl<>(args);
    assertEquals(2, immutableListADT.getSize());
  }

  /**
   * Test for get function when index is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetWhenNeg() {
    immutableListADT = new ImmutableListADTImpl<>();
    immutableListADT.get(-2);
  }

  /**
   * Test for get function when index exceed the max length value.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetWhenExceedMax() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    immutableListADT = new ImmutableListADTImpl<>(args);
    immutableListADT.get(5);
  }

  /**
   * Test for get function when data is empty.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetWhenEmpty() {
    immutableListADT = new ImmutableListADTImpl<>();
    immutableListADT.get(0);
  }

  /**
   * Test for get function when data is not empty.
   */
  @Test
  public void testGetWhenNotEmpty() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    immutableListADT = new ImmutableListADTImpl<>(args);
    assertEquals(1, (int) immutableListADT.get(0));
  }

  /**
   * Test for mapping and return immutable object.
   */
  @Test
  public void testMap() {
    ListADT<Integer> args = new ListADTImpl<>();
    args.addBack(1);
    args.addBack(2);
    immutableListADT = new ImmutableListADTImpl<>(args);
    ImmutableListADTImpl<Integer> temp = (ImmutableListADTImpl) immutableListADT.map(a -> a * a);
    assertEquals(1, (int) temp.get(0));
    assertEquals(4, (int) temp.get(1));
  }
}