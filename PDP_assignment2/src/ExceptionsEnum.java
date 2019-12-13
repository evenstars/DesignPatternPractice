/**
 * This enumerate class representing all the exception information the XML parser can give.
 */
public enum ExceptionsEnum {
  INVALID_START_FIRST_CHAR("input first char of the start tag is invalid"),
  INVALID_START_CONTENT("input chars of start tag is invalid"),
  INVALID_END("input chars of end tag is invalid"),
  INVALID_CONTENT("input chars of content is invalid"),
  MULTIPLE_ROOT("multiple root node appears"),
  ERROR_XML_START("the start char of this XML file is error"),
  EMPTY_TAG("the tag is empty");

  /**
   * The string type prompts.
   */
  private String message;

  /**
   * Get the prompt.
   *
   * @return the prompt
   */
  public String getMessage() {
    return message;
  }

  /**
   * The construct function of this enumerate class.
   *
   * @param message the prompt information
   */
  ExceptionsEnum(String message) {
    this.message = message;
  }
}
