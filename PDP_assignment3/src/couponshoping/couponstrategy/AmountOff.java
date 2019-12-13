package couponshoping.couponstrategy;

import couponshoping.Coupon;
import couponshoping.products.Product;

/**
 * The class is used for amount off way of coupon.
 */
public class AmountOff extends Coupon {

  /**
   * The amount off value.
   */
  private Double amountOff;

  /**
   * Construction function for this class, initializing the amount off value and product.
   *
   * @param amountOff amount off value
   * @param product   specified product
   */
  public AmountOff(Double amountOff, Product product) {
    super(product);
    if (amountOff <= 0) {
      throw new IllegalArgumentException("The amount off cannot be lower than 0.");
    }
    if (amountOff >= product.getPrice()) {
      throw new IllegalArgumentException("the amount off cannot be more than the product price.");
    }
    this.amountOff = amountOff;
  }

  /**
   * This function is used for getting the price of one product after using coupon. Customers could
   * choose not to use any coupon.
   */
  @Override
  public Double getOverallPrice() {
    Product product = super.getProduct();
    return product.getQuantity() * (product.getPrice() - amountOff);
  }

  /**
   * Judge whether the obj and this belong the same type of coupon.
   *
   * @param obj given coupon
   * @return boolean value representing whether the two coupon belong the same coupon.
   */
  public boolean isSameCoupon(Object obj) {
    return obj instanceof AmountOff;
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
    if (!this.isSameCoupon(coupon)) {
      throw new IllegalArgumentException("Cannot stack different coupon together.");
    }
    AmountOff other = (AmountOff) coupon;
    return new AmountOff(this.amountOff + other.getAmountOff(), super.getProduct());
  }

  /**
   * Get the amount off value.
   *
   * @return the amount off value
   */
  public Double getAmountOff() {
    return amountOff;
  }
}
