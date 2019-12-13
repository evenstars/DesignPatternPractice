package model.process;

/**
 * This ColorTransformer interface offers a way to get a certain color transformer
 * matrix.
 */
public interface ColorTransformer {

  /**
   * The method helps to get a certain color transformer matrix.
   *
   * @return a certain color transformer matrix.
   */
  double[][] getTransformer();
}
