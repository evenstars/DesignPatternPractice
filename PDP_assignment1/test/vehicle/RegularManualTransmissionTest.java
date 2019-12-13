package vehicle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the RegularManualTransmission class.
 */
public class RegularManualTransmissionTest {

  /**
   * Test target object.
   */
  ManualTransmission manualTransmission;

  /**
   * Test for the construction function when the input ranges are proper.
   */
  @Test
  public void testNormalInitialization() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    assertEquals(0, manualTransmission.getSpeed());
    assertEquals(1, manualTransmission.getGear());
  }

  /**
   * Test function for construction function when start speed is zero. Expected the
   * IllegalArgumentException class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNotSpeedStartFromZero() {
    manualTransmission = new RegularManualTransmission(3, 5, 4, 10, 8, 15, 14, 20, 18, 25);
  }

  /**
   * Test function for construction function when higher speed is below the slower speed in one
   * single gear.. Expected the IllegalArgumentException class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGearHighBoundSmallThanLowBound() {
    manualTransmission = new RegularManualTransmission(0, 5, 7, 3, 8, 15, 14, 20, 18, 25);
  }

  /**
   * Test function for construction function when the upper gear has equivalent or lower start
   * speed. Expected the IllegalArgumentException class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLowerBoundIsNotIncrease() {
    manualTransmission = new RegularManualTransmission(0, 5, 3, 9, 2, 15, 14, 20, 18, 25);
  }

  /**
   * Test function for construction function when there are ranges belongs no gear range. Expected
   * the IllegalArgumentException class.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRangeIsNonOverlapping() {
    manualTransmission = new RegularManualTransmission(0, 5, 7, 10, 8, 15, 14, 20, 18, 25);
  }

  /**
   * Test function for getStatus function.
   */
  @Test
  public void getStatus() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    assertEquals("OK: everything is OK.", manualTransmission.getStatus());
  }

  /**
   * Test function for getSpeed function.
   */
  @Test
  public void getSpeed() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    assertEquals(0, manualTransmission.getSpeed());
  }

  /**
   * Test function for getGear function.
   */
  @Test
  public void getGear() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    assertEquals(1, manualTransmission.getGear());
  }

  /**
   * Test function for increaseSpeed.
   */
  @Test
  public void increaseSpeed() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    manualTransmission.increaseSpeed();
    assertEquals(1, manualTransmission.getSpeed());
  }

  /**
   * Test function for increasedSpeed when current speed is lower than the range of next gear.
   */
  @Test
  public void testSpeedWithoutEnterNextGearRange() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    manualTransmission.increaseSpeed();
    assertEquals(1, manualTransmission.getSpeed());
    assertEquals("OK: everything is OK.", manualTransmission.getStatus());
  }

  /**
   * Test function for increasedSpeed when current speed is int the range of next gear.
   */
  @Test
  public void testSpeedEnterNextGearRange() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    for (int i = 0; i < 5; i++) {
      manualTransmission.increaseSpeed();
    }
    assertEquals(5, manualTransmission.getSpeed());
    assertEquals("OK: you may increase the gear.", manualTransmission.getStatus());
  }

  /**
   * Test function for increasedSpeed when current speed is exceed the current maximum speed of
   * current gear.
   */
  @Test
  public void testSpeedExceedGearRange() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    for (int i = 0; i < 6; i++) {
      manualTransmission.increaseSpeed();
    }
    assertEquals(5, manualTransmission.getSpeed());
    assertEquals("Cannot increase speed, increase gear first.", manualTransmission.getStatus());
  }

  /**
   * Test function for increasedSpeed when current speed is the maximum speed the vehicle can be.
   */
  @Test
  public void testIncreaseSpeedWhenReachMax() {
    manualTransmission = new RegularManualTransmission(0, 1, 1, 2, 2, 3, 3, 4, 4, 5);
    manualTransmission.increaseSpeed();
    manualTransmission.increaseGear();
    manualTransmission.increaseSpeed();
    manualTransmission.increaseGear();
    manualTransmission.increaseSpeed();
    manualTransmission.increaseGear();
    manualTransmission.increaseSpeed();
    manualTransmission.increaseGear();
    manualTransmission.increaseSpeed();
    manualTransmission.increaseSpeed();
    assertEquals(5, manualTransmission.getSpeed());
    assertEquals("Cannot increase speed. Reached maximum speed.", manualTransmission.getStatus());
  }

  /**
   * Test function for decreaseSpeed.
   */
  @Test
  public void decreaseSpeed() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    manualTransmission.increaseSpeed();
    manualTransmission.decreaseSpeed();
    assertEquals(0, manualTransmission.getSpeed());
  }

  /**
   * Test function for DecreasedSpeed when current speed is higher than the range of last gear or
   * 0.
   */
  @Test
  public void testSpeedWithoutEnterLastGearRange() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    manualTransmission.increaseSpeed();
    manualTransmission.decreaseSpeed();
    assertEquals(0, manualTransmission.getSpeed());
    assertEquals("OK: everything is OK.", manualTransmission.getStatus());
  }

  /**
   * Test function for DecreasedSpeed when current speed is int the range of last gear.
   */
  @Test
  public void testSpeedEnterLastGearRange() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    for (int i = 0; i < 5; i++) {
      manualTransmission.increaseSpeed();
    }
    manualTransmission.increaseGear();
    manualTransmission.decreaseSpeed();
    assertEquals(4, manualTransmission.getSpeed());
    assertEquals("OK: you may decrease the gear.", manualTransmission.getStatus());
  }

  /**
   * Test function for DecreasedSpeed when current speed is equals to the current minimum speed of
   * current gear.
   */
  @Test
  public void testSpeedLowerGearRange() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    for (int i = 0; i < 4; i++) {
      manualTransmission.increaseSpeed();
    }
    manualTransmission.increaseGear();
    manualTransmission.decreaseSpeed();
    assertEquals(4, manualTransmission.getSpeed());
    assertEquals("Cannot decrease speed, decrease gear first.", manualTransmission.getStatus());
  }

  /**
   * Test function for decreasedSpeed when current speed is the minimum speed the vehicle can be.
   */
  @Test
  public void testDecreaseSpeedWhenReachMin() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    manualTransmission.decreaseSpeed();
    assertEquals(0, manualTransmission.getSpeed());
    assertEquals("Cannot decrease speed. Reached minimum speed.", manualTransmission.getStatus());
  }

  /**
   * Test function is for increaseGear.
   */
  @Test
  public void increaseGear() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    for (int i = 0; i < 5; i++) {
      manualTransmission.increaseSpeed();
    }
    manualTransmission.increaseGear();
    assertEquals(2, manualTransmission.getGear());
    assertEquals("OK: everything is OK.", manualTransmission.getStatus());
  }


  /**
   * This function is for IncreaseGear when the current speed is lower than the range of next gear.
   */
  @Test
  public void testIncreaseGearWithoutEnoughSpeed() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    manualTransmission.increaseGear();
    assertEquals(1, manualTransmission.getGear());
    assertEquals("Cannot increase gear, increase speed first.", manualTransmission.getStatus());
  }

  /**
   * This function is for IncreaseGear when the current gear reaches the max gear number.
   */
  @Test
  public void testIncreaseGearWithMaxGearNum() {
    manualTransmission = new RegularManualTransmission(0, 1, 1, 2, 2, 3, 3, 4, 4, 5);
    manualTransmission.increaseSpeed();
    manualTransmission.increaseGear();
    manualTransmission.increaseSpeed();
    manualTransmission.increaseGear();
    manualTransmission.increaseSpeed();
    manualTransmission.increaseGear();
    manualTransmission.increaseSpeed();
    manualTransmission.increaseGear();
    manualTransmission.increaseGear();
    assertEquals(5, manualTransmission.getGear());
    assertEquals("Cannot increase gear. Reached maximum gear.", manualTransmission.getStatus());
  }

  /**
   * This function is for decreaseGear.
   */
  @Test
  public void decreaseGear() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    for (int i = 0; i < 5; i++) {
      manualTransmission.increaseSpeed();
    }
    manualTransmission.increaseGear();
    manualTransmission.decreaseGear();
    assertEquals(1, manualTransmission.getGear());
    assertEquals("OK: everything is OK.", manualTransmission.getStatus());
  }

  /**
   * This function is for decreaseGear when the current speed is higher than the range of last
   * gear.
   */
  @Test
  public void testDecreaseGearWithoutEnoughSpeed() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    for (int i = 0; i < 5; i++) {
      manualTransmission.increaseSpeed();
    }
    manualTransmission.increaseGear();
    manualTransmission.increaseSpeed();
    manualTransmission.decreaseGear();
    assertEquals(2, manualTransmission.getGear());
    assertEquals("Cannot decrease gear, decrease speed first.", manualTransmission.getStatus());
  }

  /**
   * This function is for decreaseGear when the current gear reaches the min gear num, which is 0.
   */
  @Test
  public void testDecreaseGearWithMinGearNum() {
    manualTransmission = new RegularManualTransmission(0, 5, 4, 10, 8, 15, 14, 20, 18, 25);
    manualTransmission.decreaseGear();
    assertEquals(1, manualTransmission.getGear());
    assertEquals("Cannot decrease gear. Reached minimum gear.", manualTransmission.getStatus());
  }
}