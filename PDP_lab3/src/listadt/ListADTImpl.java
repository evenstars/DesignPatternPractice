package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements the listadt.ListADT
 * interface
 */
public class ListADTImpl<T> implements ListADT<T> {
  private GenericListADTNode<T> head;

  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  //a private constructor that is used internally (see map)
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  @Override
  public int getSize() {
    return head.count();
  }

  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }

  }

  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ListADTImpl(head.map(converter));
  }

  /**
   * Reverse the current list.
   */
  @Override
  public void reverse() {
    head = head.reverse(new GenericEmptyNode<>());
  }

  /**
   * This method changes this list to be a list that contains elements from the original list at
   * even positions, followed by odd positions. For example,if we have a list of numbers
   * {1,2,3,4,5,6,7} then the result should be {1,3,5,7,2,4,6}.
   */
  @Override
  public void interleave() {
    head = head.interleave();
  }


  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}