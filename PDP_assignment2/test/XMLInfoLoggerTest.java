import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the XMLInfoLogger class.
 */
public class XMLInfoLoggerTest {

  /**
   * The object used for testing.
   */
  XMLInfoLogger xmlInfoLogger;

  /**
   * Test the valid output that there is no character content between tags.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputWithoutContent() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input('<').input('/').input('a')
            .input('>');
    assertEquals("Started:a\nEnded:a\n", xmlInfoLogger.output());
  }

  /**
   * Test the valid output that there is character content between tags.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputWithContent() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input('F').input('<').input('/')
            .input('a').input('>');
    assertEquals("Started:a\nCharacters:F\nEnded:a\n", xmlInfoLogger.output());
  }

  /**
   * Test the valid output if there is space in the content.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputWithSpaceContent() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input(' ')
            .input(' ').input(' ').input('<').input('/').input('a').input('>');
    assertEquals("Started:a\nCharacters:   \nEnded:a\n", xmlInfoLogger.output());
  }

  /**
   * Test if the end tag is lacked, the content before the lacked end cannot appear.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testOutputCharsWithoutNextEnd() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input('F')
            .input('F').input('<').input('/').input('a');
    assertEquals("Started:a\n", xmlInfoLogger.output());
  }

  /**
   * Test valid nested structure.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testOutputCharsWithNestedTagAndContent() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input('<').input('b').input('>')
            .input('B').input('<').input('/').input('b').input('>').input('A')
            .input('<').input('/').input('a').input('>');
    assertEquals("Started:a\nStarted:b\nCharacters:B\nEnded:b\nCharacters:A\nEnded:a\n",
            xmlInfoLogger.output());
  }

  /**
   * Test the valid tag name with 0-9,a-z,A-Z,:,-,_.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputWithValidTagName() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('A').input('5').input(':').input('-')
            .input('_').input('>').input('<').input('/').input('a').input('A').input('5')
            .input(':').input('-').input('_').input('>');
    assertEquals("Started:aA5:-_\nEnded:aA5:-_\n", xmlInfoLogger.output());
  }

  /**
   * Test invalid tag name whose first character is a number.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidTagStartNumber() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('0').input('A').input('5').input(':')
            .input('-').input('_').input('>').input('<').input('/').input('O')
            .input('A').input('5').input(':').input('-').input('_').input('>');
  }

  /**
   * Test invalid tag name whose first character is -.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidTagStartLine() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('-').input('A').input('5').input(':').input('-')
            .input('_').input('>').input('<').input('/').input('-').input('A').input('5')
            .input(':').input('-').input('_').input('>');
  }

  /**
   * Test invalid tag name who contains invalid character.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidTagChar() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('[').input('5').input(':').input('-')
            .input('_').input('>').input('<').input('/').input('a').input('[').input('5')
            .input(':').input('-').input('_').input('>');
  }

  /**
   * Test if the < appeared not as the start of a tag.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidContentLeft() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input('<')
            .input('<').input('/').input('a').input('>');
  }

  /**
   * Test if the > appeared not as the end of a tag.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidContentRight() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input('>')
            .input('<').input('/').input('a').input('>');
  }

  /**
   * Test if the nested structure is error.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidNestedTag() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input('<').input('b').input('>')
            .input('B').input('<').input('/').input('a').input('>');
  }

  /**
   * Test if there are multiple root nodes.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testMultipleRoot() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>').input('<').input('/').input('a')
            .input('>').input('<').input('b').input('>').input('<').input('/')
            .input('b').input('>');
  }

  /**
   * Test if the parser can return the right exception.
   */
  @Test
  public void testException() {
    xmlInfoLogger = new XMLInfoLogger();
    try {
      xmlInfoLogger.input('<').input('a').input('>').input('<').input('/').input('a').input('>')
              .input('<').input('b').input('>').input('<').input('/').input('b').input('>');
      fail("no exception here");
    } catch (InvalidXMLException e) {
      assertEquals("multiple root node appears", e.getMessage());
    }
  }

  /**
   * Test if the output transits from valid status to invalid status according to the input.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testOutputTransitFromIncompleteToInValid() throws InvalidXMLException {
    xmlInfoLogger = new XMLInfoLogger();
    xmlInfoLogger.input('<').input('a').input('>');
    assertEquals("Started:a\n", xmlInfoLogger.output());
    try {
      xmlInfoLogger.input('>');
      fail("no exception here");
    } catch (InvalidXMLException e) {
      assertEquals("input chars of content is invalid", e.getMessage());
    }
  }
}