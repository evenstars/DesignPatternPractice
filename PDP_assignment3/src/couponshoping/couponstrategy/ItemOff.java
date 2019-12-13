package couponshoping.couponstrategy;

import couponshoping.Coupon;
import couponshoping.products.Product;

/**
 * The class is used for items off way of coupon.
 */
public class ItemOff extends Coupon {

  /**
   * The basic quantity of purchasing to enjoy the free items.
   */
  private Integer basePurchase;

  /**
   * The number of free items.
   */
  private Integer freeItems;

  /**
   * Construction for initializing the class. The basePurchase cannot below to the freeItems.
   *
   * @param basePurchase the basic purchase to enjoy the free items
   * @param freeItems    the free items
   * @param product      the given product
   */
  public ItemOff(Integer basePurchase, Integer freeItems, Product product) {
    super(product);
    if (freeItems >= basePurchase) {
      throw new IllegalArgumentException("the free items cannot be "
              + "more than the product to be bought.");
    }
    this.basePurchase = basePurchase;
    this.freeItems = freeItems;
  }

  /**
   * This function is used for getting the price of one product after using coupon. Customers could
   * choose not to use any coupon.
   */
  @Override
  public Double getOverallPrice() {
    Product product = super.getProduct();
    if (product.getQuantity() < basePurchase) {
      return product.getQuantity() * product.getPrice();
    } else {
      Double overAllPrice;
      overAllPrice = product.getPrice() * basePurchase;
      int remain = product.getQuantity() - basePurchase - freeItems;
      if (remain > 0) {
        overAllPrice += product.getPrice() * remain;
      }
      return overAllPrice;
    }
  }

  /**
   * Judge whether the obj and this belong the same type of coupon.
   *
   * @param obj given coupon
   * @return boolean value representing whether the two coupon belong the same coupon.
   */
  @Override
  public boolean isSameCoupon(Object obj) {
    return obj instanceof ItemOff;
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
    if (!(this.isSameCoupon(coupon))) {
      throw new IllegalArgumentException("Cannot stack different coupon together.");
    }
    ItemOff other = (ItemOff) coupon;
    int ratioCompareResult = freeItems * other.getBasePurchase()
            - other.getFreeItems() * basePurchase;
    if (ratioCompareResult > 0) {
      return this;
    } else if (ratioCompareResult < 0) {
      return coupon;
    } else {
      return basePurchase <= other.getBasePurchase() ? this : coupon;
    }
  }

  /**
   * Get the basePruchase.
   *
   * @return the basePurchase
   */
  public Integer getBasePurchase() {
    return basePurchase;
  }

  /**
   * Get the free items.
   *
   * @return the free items
   */
  public Integer getFreeItems() {
    return freeItems;
  }
}
