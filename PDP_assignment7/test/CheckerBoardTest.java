import org.junit.Test;

import model.generate.CheckerBoard;


/**
 * Test for the checkerBoard class.
 */
public class CheckerBoardTest {

  /**
   * Test object here.
   */
  private CheckerBoard checkerBoard;

  /**
   * Test for the square size is negative in the constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegSquare() {
    checkerBoard = new CheckerBoard(3, -2);
  }

  /**
   * Test for the square is zero in the constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroSquare() {
    checkerBoard = new CheckerBoard(5, 0);
  }
}