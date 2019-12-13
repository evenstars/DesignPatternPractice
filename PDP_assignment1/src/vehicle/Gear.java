package vehicle;

/**
 * Used for representing the speed range of gears.The range is between [low,high], inclusively.
 */
public class Gear {
  /**
   * The lower bound of the range.
   */
  Integer lowSpeed;

  /**
   * the upper bound of the range.
   */
  Integer highSpeed;

  /**
   * Initial the range of the speed of the gear.
   *
   * @param lowSpeed  Lower bound.
   * @param highSpeed Upper bound.
   */
  public Gear(Integer lowSpeed, Integer highSpeed) {
    this.lowSpeed = lowSpeed;
    this.highSpeed = highSpeed;
  }
}
