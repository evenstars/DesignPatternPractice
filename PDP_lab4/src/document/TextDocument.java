package document;

/**
 * Interface specified the text document's operation including get length, get text and get common
 * maximum sub string with another document.
 */
public interface TextDocument {

  /**
   * Get the length of words. The length of empty text is 0. The words are seperated by spaces.
   * Multiple adjacent spaces count one separator.
   *
   * @return length of the words
   */
  int getWordCount();

  /**
   * Get the string type of text.
   *
   * @return the text
   */
  String getText();

  /**
   * Return the maximum length sub string. The input argument cannot be null. And if there is
   * multiple tie answer( several with same length), we pick the first one.
   *
   * @param other Given another text
   * @return maximum length String
   */
  String commonSubText(TextDocument other);
}
