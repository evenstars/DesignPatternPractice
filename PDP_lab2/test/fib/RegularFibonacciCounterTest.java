package fib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * The test class for the functions of RegularFibonacciCounter.
 */
public class RegularFibonacciCounterTest {

  /**
   * Test target class.
   */
  private FibonacciCounter fibonacciCounter;

  /**
   * Test for incrementByOne function when count is 1.
   */
  @org.junit.Test
  public void testIncrementByOneCountFromOne() {
    fibonacciCounter = new RegularFibonacciCounter();
    fibonacciCounter.incrementByOne();
    assertEquals(2, fibonacciCounter.getCount().intValue());
    assertEquals(0, fibonacciCounter.getFibonacciNumber().intValue());
  }

  /**
   * Test for incrementByOne function when count is 2.
   */
  @org.junit.Test
  public void testIncrementByOneCountFromTwo() {
    fibonacciCounter = new RegularFibonacciCounter();
    fibonacciCounter.incrementByOne();
    fibonacciCounter.incrementByOne();
    assertEquals(3, fibonacciCounter.getCount().intValue());
    assertEquals(1, fibonacciCounter.getFibonacciNumber().intValue());
  }

  /**
   * Test for incrementByOne function when count is beyond 2..
   */
  @org.junit.Test
  public void testIncrementByOneCountFromMoreThanTwo() {
    fibonacciCounter = new RegularFibonacciCounter();
    fibonacciCounter.incrementByOne();
    fibonacciCounter.incrementByOne();
    fibonacciCounter.incrementByOne();
    fibonacciCounter.incrementByOne();
    assertEquals(5, fibonacciCounter.getCount().intValue());
    assertEquals(2, fibonacciCounter.getFibonacciNumber().intValue());
  }

  /**
   * Test for decrementByOne when count is beyond One.
   */
  @org.junit.Test
  public void decrementByOneCountBeyondOne() {
    fibonacciCounter = new RegularFibonacciCounter();
    fibonacciCounter.incrementByOne();
    fibonacciCounter.incrementByOne();
    try {
      fibonacciCounter.decrementByOne();
    } catch (Exception e) {
      fail("count is already minimum.");
    }
    assertEquals(2, fibonacciCounter.getCount().intValue());
    assertEquals(0, fibonacciCounter.getFibonacciNumber().intValue());
  }

  /**
   * Test for decrementByOne when count equals One.
   */
  @org.junit.Test()
  public void decrementByOneCountEqualsOne() {
    fibonacciCounter = new RegularFibonacciCounter();
    try {
      fibonacciCounter.decrementByOne();
      fail("count is already minimum.");
    } catch (Exception e) {
      //do not do anything except catch the exception and let the test continue
    }
  }

  /**
   * Test for getCount function.
   */
  @org.junit.Test
  public void getCount() {
    fibonacciCounter = new RegularFibonacciCounter();
    assertEquals(1, fibonacciCounter.getCount().intValue());
  }

  /**
   * Test for gerFibonacciNumber function.
   */
  @org.junit.Test
  public void getFibonacciNumber() {
    fibonacciCounter = new RegularFibonacciCounter();
    fibonacciCounter.incrementByOne();
    assertEquals(0, fibonacciCounter.getFibonacciNumber().intValue());
  }
}