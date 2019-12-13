package document;

/**
 * The implementation class of TextDocument.
 */
public class TextDocumentImpl implements TextDocument {

  /**
   * The string type of text, cannot be null after initialization.
   */
  private String text;

  /**
   * Construction method to initialize the text. The text cannot be null after initialization.
   *
   * @param text given text data
   */
  public TextDocumentImpl(String text) {
    if (text == null) {
      throw new IllegalArgumentException("Text cannot be null.");
    }
    this.text = text;
  }

  @Override
  public int getWordCount() {
    return text.split("\\s+").length;
  }

  @Override
  public String getText() {
    return text;
  }

  @Override
  public String commonSubText(TextDocument other) {
    if (other == null) {
      throw new IllegalArgumentException("The another text cannot be null.");
    }
    if (text.isEmpty() || other.getText().isEmpty()) {
      return "";
    }
    char[] curData = text.toCharArray();
    char[] otherData = other.getText().toCharArray();
    int curLength = curData.length;
    int otherLength = otherData.length;
    int maxLength = 0;
    int endIdxInCur = 1;
    int[][] dp = new int[curLength + 1][otherLength + 1];
    for (int i = 1; i <= curLength; i++) {
      for (int j = 1; j <= otherLength; j++) {
        if (curData[i - 1] == otherData[j - 1]) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = 0;
        }
        if (dp[i][j] > maxLength) {
          maxLength = dp[i][j];
          endIdxInCur = i;
        }
      }
    }
    return text.substring(endIdxInCur - maxLength, endIdxInCur);
  }
}
