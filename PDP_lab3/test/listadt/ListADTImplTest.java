package listadt;


import static org.junit.Assert.assertEquals;

/**
 * Class used for test ListADTImpl.
 */
public class ListADTImplTest {

  /**
   * Object for test.
   */
  ListADT<Integer> listADT;

  /**
   * Test for reversing.
   */
  @org.junit.Test
  public void reverse() {
    listADT = new ListADTImpl<>();
    listADT.addBack(1);
    listADT.addBack(2);
    listADT.addBack(3);
    listADT.reverse();
    assertEquals("(3 2 1)", listADT.toString());
  }

  /**
   * Test for reversing when list is empty.
   */
  @org.junit.Test
  public void reverseEmpty() {
    listADT = new ListADTImpl<>();
    listADT.reverse();
    assertEquals("()", listADT.toString());
  }

  /**
   * Test for reversing when there is only one node.
   */
  @org.junit.Test
  public void reverseSingle() {
    listADT = new ListADTImpl<>();
    listADT.addBack(1);
    listADT.reverse();
    assertEquals("(1)", listADT.toString());
  }

  /**
   * Test for interleave.
   */
  @org.junit.Test
  public void interleave() {
    listADT = new ListADTImpl<>();
    listADT.addBack(1);
    listADT.addBack(2);
    listADT.addBack(3);
    listADT.addBack(4);
    listADT.addBack(5);
    listADT.addBack(6);
    listADT.interleave();
    assertEquals("(1 3 5 2 4 6)", listADT.toString());
  }

  /**
   * Test for interleave when there is no node.
   */
  @org.junit.Test
  public void interleaveEmpty() {
    listADT = new ListADTImpl<>();
    listADT.interleave();
    assertEquals("()", listADT.toString());
  }

  /**
   * Test for interleave when .
   */
  @org.junit.Test
  public void interleaveSingle() {
    listADT = new ListADTImpl<>();
    listADT.addBack(1);
    listADT.interleave();
    assertEquals("(1)", listADT.toString());

  }
}