package controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.LinearRegressionModel;
import model.MLModel;
import util.DisplayDataBag;
import view.LinearRegressionView;
import view.MLView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for controller.
 */
public class MLControllerImplTest {

  /**
   * Controller object.
   */
  private MLController mlController;

  /**
   * Test for constructor of controller when model is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testControllerModelNull() {
    mlController = new MLControllerImpl(new LinearRegressionView(), null);
  }

  /**
   * Test for constructor of controller when view is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testControllerViewNull() {
    mlController = new MLControllerImpl(null, new LinearRegressionModel());
  }

  /**
   * Test for constructor's function.
   */
  @Test
  public void testController() {
    MLView view = new MLView() {
      @Override
      public void display(DisplayDataBag result) {
        return;
      }

      @Override
      public List<double[]> input() {
        List<double[]> data = new ArrayList<>();
        data.add(new double[]{0, 0});
        return data;
      }
    };
    MLModel model = new MLModel() {
      @Override
      public void initialize(List<double[]> data) {
        if (data == null || data.isEmpty()) {
          fail("Data should not be empty or null here.");
        }
        assertEquals("0.0 0.0", data.get(0)[0] + " " + data.get(0)[1]);
      }

      @Override
      public DisplayDataBag fit() {
        return null;
      }
    };
    mlController = new MLControllerImpl(view, model);
    mlController.calculate();
  }
}