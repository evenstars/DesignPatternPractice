package polynomial;

import java.util.Objects;

/**
 * Term data class.
 */
public class Term {

  /**
   * Coefficient of the term.
   */
  private int coefficient;

  /**
   * Power of the term.
   */
  private int power;

  /**
   * Constructor for the term.
   *
   * @param coefficient coefficient
   * @param power       input power
   */
  public Term(int coefficient, int power) {
    this.coefficient = coefficient;
    this.power = power;
  }

  /**
   * Get the power.
   *
   * @return power
   */
  public int getPower() {
    return power;
  }

  /**
   * Get the coefficient.
   *
   * @return the coefficient
   */
  public int getCoefficient() {

    return coefficient;
  }

  /**
   * Whether two term equals.
   *
   * @param o given object
   * @return whether they are equivalent
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Term)) {
      return false;
    }
    Term term = (Term) o;
    return getCoefficient() == term.getCoefficient()
            && getPower() == term.getPower();
  }

  /**
   * Hash value.
   *
   * @return hash value
   */
  @Override
  public int hashCode() {
    return Objects.hash(getCoefficient(), getPower());
  }

  /**
   * Convert to string.
   *
   * @return string format
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (coefficient > 0) {
      sb.append('+');
    }
    sb.append(coefficient);
    if (power != 0) {
      sb.append("x^");
      sb.append(power);
    }
    return sb.toString();
  }

  /**
   * Set the coefficient.
   *
   * @param coefficient expected coefficient
   */
  public void setCoefficient(int coefficient) {
    this.coefficient = coefficient;
  }

  /**
   * Multiple two term.
   *
   * @param term given term
   * @return term after multiplication
   */
  public Term multiple(Term term) {
    return new Term(coefficient * term.getCoefficient(), power + term.getPower());
  }
}
