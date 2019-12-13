package couponshoping.couponstrategy;

import couponshoping.Coupon;
import couponshoping.products.Product;

/**
 * The class is used for no coupons.
 */
public class NonCoupon extends Coupon {

  /**
   * Construction function, just initialize the product.
   *
   * @param product given product
   */
  public NonCoupon(Product product) {
    super(product);
  }

  /**
   * This function is used for getting the price of one product after using coupon. Customers could
   * choose not to use any coupon.
   */
  @Override
  public Double getOverallPrice() {
    Product product = super.getProduct();
    return product.getPrice() * product.getQuantity();
  }

  /**
   * Judge whether the obj and this belong the same type of coupon.
   *
   * @param obj given coupon
   * @return boolean value representing whether the two coupon belong the same coupon.
   */
  @Override
  public boolean isSameCoupon(Object obj) {
    return obj instanceof NonCoupon;
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
    return coupon;
  }
}
