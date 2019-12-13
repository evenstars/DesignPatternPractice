import org.junit.Test;

import model.generate.SwitzerlandFlag;


/**
 * Test flag classes with Switzerland flag as example.
 */
public class SwitzerlandFlagTest {

  /**
   * Test for the proportion between the width and height is incorrect.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testErrorPortion() {
    SwitzerlandFlag switzerlandFlag = new SwitzerlandFlag(5, 3);
  }

}