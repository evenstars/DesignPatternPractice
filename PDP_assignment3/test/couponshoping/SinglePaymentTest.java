package couponshoping;


import couponshoping.couponstrategy.NonCoupon;
import couponshoping.couponstrategy.PercentOff;
import couponshoping.couponstrategy.AmountOff;
import couponshoping.couponstrategy.ItemOff;
import couponshoping.products.Napkin;
import couponshoping.products.Product;

import static org.junit.Assert.assertEquals;

/**
 * This test class is used to testing payment entrance class SinglePayment, with different coupon
 * strategy.
 */
public class SinglePaymentTest {

  /**
   * The test class SinglePayment.
   */
  private SinglePayment singlePayment;

  /**
   * Test for add amount off coupon to non coupon.
   */
  @org.junit.Test
  public void testAddAmountOffCoupon() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon amountOff = new AmountOff(3.0, napkin);
    singlePayment = new SinglePayment(napkin);
    AmountOff curConpon = (AmountOff) singlePayment.addCoupon(amountOff);
    assertEquals(3.0, curConpon.getAmountOff(), 0.01);
    assertEquals(20, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for add multuple amount off coupon together.
   */
  @org.junit.Test
  public void testAddMulipleAmountOffCoupon() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon amountOff1 = new AmountOff(1.0, napkin);
    Coupon amountOff2 = new AmountOff(2.0, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(amountOff1);
    AmountOff curConpon = (AmountOff) singlePayment.addCoupon(amountOff2);
    assertEquals(3.0, curConpon.getAmountOff(), 0.01);
    assertEquals(20, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for setting the amount off more than price.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testAmountOffMoreThanPrice() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon amountOff1 = new AmountOff(6.0, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(amountOff1);
  }

  /**
   * Test for setting the amount off lower or equal than zero.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testAmountOffLowerOrEqualThanZero() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon amountOff1 = new AmountOff(-6.0, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(amountOff1);
  }

  /**
   * Test for adding percent off coupon on non coupon.
   */
  @org.junit.Test
  public void testPercentOffCoupon() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon percentOff = new PercentOff(0.5, napkin);
    singlePayment = new SinglePayment(napkin);
    PercentOff curConpon = (PercentOff) singlePayment.addCoupon(percentOff);
    assertEquals(0.5, curConpon.getPercentOff(), 0.01);
    assertEquals(25, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for adding multiple percent off coupon together.
   */
  @org.junit.Test
  public void testMultiplePercentOffCoupon() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon percentOff1 = new PercentOff(0.5, napkin);
    Coupon percentOff2 = new PercentOff(0.3, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(percentOff1);
    PercentOff curConpon = (PercentOff) singlePayment.addCoupon(percentOff2);
    assertEquals(0.8, curConpon.getPercentOff(), 0.01);
    assertEquals(10, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for setting the percent off value lower than zero.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testPercentOffBelowZero() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon percentOff = new PercentOff(-1.0, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(percentOff);
  }

  /**
   * Test for setting the percent iff value more than one.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testPercentOffMoreThanOne() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon percentOff = new PercentOff(2.0, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(percentOff);
  }

  /**
   * Test for adding item off coupon to non coupon.
   */
  @org.junit.Test
  public void testItemOffCoupon() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon itemOff = new ItemOff(5, 3, napkin);
    singlePayment = new SinglePayment(napkin);
    ItemOff curCoupon = (ItemOff) singlePayment.addCoupon(itemOff);
    assertEquals(5, (int) curCoupon.getBasePurchase());
    assertEquals(3, (int) curCoupon.getFreeItems());
    assertEquals(35, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for adding multiple item off coupon with different ratio.
   */
  @org.junit.Test
  public void testMultipleItemOffNotSameRatio() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon itemOff1 = new ItemOff(5, 3, napkin);
    Coupon itemOff2 = new ItemOff(6, 2, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(itemOff1);
    ItemOff curCoupon = (ItemOff) singlePayment.addCoupon(itemOff2);
    assertEquals(5, (int) curCoupon.getBasePurchase());
    assertEquals(3, (int) curCoupon.getFreeItems());
    assertEquals(35, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for adding multiple item off coupon with same ratio.
   */
  @org.junit.Test
  public void testMultipleItemOffSameRatio() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon itemOff1 = new ItemOff(3, 1, napkin);
    Coupon itemOff2 = new ItemOff(6, 2, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(itemOff1);
    ItemOff curCoupon = (ItemOff) singlePayment.addCoupon(itemOff2);
    assertEquals(3, (int) curCoupon.getBasePurchase());
    assertEquals(1, (int) curCoupon.getFreeItems());
    assertEquals(45, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for Adding item off coupon with more free item than the basic purchase item.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testItemOffFreeMoreThanBase() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon itemOff = new ItemOff(5, 8, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(itemOff);
  }

  /**
   * Test for non coupon.
   */
  @org.junit.Test
  public void testNonCoupon() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon nonCoupon = new NonCoupon(napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(nonCoupon);
    assertEquals(50, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for multiple non coupon used together.
   */
  @org.junit.Test
  public void testMultipleNonCoupon() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon nonCoupon1 = new NonCoupon(napkin);
    Coupon nonCoupon2 = new NonCoupon(napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(nonCoupon1);
    singlePayment.addCoupon(nonCoupon2);
    assertEquals(50, singlePayment.getPrice(), 0.01);
  }

  /**
   * Test for adding amount off coupon on percent off coupon.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testAmountAndPercent() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon amountOff = new AmountOff(2.0, napkin);
    Coupon percentOff = new PercentOff(0.5, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(amountOff);
    singlePayment.addCoupon(percentOff);
  }

  /**
   * Test for adding amount off coupon on the item off coupon.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testAmountAndItem() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon amountOff = new AmountOff(2.0, napkin);
    Coupon itemOff = new ItemOff(5, 3, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(amountOff);
    singlePayment.addCoupon(itemOff);
  }

  /**
   * Test for adding item off coupon on percent off coupon.
   */
  @org.junit.Test(expected = IllegalArgumentException.class)
  public void testPercentAndItem() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon percentOff = new PercentOff(0.5, napkin);
    Coupon itemOff = new ItemOff(5, 3, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(percentOff);
    singlePayment.addCoupon(itemOff);
  }

  /**
   * Test for renew product and coupon.
   */
  @org.junit.Test
  public void exchangeProduct() {
    Product napkin = new Napkin("napkin", 10, 5.0);
    Coupon percentOff = new PercentOff(0.5, napkin);
    singlePayment = new SinglePayment(napkin);
    singlePayment.addCoupon(percentOff);
    singlePayment.exchangeProduct(new Napkin("napkin", 4, 2.0));
    assertEquals(4, (int) singlePayment.getProduct().getQuantity());
    assertEquals(2, singlePayment.getProduct().getPrice(), 0.01);
  }
}