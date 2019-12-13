package polynomial;

import java.util.Objects;
import java.util.Scanner;

/**
 * This class implement the Polynomial interface mainly using linked list structure.
 */
public class PolynomialImpl implements Polynomial {

  /**
   * Head of term list.
   */
  private TermListADTNode head;

  /**
   * Empty constructor. Coefficient and power are set to zero.NextTerm is null.
   */
  public PolynomialImpl() {
    head = new TermListADTEmptyNode();
  }

  /**
   * A constructor using a string as input. Parse the string and get the term linked list.
   *
   * @param serializedPolynomial give string whose form resembles:-3x^4 -2x^5 -5 +11x^1
   */
  public PolynomialImpl(String serializedPolynomial) {
    if (serializedPolynomial == null) {
      throw new IllegalArgumentException("Term string cannot be empty");
    }
    head = new TermListADTEmptyNode();
    Scanner scanner = new Scanner(serializedPolynomial).useDelimiter("\\s+");
    while (scanner.hasNext()) {
      String term = scanner.next();
      int[] coefficientAndPower = termParser(term);
      if (coefficientAndPower[0] == 0) {
        continue;
      }
      head = head.addTerm(new Term(coefficientAndPower[0], coefficientAndPower[1]));
    }
  }

  /**
   * Construct which take new list as input.
   *
   * @param termListADTNode new list
   */
  public PolynomialImpl(TermListADTNode termListADTNode) {
    head = termListADTNode;
  }

  /**
   * Utilize funtion used for parsing single term string like: -3x^4.
   *
   * @param term input single term string
   * @return a int array containing the parsed coefficient and power
   * @throws IllegalArgumentException if the input string is null or empty. throw it
   */
  private static int[] termParser(String term) {
    if (term == null || term.isEmpty()) {
      throw new IllegalArgumentException("empty term");
    }
    char[] termChars = term.toCharArray();
    int idx = 0;
    int length = termChars.length;
    int coefficient = 0;
    int power = 0;
    boolean isNegative = false;
    if (termChars[idx] == '+' || termChars[idx] == '-') {
      if (termChars[idx] == '-') {
        isNegative = true;
      }
      idx++;
    }
    //find x index
    int xIdx = idx;
    while (xIdx < length && termChars[xIdx] != 'x') {
      xIdx++;
    }
    //find coefficient
    if (xIdx == idx) {
      coefficient = 1;
    } else {
      coefficient = collectInteger(termChars, idx, xIdx);
    }
    //find power
    if (xIdx == length) {
      //no x
      return isNegative ? new int[]{-coefficient, 0} : new int[]{coefficient, 0};
    } else if (xIdx == length - 1) {
      //x is last character
      return isNegative ? new int[]{-coefficient, 1} : new int[]{coefficient, 1};
    } else {
      if (termChars[xIdx + 1] != '^') {
        throw new IllegalArgumentException("Invalid input.");
      }
      idx = xIdx + 2;
      if (idx >= length) {
        throw new IllegalArgumentException("Invalid input.");
      }
      power = collectInteger(termChars, idx, length);
    }
    return isNegative ? new int[]{-coefficient, power} : new int[]{coefficient, power};
  }

  private static int collectInteger(char[] cs, int idx, int end) {
    int val = 0;
    while (idx < end) {
      if (cs[idx] < '0' || cs[idx] > '9') {
        throw new IllegalArgumentException("Invalid input.");
      }
      val = val * 10 + (cs[idx] - '0');
      idx++;
    }
    return val;
  }

  /**
   * Add One term to this polynomial number.
   *
   * @param coefficient coefficient of the other polynomial.
   * @param power       non-negative power of the other polynomial.
   * @throws IllegalArgumentException if the term is invalid
   */
  @Override
  public void addTerm(Integer coefficient, Integer power) {
    if (power < 0) {
      throw new IllegalArgumentException("Power cannot be negative.");
    }
    if (coefficient == 0) {
      return;
    }
    head = head.addTerm(new Term(coefficient, power));
  }

  /**
   * Get the degree of the polynomial which is the power of the highest term.
   *
   * @return degree of the term with max power
   */
  @Override
  public int getDegree() {
    return head.getTerm().getPower();
  }

  /**
   * Return the coefficient with specified power after sorting.
   *
   * @param power specified power
   * @return the coefficient value with the power given
   * @throws IllegalArgumentException if no such power
   */
  @Override
  public int getCoefficient(Integer power) {
    return head.getCoefficient(power);
  }

  /**
   * Evaluate the polynomial with input x value.
   *
   * @param number input x value
   * @return double type evaluate value
   */
  @Override
  public double evaluate(Double number) {
    return head.evaluate(number);
  }

  /**
   * Add the given polynomial to this polynomial.
   *
   * @param polynomial given polynomial
   * @return the polynomial after adding
   */
  @Override
  public Polynomial add(Polynomial polynomial) {
    PolynomialImpl other = (PolynomialImpl) polynomial;
    return new PolynomialImpl(head.add(other.getHead()));
  }

  /**
   * Multiply the given polynomial to this polynomial.
   *
   * @param polynomial given polynomial
   * @return the polynomial after multiplying
   */
  @Override
  public Polynomial multiply(Polynomial polynomial) {
    PolynomialImpl other = (PolynomialImpl) polynomial;
    TermListADTNode result = new TermListADTEmptyNode();
    TermListADTNode curTerm = head;
    TermListADTNode otherTerm = other.getHead();
    while (!(curTerm instanceof TermListADTEmptyNode)) {
      TermListADTNode temp = otherTerm;
      while (!(temp instanceof TermListADTEmptyNode)) {
        result = result.addTerm(curTerm.getTerm().multiple(temp.getTerm()));
        temp = temp.gerRest();
      }
      curTerm = curTerm.gerRest();
    }
    return new PolynomialImpl(result);
  }

  /**
   * Derivative this polynomial.The derivative of a polynomial (from differential calculus) is a
   * polynomial made up of the derivative of each term. The derivative of a term 𝑎𝑥𝑏 is a term
   * with coefficient 𝑎𝑏 with the variable raised to the power 𝑏−1. The derivative of a constant
   * term (𝑥0) is 0. For example the derivative of 3𝑥4−5𝑥3+2𝑥−4 is 12𝑥3−15𝑥2+2.
   *
   * @return polynomial after derivative
   */
  @Override
  public Polynomial derivative() {
    return new PolynomialImpl(head.derivative());
  }

  /**
   * Determine whether the given PolynomialImpl object has same value with this object.
   *
   * @param polynomial given PolynomialImpl object
   * @return Boolean type data representing whether they have same value
   */
  @Override
  public boolean equals(Object polynomial) {
    if (this == polynomial) {
      return true;
    }
    if (polynomial == null) {
      return false;
    }
    if (getClass() != polynomial.getClass()) {
      return false;
    }
    PolynomialImpl other = (PolynomialImpl) polynomial;
    return head.isSame(other.getHead());
  }

  /**
   * Convert this object to a string, like 2x^5-3x^2+4x^1-10.
   *
   * @return String representing this object
   */
  public String toString() {
    String result = head.toString();
    if (result.isEmpty()) {
      return "0";
    }
    if (result.charAt(0) == '+') {
      result = result.substring(1, result.length());
    }
    return result;
  }

  /**
   * Hash method.
   *
   * @return hash value
   */
  @Override
  public int hashCode() {
    return Objects.hash(head);
  }

  /**
   * Return the firstTerm object.
   *
   * @return the firstTerm object
   */
  public TermListADTNode getHead() {
    return head;
  }
}
