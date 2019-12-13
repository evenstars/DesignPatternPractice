package model.process;

/**
 * This class implements Filter interface and offer a way to get a blur filter kernel.
 */
public class BlurFilter implements Filter {
  protected double oneSixteen = (double) 1 / 16;
  protected double oneEight = (double) 1 / 8;
  protected double oneFour = (double) 1 / 4;
  private double[][] blurFilter;

  /**
   * Default constructor that initialize a blur filter kernel.
   */
  public BlurFilter() {
    blurFilter = new double[3][3];
    blurFilter[0][0] = oneSixteen;
    blurFilter[0][1] = oneEight;
    blurFilter[0][2] = oneSixteen;
    blurFilter[1][0] = oneEight;
    blurFilter[1][1] = oneFour;
    blurFilter[1][2] = oneEight;
    blurFilter[2][0] = oneSixteen;
    blurFilter[2][1] = oneEight;
    blurFilter[2][2] = oneSixteen;
  }

  @Override
  public double[][] getFilter() {
    return blurFilter;
  }
}
