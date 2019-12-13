package polynomial;

/**
 * Class for empty node.
 */
public class TermListADTEmptyNode implements TermListADTNode {


  /**
   * Add a term to the front of this list.
   *
   * @param term the term to be added to the front of this list
   */
  @Override
  public TermListADTNode addFront(Term term) {
    return new TermListADTElementNode(term, this);
  }

  /**
   * Add the given Term to the right position in descend order of this list and return
   * this modified list.
   *
   * @param term the Term to be added
   * @return the head of the resulting list
   */
  @Override
  public TermListADTNode addTerm(Term term) {
    return addFront(term);
  }

  /**
   * Get current term.
   *
   * @return current term
   */
  @Override
  public Term getTerm() {
    return new Term(0, 0);
  }

  /**
   * Get coefficient with given power.
   *
   * @param power the given power
   * @return coefficient
   */
  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  /**
   * Get rest list.
   *
   * @return rest list
   */
  @Override
  public TermListADTNode gerRest() {
    return this;
  }


  /**
   * Get the evaluate number of current term.
   *
   * @param number the x number
   * @return double value of current term
   */
  @Override
  public double evaluate(double number) {
    return 0.0;
  }

  /**
   * Add the value of two list.
   *
   * @param termListADTNode given list
   * @return result after adding
   */
  @Override
  public TermListADTNode add(TermListADTNode termListADTNode) {
    return termListADTNode;
  }


  /**
   * Get the derivative number.
   *
   * @return the derivative number
   */
  @Override
  public TermListADTNode derivative() {
    return this;
  }

  /**
   * Whether the other one is also empty.
   *
   * @param termListADTNode given list
   * @return boolean type data
   */
  @Override
  public boolean isSame(TermListADTNode termListADTNode) {
    return termListADTNode instanceof TermListADTEmptyNode;
  }

  /**
   * Convert to empty.
   *
   * @return empty string
   */
  @Override
  public String toString() {
    return "";
  }
}
