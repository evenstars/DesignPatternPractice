package couponshoping;

import couponshoping.products.Product;

/**
 * This abstract class is used for representing all specific coupon, and storing product.
 */
public abstract class Coupon implements Charge {

  /**
   * Given product. A coupon can only be applied to one single product.
   */
  protected Product product;

  /**
   * Construction function. Initialize the product.
   *
   * @param product given product
   */
  public Coupon(Product product) {
    this.product = product;
  }

  /**
   * This function is used for stacking new coupon to the old coupon. If there is no coupon, which
   * is NonCoupon, we substitute the new coupon to original coupon. Otherwise, we stack them
   * together accordingly.
   *
   * @param coupon the new given coupon
   * @return the coupon after stacking the two given coupon
   */
  public abstract Coupon addCoupon(Coupon coupon);

  /**
   * Get the product.
   *
   * @return Stored product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * Judge whether the obj and this belong the same type of coupon.
   *
   * @param obj given coupon
   * @return boolean value representing whether the two coupon belong the same coupon.
   */
  public abstract boolean isSameCoupon(Object obj);
}
