package util;


import java.util.List;

/**
 * Interface used for inputting point data.
 */
public interface InputUtil {

  /**
   * Parse given input stream by different way and get the points in double[] form in a list.
   *
   * @return generated points list
   */
  List<double[]> parse();
}
