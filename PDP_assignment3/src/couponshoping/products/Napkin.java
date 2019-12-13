package couponshoping.products;

/**
 * One test product called napkin.
 */
public class Napkin extends Product {
  /**
   * Construction for the abstract class used for initialization of features.
   *
   * @param name     name of product
   * @param quantity quantity of product
   * @param price    price of product
   */
  public Napkin(String name, Integer quantity, Double price) {
    super(name, quantity, price);
  }
}
