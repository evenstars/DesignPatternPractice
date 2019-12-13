package fib;

/**
 * This interface epresents the operation of Fibonacci series.
 */
public interface FibonacciCounter {

  /**
   * Increase the Fibonacci count by one. Count equals to index plus one,starting from one.
   *
   * @return One object implemented FibonacciCounter interface
   */
  public FibonacciCounter incrementByOne();

  /**
   * Decrease the Fibonacci count by one. Count equals to index plus one,starting from one.
   *
   * @return One object implemented FibonacciCounter interface
   */
  public FibonacciCounter decrementByOne() throws Exception;

  /**
   * Get the count of current object.
   *
   * @return Integer type, representing the Fibonacci class.
   */
  public Integer getCount();

  /**
   * Get the value according to the current count of this object.
   *
   * @return One Integer type value regarding to the current count
   */
  public Integer getFibonacciNumber();
}
