import org.junit.Before;

import static org.junit.Assert.assertEquals;

/**
 * this class is designed for test SimpleProjectile class.
 */
public class SimpleProjectileTest {

  private SimpleProjectile simpleProjectile;

  /**
   * used for set up data before test.
   */
  @Before
  public void setUp() {
    simpleProjectile = new SimpleProjectile(0, 0, 0, 10);
  }

  /**
   * used for test getState function.
   */
  @org.junit.Test
  public void getState() {
    assertEquals("At time 2.00: position is (0.00,0.38)", simpleProjectile.getState(2F));
  }
}