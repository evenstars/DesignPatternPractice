package couponshoping;


import couponshoping.couponstrategy.NonCoupon;
import couponshoping.products.Product;

/**
 * This class is used for making payment with coupon. And each time of getting price can only used
 * for one single type of product. And this class could also be used as adding coupon together.
 */
public class SinglePayment {

  /**
   * The current coupon used for the product.
   */
  private Coupon coupon;

  /**
   * The current product.
   */
  private Product product;

  /**
   * Construction function, initializing the product and the coupon. The initialized coupon is non
   * coupon, which means no coupon added to the product.
   *
   * @param product given product
   */
  public SinglePayment(Product product) {
    exchangeProduct(product);
  }

  /**
   * Add coupon together. If there is no coupon, just substitute the coupon with the new one.
   *
   * @param newCoupon new coming coupon
   * @return new coupon after stacking
   */
  public Coupon addCoupon(Coupon newCoupon) {
    coupon = coupon.addCoupon(newCoupon);
    return coupon;
  }

  /**
   * Get the prices after using price of the given product.
   *
   * @return double type of price.
   */
  public Double getPrice() {
    return coupon.getOverallPrice();
  }

  /**
   * Exchange the product with a new one, and set the coupon to no coupon status.
   */
  public void exchangeProduct(Product product) {
    this.product = product;
    coupon = new NonCoupon(product);
  }

  /**
   * Get the current coupon.
   *
   * @return current coupon
   */
  public Coupon getCoupon() {
    return coupon;
  }

  /**
   * Get the current product.
   *
   * @return current product
   */
  public Product getProduct() {
    return product;
  }
}
