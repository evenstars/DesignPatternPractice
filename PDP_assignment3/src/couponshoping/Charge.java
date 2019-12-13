package couponshoping;

/**
 * The interface is used for getting price.
 */
public interface Charge {

  /**
   * This function is used for getting the price of one product after using coupon. Customers could
   * choose not to use any coupon.
   */
  public Double getOverallPrice();
}
