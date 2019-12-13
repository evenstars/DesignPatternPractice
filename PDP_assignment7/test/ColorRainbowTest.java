import org.junit.Test;

import model.generate.ColorRainbow;


/**
 * Test for the color rainbow class.
 */
public class ColorRainbowTest {

  /**
   * Test object.
   */
  private ColorRainbow colorRainbow;

  /**
   * Test for the negative width constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegWidthConstructor() {
    colorRainbow = new ColorRainbow(-2, 3, 3, true);
  }

  /**
   * Test for negative height constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegHeightConstructor() {
    colorRainbow = new ColorRainbow(3, -2, 3, true);
  }

  /**
   * Test for zero height constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightConstructor() {
    colorRainbow = new ColorRainbow(4, 0, 3, true);
  }

  /**
   * Test for zero width constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthConstructor() {
    colorRainbow = new ColorRainbow(0, 3, 2, true);
  }

  /**
   * Test for negative stripe constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegStripe() {
    colorRainbow = new ColorRainbow(3, 2, -2, true);
  }

  /**
   * Test for zero stripe constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testZeroStripe() {
    colorRainbow = new ColorRainbow(3, 2, 0, true);
  }

}