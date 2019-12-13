package listadt;

import java.util.function.Function;

/**
 * This represents an empty node of the generic list implementation.
 */
public class GenericEmptyNode<T> implements GenericListADTNode<T> {
  @Override
  public int count() {
    return 0;
  }

  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode(object, this);
  }

  @Override
  public GenericListADTNode<T> addBack(T object) {
    return addFront(object);
  }

  @Override
  public GenericListADTNode<T> add(int index, T object) throws
          IllegalArgumentException {
    if (index == 0) {
      return addFront(object);
    }
    throw new IllegalArgumentException("Invalid index to add an element");
  }

  @Override
  public GenericListADTNode<T> remove(T object) {
    return this; //cannot remove from nothing!
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    throw new IllegalArgumentException("Wrong index");
  }

  @Override
  public <R> GenericListADTNode<R> map(Function<T, R> converter) {
    return new GenericEmptyNode();
  }

  /**
   * Reverse list one by one.
   *
   * @param last the last node in original list
   * @return reversed list head
   */
  @Override
  public GenericListADTNode<T> reverse(GenericListADTNode<T> last) {
    return last;
  }

  /**
   * This method changes this list to be a list that contains elements from the original list at
   * even positions, followed by odd positions. For example,if we have a list of numbers
   * {1,2,3,4,5,6,7} then the result should be {1,3,5,7,2,4,6}.
   */
  @Override
  public GenericListADTNode<T> interleave() {
    return this;
  }

  /**
   * Collect items from each odd position.
   *
   * @param index current index
   * @return head of the collected list
   */
  @Override
  public GenericListADTNode<T> collectEven(int index) {
    return new GenericEmptyNode<>();
  }

  /**
   * Merge two list.
   *
   * @param back list at back
   * @return head of the merged list
   */
  @Override
  public GenericListADTNode<T> merge(GenericListADTNode<T> back) {
    return back;
  }

  @Override
  public String toString() {
    return "";
  }
}