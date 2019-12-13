package model.process;

/**
 * The GreyscaleTransformer class implements ColorTransformer interface to offer a way to
 * get the greyscale color transformer matrix.
 */
public class GreyscaleTransformer implements ColorTransformer {
  private double[][] colorTransformer;

  /**
   * Default constructor that initialize the greyscale color transformer matrix.
   */
  public GreyscaleTransformer() {
    colorTransformer = new double[3][3];
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (col == 0) {
          colorTransformer[row][col] = 0.2126;
        } else if (col == 1) {
          colorTransformer[row][col] = 0.7152;
        } else {
          colorTransformer[row][col] = 0.0722;
        }
      }
    }
  }

  @Override
  public double[][] getTransformer() {
    return colorTransformer;
  }
}
