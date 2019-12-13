package model.process;

/**
 * This Filter interface offers a way to get a certain filter kernel.
 */
public interface Filter {

  /**
   * The method helps to get a certain filter kernel.
   *
   * @return a certain filter kernel.
   */
  double[][] getFilter();
}
