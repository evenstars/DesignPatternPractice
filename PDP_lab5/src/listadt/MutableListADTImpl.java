package listadt;

import java.util.function.Function;

/**
 * Implementation class of the mutable list.
 *
 * @param <T> given type
 */
public class MutableListADTImpl<T> implements List<T>, Mutable {

  /**
   * ListADT data type.
   */
  private ListADT<T> data;

  /**
   * Default constructor which gives a empty node of data.
   */
  public MutableListADTImpl() {
    data = new ListADTImpl<>();
  }

  /**
   * Constructor with given ListADT type.
   *
   * @param data ListADT node
   */
  public MutableListADTImpl(ListADT<T> data) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be empty.");
    }
    this.data = data;
  }

  /**
   * Add a new object to the front data list. If the given data is null, throw
   * IllegalArgumentException.
   *
   * @param b given object
   * @return this mutable object
   */
  public List addFront(T b) {
    if (b == null) {
      throw new IllegalArgumentException("cannot operate null.");
    }
    data.addFront(b);
    return this;
  }

  /**
   * Add a new object to the back data list. If the given data is null, throw
   * IllegalArgumentException.
   *
   * @param b given object
   * @return this mutable object
   */
  public List addBack(T b) {
    if (b == null) {
      throw new IllegalArgumentException("cannot operate null.");
    }
    data.addBack(b);
    return this;
  }

  /**
   * Add a new object to the data list at the index position. If the given data is null, throw
   * IllegalArgumentException. If the index is not int the legal range, throw
   * IllegalArgumentException.
   *
   * @param index position
   * @param b     given new data
   * @return list after adding
   */
  public List add(int index, T b) {
    if (b == null) {
      throw new IllegalArgumentException("cannot operate null.");
    }
    data.add(index, b);
    return this;
  }

  @Override
  public int getSize() {
    return data.getSize();
  }

  /**
   * Remove the first instance of this object from this list.
   *
   * @param b the object to be removed
   * @return the mutable list after removing
   */
  public List remove(T b) {
    if (b == null) {
      throw new IllegalArgumentException("cannot operate null.");
    }
    data.remove(b);
    return this;
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    return data.get(index);
  }

  @Override
  public <R> List<R> map(Function<T, R> converter) {
    ListADT<R> result = data.map(converter);
    return new MutableListADTImpl<>(result);
  }

  @Override
  public Immutable<T> convertToImmutable() {
    return new ImmutableListADTImpl<>(data);
  }
}
