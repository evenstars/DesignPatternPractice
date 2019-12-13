package listadt;

/**
 * Immutable interface whose subclass cannot be modified and offer way to convert to mutable
 * object.
 * @param <T> given type
 */
public interface Immutable<T> {

  /**
   * Convert to mutable object.
   *
   * @return mutable object
   */
  Mutable convertToMutable();
}
