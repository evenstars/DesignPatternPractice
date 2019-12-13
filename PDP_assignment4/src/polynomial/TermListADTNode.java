package polynomial;

/**
 * This interface specify the operation of list.
 */
public interface TermListADTNode {

  /**
   * Add a term to the front of this list.
   * @param term the term to be added to the front of this list
   */
  TermListADTNode addFront(Term term);

  /**
   * Add the given Term to the right position in descend order of this list and return
   * this modified list.
   * @param term the Term to be added
   * @return the head of the resulting list
   */
  TermListADTNode addTerm(Term term);

  /**
   * Get current term.
   * @return current term
   */
  Term getTerm();

  /**
   * Get coefficient with given power.
   * @param power the given power
   * @return coefficient
   */
  int getCoefficient(int power);

  /**
   * Get rest list.
   * @return rest list
   */
  TermListADTNode gerRest();

  /**
   * Get the evaluate number of current term.
   * @param number the x number
   * @return double value of current term
   */
  double evaluate(double number);

  /**
   * Add the value of two list.
   * @param termListADTNode given list
   * @return result after adding
   */
  TermListADTNode add(TermListADTNode termListADTNode);


  /**
   * Get the derivative number.
   * @return the derivative number
   */
  TermListADTNode derivative();

  /**
   * Get the string represent of the list.
   * @return string represent
   */
  String toString();

  /**
   * Determine whether two list is same.
   * @param termListADTNode given list
   * @return boolean type data whether two list is same
   */
  boolean isSame(TermListADTNode termListADTNode);
}
