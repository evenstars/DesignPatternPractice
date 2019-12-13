package model.generate;

/**
 * Interface for the rainbow or stripe classes.
 */
public interface Rainbow {

  /**
   * Get size of stripes.
   *
   * @return size of stripes, depending on the direction of the rainbow
   */
  int getStripeSize();

  /**
   * Get the direction whether it is horizontal.
   *
   * @return whether it is horizontal
   */
  boolean isHorizontal();

  /**
   * Set the direction to the rainbow.
   *
   * @param isHorizontal whether is horizontal
   */
  void setDirection(boolean isHorizontal);
}
