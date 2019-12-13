package view;

import java.util.List;

import util.DisplayDataBag;

/**
 * Interface for view, which could display result and input data.
 */
public interface MLView {

  /**
   * Display result.
   *
   * @param result result in bag, the order is specified by the writer of the model.
   */
  void display(DisplayDataBag result);

  /**
   * Input data.
   *
   * @return double[] type of point after parsing
   */
  List<double[]> input();
}
