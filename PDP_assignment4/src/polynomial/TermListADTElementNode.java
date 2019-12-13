package polynomial;

/**
 * Class used for normal Term List.
 */
public class TermListADTElementNode implements TermListADTNode {

  /**
   * Term data.
   */
  private Term term;

  /**
   * Rest term data.
   */
  private TermListADTNode rest;

  /**
   * Construction method.
   *
   * @param term given data
   * @param rest given next link
   */
  public TermListADTElementNode(Term term, TermListADTNode rest) {
    this.term = term;
    this.rest = rest;
  }

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
   * Add the given Term to the right position in descend order of this list and return this modified
   * list.
   *
   * @param term the Term to be added
   * @return the head of the resulting list
   */
  @Override
  public TermListADTNode addTerm(Term term) {
    if (this.term.getPower() > term.getPower()) {
      this.rest = this.rest.addTerm(term);
    } else if (this.term.getPower() < term.getPower()) {
      return addFront(term);
    } else {
      this.term.setCoefficient(term.getCoefficient() + this.term.getCoefficient());
      if (this.term.getCoefficient() == 0) {
        return this.rest;
      }
    }
    return this;
  }

  /**
   * Get current term.
   *
   * @return current term
   */
  @Override
  public Term getTerm() {
    return term;
  }

  /**
   * Get coefficient with given power.
   *
   * @param power the given power
   * @return coefficient
   */
  @Override
  public int getCoefficient(int power) {
    if (term.getPower() > power) {
      return rest.getCoefficient(power);
    } else if (term.getPower() < power) {
      return 0;
    } else {
      return term.getCoefficient();
    }
  }

  /**
   * Get rest list.
   *
   * @return rest list
   */
  @Override
  public TermListADTNode gerRest() {
    return rest;
  }

  /**
   * Get the evaluate number of current term.
   *
   * @param number the x number
   * @return double value of current term
   */
  @Override
  public double evaluate(double number) {
    return (double) term.getCoefficient() * Math.pow(number, term.getPower()) +
            rest.evaluate(number);
  }

  /**
   * Add the value of two list.
   *
   * @param termListADTNode given list
   * @return result after adding
   */
  @Override
  public TermListADTNode add(TermListADTNode termListADTNode) {
    Term other = termListADTNode.getTerm();
    Term newTerm;
    if (term.getPower() > other.getPower()) {
      newTerm = new Term(term.getCoefficient(), term.getPower());
      return new TermListADTElementNode(newTerm, rest.add(termListADTNode));
    } else if (term.getPower() < other.getPower()) {
      newTerm = new Term(other.getCoefficient(), other.getPower());
      return new TermListADTElementNode(newTerm, add(termListADTNode.gerRest()));
    } else {
      newTerm = new Term(term.getCoefficient() + other.getCoefficient(), term.getPower());
      if (newTerm.getCoefficient() == 0) {
        return rest.add(termListADTNode.gerRest());
      }
      return new TermListADTElementNode(newTerm, rest.add(termListADTNode.gerRest()));
    }
  }


  /**
   * Get the derivative number.
   *
   * @return the derivative number
   */
  @Override
  public TermListADTNode derivative() {
    Term newTerm;
    if (term.getPower() == 0) {
      return new TermListADTEmptyNode();
    } else {
      newTerm = new Term(term.getCoefficient() * term.getPower(),
              term.getPower() - 1);
    }
    return new TermListADTElementNode(newTerm, rest.derivative());
  }

  /**
   * Whether two list is same since current node.
   *
   * @param termListADTNode given list
   * @return whether they are equivalent
   */
  @Override
  public boolean isSame(TermListADTNode termListADTNode) {
    return term.equals(termListADTNode.getTerm()) && rest.isSame(termListADTNode.gerRest());
  }

  /**
   * Convert to string.
   *
   * @return string format
   */
  @Override
  public String toString() {
    return term.toString() + rest.toString();
  }
}
