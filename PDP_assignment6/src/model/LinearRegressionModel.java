package model;

import java.util.List;

import util.DisplayDataBag;

/**
 * Model solved the linear regression algorithm with least squares method.
 */
public class LinearRegressionModel implements MLModel {

  /**
   * given data in list form.
   */
  List<double[]> data;


  @Override
  public void initialize(List<double[]> data) {
    if (data == null || data.isEmpty()) {
      throw new IllegalArgumentException("Input data cannot be null or empty.");
    }
    this.data = data;
  }

  @Override
  public DisplayDataBag fit() {
    if (data == null) {
      throw new IllegalStateException("The data has not been initialized.");
    }
    double[] res = getExpectandS();
    double expectX = res[0];
    double expectY = res[1];
    double Sxx = res[2];
    double Syy = res[3];
    double Sxy = res[4];
    double t = getTThroughTheta(Sxx, Syy, Sxy);
    double a = Math.cos(t / 2);
    double b = Math.sin(t / 2);
    double c = -a * expectX - b * expectY;
    DisplayDataBag bag = new DisplayDataBag();
    bag.insertBag(new double[]{a, b, c});
    bag.insertBag(data);
    return bag;
  }

  /**
   * Get the data the algorithm needs, including expectation of x, y, and Sxx,Syy,Sxy.
   *
   * @return the double array contains all the parameters calculated
   */
  private double[] getExpectandS() {
    double expectX = 0;
    double expectY = 0;
    double Syy = 0;
    double Sxx = 0;
    double Sxy = 0;
    int n = data.size();
    for (double[] point : data) {
      expectX += point[0];
      expectY += point[1];
    }
    expectX /= n;
    expectY /= n;
    for (double[] point : data) {
      double Xdiff = point[0] - expectX;
      double Ydiff = point[1] - expectY;
      Syy += Ydiff * Ydiff;
      Sxx += Xdiff * Xdiff;
      Sxy += Xdiff * Ydiff;
    }
    return new double[]{expectX, expectY, Sxx, Syy, Sxy};
  }

  /**
   * Get t throw calculating theta first.
   *
   * @param Sxx given Sxx
   * @param Syy given Syy
   * @param Sxy given Sxy
   * @return the double value t
   */
  private double getTThroughTheta(double Sxx, double Syy, double Sxy) {
    double d = 2 * Sxy / (Sxx - Syy);
    double theta = Math.atan(d);
    double t;
    if (getF(Sxx, Syy, Sxy, theta) > 0) {
      t = theta;
    } else {
      t = theta + Math.PI;
    }
    return t;
  }

  /**
   * Helper function for getting F.
   *
   * @param Sxx given Sxx
   * @param Syy given Syy
   * @param Sxy given Sxy
   * @param t   given t
   * @return the double value of F
   */
  private double getF(double Sxx, double Syy, double Sxy, double t) {
    return (Syy - Sxx) * Math.cos(t) - 2 * Sxy * Math.sin(t);
  }
}
