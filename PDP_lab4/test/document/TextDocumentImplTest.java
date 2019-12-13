package document;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the TextDocumentImpl.
 */
public class TextDocumentImplTest {

  /**
   * The object used for test.
   */
  private TextDocumentImpl textDocument;

  /**
   * Test for constructor when argument isn't empty.
   */
  @Test
  public void testConstructorNotEmpty() {
    textDocument = new TextDocumentImpl("abc");
    assertEquals("abc", textDocument.getText());
  }

  /**
   * Test for constructor when argument is empty.
   */
  @Test
  public void testConstructorEmpty() {
    textDocument = new TextDocumentImpl("");
    assertEquals("", textDocument.getText());
    assertEquals(1, textDocument.getWordCount());
  }

  /**
   * Test for constructor when argument is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    textDocument = new TextDocumentImpl(null);
  }

  /**
   * Test for getWordCount when text contains single word.
   */
  @Test
  public void testGetWordCountNotEmpty() {
    textDocument = new TextDocumentImpl("abcde");
    assertEquals(1, textDocument.getWordCount());
  }

  /**
   * Test for getWordCount when text contains multiple words.
   */
  @Test
  public void testGetWordCountMultipleWord() {
    textDocument = new TextDocumentImpl("abcde de dd");
    assertEquals(3, textDocument.getWordCount());
  }

  /**
   * Test for getWordCount when separator is multiple space.
   */
  @Test
  public void testGetWordCountMultipleSpace() {
    textDocument = new TextDocumentImpl("abcde de    dd");
    assertEquals(3, textDocument.getWordCount());
  }

  /**
   * Test for getWordCount when text is empty.
   */
  @Test
  public void testGetWordCountWhenEmpty() {
    textDocument = new TextDocumentImpl("");
    assertEquals(1, textDocument.getWordCount());
  }

  /**
   * Test for commonSubText when texts are both empty.
   */
  @Test
  public void testGetCommonWhenBothAreEmpty() {
    textDocument = new TextDocumentImpl("");
    TextDocument other = new TextDocumentImpl("");
    assertEquals("", textDocument.commonSubText(other));
  }

  /**
   * Test for commonSubText when argument text is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testGetCommonWhenArgumentIsNull() {
    textDocument = new TextDocumentImpl("");
    TextDocument other = null;
    textDocument.commonSubText(other);
  }

  /**
   * Test for commonSubText when texts are both not empty.
   */
  @Test
  public void testGetCommonWhenBothAreNotEmpty() {
    textDocument = new TextDocumentImpl("Today is a Thursday");
    TextDocument other = new TextDocumentImpl("This is a Thursday evening event");
    assertEquals(" is a Thursday", textDocument.commonSubText(other));
  }

  /**
   * Test for commonSubText when this text is empty.
   */
  @Test
  public void testGetCommonWhenThisTextAreEmpty() {
    textDocument = new TextDocumentImpl("");
    TextDocument other = new TextDocumentImpl("This is a Thursday evening event");
    assertEquals("", textDocument.commonSubText(other));
  }

  /**
   * Test for commonSubText when the other text is empty.
   */
  @Test
  public void testGetCommonWhenOtherTextAreEmpty() {
    textDocument = new TextDocumentImpl("Today is a Thursday");
    TextDocument other = new TextDocumentImpl("");
    assertEquals("", textDocument.commonSubText(other));
  }
}