package model;

import java.util.List;

import util.DisplayDataBag;

/**
 * Interface specified the action of the model here, these are receiving data and fit data with some
 * algorithms.
 */
public interface MLModel {

  /**
   * Receiving data. The data cannot be null or empty.
   *
   * @param data given data
   */
  void initialize(List<double[]> data);

  /**
   * Fit the data with given algorithm.
   *
   * @return the bag contains the data and learned parameters.
   */
  DisplayDataBag fit();
}
