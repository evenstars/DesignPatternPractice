package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import util.DisplayDataBag;

import static org.junit.Assert.assertEquals;

/**
 * Test for linear regression model.
 */
public class LinearRegressionModelTest {

  /**
   * Test object.
   */
  private MLModel mlModel;

  /**
   * Test for fit function without initialization first.
   */
  @Test(expected = IllegalStateException.class)
  public void testLRModelWithoutInitial() {
    mlModel = new LinearRegressionModel();
    mlModel.fit();
  }

  /**
   * Test for Initialization with null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLRModelInitializeWithNull() {
    mlModel = new LinearRegressionModel();
    mlModel.initialize(null);
  }

  /**
   * Test for Initialization with Empty argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLRModelInitializeWithEmpty() {
    mlModel = new LinearRegressionModel();
    mlModel.initialize(new ArrayList<>());
  }

  /**
   * Test for fitting data.
   */
  @Test
  public void testLRModelFit() {
    mlModel = new LinearRegressionModel();
    List<double[]> points = new ArrayList<>();
    points.add(new double[]{0, 0});
    points.add(new double[]{1, 1});
    points.add(new double[]{2, 2});
    mlModel.initialize(points);
    DisplayDataBag displayDataBag = mlModel.fit();
    List res = displayDataBag.getBag();
    double[] parms = (double[]) res.get(0);
    assertEquals(-0.707, parms[0], 0.01);
    assertEquals(0.707, parms[1], 0.01);
    assertEquals(0, parms[2], 0.01);
  }
}