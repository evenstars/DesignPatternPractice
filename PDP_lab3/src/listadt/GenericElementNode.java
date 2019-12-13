package listadt;

import java.util.function.Function;

/**
 * This is a non-empty node in a generic list. It contains the object data and the rest of the list
 */
public class GenericElementNode<T> implements GenericListADTNode<T> {
  private T object;
  private GenericListADTNode<T> rest;

  public GenericElementNode(T p, GenericListADTNode<T> rest) {
    this.object = p;
    this.rest = rest;
  }

  @Override
  public int count() {
    return 1 + this.rest.count();
  }


  @Override
  public GenericListADTNode<T> addFront(T object) {
    return new GenericElementNode(object, this);
  }

  @Override
  public GenericListADTNode<T> addBack(T object) {
    this.rest = this.rest.addBack(object);
    return this;
  }

  @Override
  public GenericListADTNode<T> add(int index, T object) {
    if (index == 0) {
      return addFront(object);
    } else {
      this.rest = this.rest.add(index - 1, object);
      return this;
    }
  }

  @Override
  public GenericListADTNode<T> remove(T object) {
    if (this.object.equals(object)) {
      return this.rest;
    } else {
      this.rest = this.rest.remove(object);
      return this;
    }
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if (index == 0) {
      return this.object;
    }
    return this.rest.get(index - 1);
  }

  @Override
  public <R> GenericListADTNode<R> map(Function<T, R> converter) {
    /* Starting from this list of T, the resulting list of type R is an
    element that contains this data converted to T, followed by the rest of
    the converted list
     */
    return new GenericElementNode(
            converter.apply(this.object),
            this.rest.map(converter));
  }

  /**
   * Reverse list one by one.
   *
   * @param last the last node in original list
   * @return reversed list head
   */
  @Override
  public GenericListADTNode<T> reverse(GenericListADTNode<T> last) {
    GenericListADTNode<T> next = rest;
    rest = last;
    return next.reverse(this);
  }

  /**
   * This method changes this list to be a list that contains elements from the original list at
   * even positions, followed by odd positions. For example,if we have a list of numbers
   * {1,2,3,4,5,6,7} then the result should be {1,3,5,7,2,4,6}.
   */
  @Override
  public GenericListADTNode<T> interleave() {
    GenericListADTNode<T> front = collectEven(1);
    GenericListADTNode<T> back = collectEven(0);
    front.merge(back);
    return front;
  }

  /**
   * Collect items from each odd position.
   *
   * @param index current index
   * @return head of the collected list
   */
  @Override
  public GenericListADTNode<T> collectEven(int index) {
    if ((index & 1) == 1) {
      GenericElementNode<T> res = new GenericElementNode<>(object, rest.collectEven(index + 1));
      return res;
    } else {
      return rest.collectEven(index + 1);
    }
  }

  /**
   * Merge two list.
   *
   * @param back list at back
   * @return head of the merged list
   */
  @Override
  public GenericListADTNode<T> merge(GenericListADTNode<T> back) {
    rest = rest.merge(back);
    return this;
  }

  @Override
  public String toString() {
    String objstring = this.object.toString();
    String rest = this.rest.toString();
    if (rest.length() > 0) {
      return objstring + " " + rest;
    } else {
      return objstring;
    }
  }
}