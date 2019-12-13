package couponshoping.couponstrategy;

import couponshoping.Coupon;
import couponshoping.products.Product;

/**
 * The class is used for percent off way of coupon.
 */
public class PercentOff extends Coupon {

  /**
   * Percent off value.
   */
  private Double percentOff;

  /**
   * The construction function for this class, initialize the percent off value and product.
   *
   * @param percentOff given percent off value, which have to be in (0,1), exclusively
   * @param product    the given product
   */
  public PercentOff(Double percentOff, Product product) {
    super(product);
    if (percentOff <= 0 || percentOff >= 1) {
      throw new IllegalArgumentException("The percent off must ranges between (0,1), exclusively");
    }
    this.percentOff = percentOff;
  }

  /**
   * This function is used for getting the price of one product after using coupon. Customers could
   * choose not to use any coupon.
   */
  @Override
  public Double getOverallPrice() {
    Product product = super.getProduct();
    return product.getQuantity() * product.getPrice() * (1 - percentOff);
  }

  /**
   * Judge whether the obj and this belong the same type of coupon.
   *
   * @param obj given coupon
   * @return boolean value representing whether the two coupon belong the same coupon.
   */
  @Override
  public boolean isSameCoupon(Object obj) {
    return obj instanceof PercentOff;
  }

  /**
   * This function is used for stacking new coupon to the old coupon. If there is no coupon, which
   * is NonCoupon, we substitute the new coupon to original coupon. Otherwise, we stack them
   * together accordingly.
   *
   * @param coupon the new given coupon
   * @return the coupon after stacking the two given coupon
   */
  @Override
  public Coupon addCoupon(Coupon coupon) {
    if (!(coupon instanceof PercentOff)) {
      throw new IllegalArgumentException("Cannot stack different coupon together.");
    }
    PercentOff other = (PercentOff) coupon;
    if (other.getPercentOff() + percentOff >= 1) {
      throw new IllegalArgumentException("cannot percent off prices more than 1.");
    }
    return new PercentOff(other.getPercentOff() + percentOff, super.getProduct());
  }

  /**
   * Get the percent off value.
   *
   * @return the percent off value
   */
  public Double getPercentOff() {
    return percentOff;
  }
}
