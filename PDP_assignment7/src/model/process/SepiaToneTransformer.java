package model.process;

/**
 * The SepiaToneTransformer class implements ColorTransformer interface to offer a way to
 * get the sepia tone color transformer matrix.
 */
public class SepiaToneTransformer implements ColorTransformer {
  private double[][] colorTransformer;

  /**
   * Default constructor that initialize the sepia tone color transformer matrix.
   */
  public SepiaToneTransformer() {
    double[] arrayBufF = {0.393, 0.769, 0.189};
    double[] arrayBufS = {0.349, 0.686, 0.168};
    double[] arrayBufT = {0.272, 0.534, 0.131};
    colorTransformer = new double[3][3];
    colorTransformer[0] = arrayBufF;
    colorTransformer[1] = arrayBufS;
    colorTransformer[2] = arrayBufT;
  }

  @Override
  public double[][] getTransformer() {
    return colorTransformer;
  }

}
