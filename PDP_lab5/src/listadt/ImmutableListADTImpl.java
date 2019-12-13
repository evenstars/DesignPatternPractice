package listadt;


import java.util.function.Function;

/**
 * Immutable list adt. All the function cannot modify the object.
 *
 * @param <T> given type
 */
public class ImmutableListADTImpl<T> implements List<T>, Immutable {

  /**
   * ListADT type of data.
   */
  private final ListADT<T> data;

  /**
   * default constructor giving empty data node.
   */
  public ImmutableListADTImpl() {
    data = new ListADTImpl<>();
  }

  /**
   * Constructor with given ListADT data. The data cannot be null.
   *
   * @param data given ListADT data
   */
  public ImmutableListADTImpl(ListADT<T> data) {
    if (data == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    this.data = data;
  }

  @Override
  public Mutable<T> convertToMutable() {
    return new MutableListADTImpl<>(data);
  }

  @Override
  public int getSize() {
    return data.getSize();
  }

  @Override
  public T get(int index) throws IllegalArgumentException {
    if (index < 0 || index >= data.getSize()) {
      throw new IllegalArgumentException("Wrong index range.");
    }
    return data.get(index);
  }

  @Override
  public <R> List<R> map(Function<T, R> converter) {
    ListADT<R> result = data.map(converter);
    return new ImmutableListADTImpl(result);
  }
}
