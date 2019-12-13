package polynomial;

/**
 * This interface representing multiple operation of Polynomial including addTrem, getDegree, etc.
 */
public interface Polynomial {

  /**
   * Add One term to this polynomial number.
   *
   * @param coefficient coefficient of the other polynomial
   * @param power       non-negative power of the other polynomial
   * @throws IllegalArgumentException if the term is invalid
   */
  void addTerm(Integer coefficient, Integer power);

  /**
   * Get the degree of the polynomial which is the power of the highest term.
   *
   * @return degree of the term with max power
   */
  int getDegree();

  /**
   * Return the coefficient with specified power.
   *
   * @param power specified power
   * @return the coefficient value with the power given
   */
  int getCoefficient(Integer power);

  /**
   * Evaluate the polynomial with input x value.
   *
   * @param number input x value
   * @return double type evaluate value
   */
  double evaluate(Double number);

  /**
   * Add the given polynomial to this polynomial.
   *
   * @param polynomial1 given polynomial
   * @return the polynomial after adding
   */
  Polynomial add(Polynomial polynomial1);

  /**
   * Multiply the given polynomial to this polynomial.
   *
   * @param polynomial1 given polynomial
   * @return the polynomial after multiplying
   */
  Polynomial multiply(Polynomial polynomial1);

  /**
   * Derivative this polynomial.The derivative of a polynomial (from differential calculus) is a
   * polynomial made up of the derivative of each term. The derivative of a term 𝑎𝑥𝑏 is a term
   * with coefficient 𝑎𝑏 with the variable raised to the power 𝑏−1. The derivative of a constant
   * term (𝑥0) is 0. For example the derivative of 3𝑥4−5𝑥3+2𝑥−4 is 12𝑥3−15𝑥2+2.
   *
   * @return polynomial after derivative
   */
  Polynomial derivative();
}
