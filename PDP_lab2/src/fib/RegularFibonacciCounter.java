package fib;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a implementation class for FibonarriCounter interface. Used for the specific
 * operation of Fibonacci series.
 */
public class RegularFibonacciCounter implements FibonacciCounter {

  /**
   * This list used for storing fibonacci values.
   */
  private List<Integer> fibonacci;

  /**
   * The current count of this Fibonacci series.
   */
  private Integer count;

  /**
   * Construction function, setting the List data fibonacci as ArrayList, and initial count is 1.
   */
  public RegularFibonacciCounter() {
    fibonacci = new ArrayList<>();
    count = 1;
  }

  /**
   * Get the fibonacci value regarding to the given count.
   *
   * @param count Count of the wanted value, equalling index+1.to
   * @return Corresponding fibonacci value.
   */
  private Integer getValue(int count) {
    if (count <= 0) {
      throw new IllegalArgumentException("The count is below 1.");
    }
    if (count > fibonacci.size()) {
      throw new IllegalArgumentException("The count is beyond upper bound.");
    }
    return fibonacci.get(count - 1);
  }

  /**
   * Increase the Fibonacci count by one. Count equals to index plus one,starting from one.
   *
   * @return One object implemented FibonacciCounter interface
   */
  @Override
  public FibonacciCounter incrementByOne() {
    if (count == 1) {
      fibonacci.add(0);
    } else if (count == 2) {
      fibonacci.add(1);
    } else if (count > fibonacci.size()) {
      fibonacci.add(getValue(count - 1) + getValue(count - 2));
    }
    count++;
    return this;
  }

  /**
   * Decrease the Fibonacci count by one. Count equals to index plus one,starting from one.
   *
   * @return One object implemented FibonacciCounter interface
   */
  @Override
  public FibonacciCounter decrementByOne() throws Exception {
    if (count == 1) {
      throw new Exception("Count is already minimum.");
    }
    count--;
    return this;
  }

  /**
   * Get the count of current object.
   *
   * @return Integer type, representing the Fibonacci class.
   */
  @Override
  public Integer getCount() {
    return count;
  }

  /**
   * Get the value according to the current count of this object.
   *
   * @return One Integer type value regarding to the current count
   */
  @Override
  public Integer getFibonacciNumber() {
    return getValue(count - 1);
  }
}
