/**
 * This class represents a checked exception. This is used by xmlparser
 * implementations to signal invalid XML.
 */

public class InvalidXMLException extends Exception {
  public InvalidXMLException(String message) {
    super(message);
  }
}