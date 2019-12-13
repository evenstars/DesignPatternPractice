package listadt;

/**
 * Mutable interface whose subclass can be modified and offer way to convert to immutable object.
 *
 * @param <T> given type
 */
public interface Mutable<T> {

  /**
   * Convert to immutable object.
   *
   * @return immutable object
   */
  Immutable convertToImmutable();
}
