package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for passing the data generated by model. The order and type don't matter.
 *
 * @param <T> given type
 */
public class DisplayDataBag<T> {

  /**
   * Bag used for storing data.
   */
  private List<T> bag;

  /**
   * Constructor method initializing the bag.
   */
  public DisplayDataBag() {
    bag = new ArrayList<>();
  }

  /**
   * Insert data to the bag.
   *
   * @param data the given data
   */
  public void insertBag(T data) {
    bag.add(data);
  }

  /**
   * Get the data of the bag in list form.
   *
   * @return list of given data
   */
  public List<T> getBag() {
    return new ArrayList<>(bag);
  }
}