package couponshoping.products;

/**
 * This abstract product represents all the product and their common features.
 */
public abstract class Product {

  /**
   * The name of the product.
   */
  private String name;

  /**
   * The quantity of the product.
   */
  private Integer quantity;

  /**
   * The price of the product.
   */
  private Double price;

  /**
   * Construction for the abstract class used for initialization of features.
   *
   * @param name     name of product
   * @param quantity quantity of product
   * @param price    price of product
   */
  public Product(String name, Integer quantity, Double price) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Product name cannot be empty.");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Product quantity cannot below or equal 0.");
    }
    if (price <= 0) {
      throw new IllegalArgumentException("Product price cannot below or equal 0.0.");
    }
    this.name = name;
    this.quantity = quantity;
    this.price = price;
  }

  /**
   * Get name of the product.
   *
   * @return the name of the product
   */
  public String getName() {
    return name;
  }

  /**
   * Get the price of the product.
   *
   * @return the price of the product
   */
  public Double getPrice() {
    return price;
  }

  /**
   * Get quantity of the product.
   *
   * @return the quantity of the product
   */
  public Integer getQuantity() {
    return quantity;
  }
}
