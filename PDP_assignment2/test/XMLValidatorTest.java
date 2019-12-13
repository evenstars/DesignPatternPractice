import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the XMLValidator class.
 */
public class XMLValidatorTest {

  /**
   * The object used for testing the class.
   */
  XMLValidator xmlValidator;

  /**
   * Test the valid output that there is character content between tags.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputWithContent() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('>').input('T')
            .input('<').input('b').input('>').input('F').input('<').input('/').input('b').input('>')
            .input('<').input('/').input('a').input('>');
    assertEquals("Status:Valid", xmlValidator.output());
  }

  /**
   * Test the valid output that there is no character content between tags.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputWithoutContent() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('>').input('<').input('/').input('a').input('>');
    assertEquals("Status:Valid", xmlValidator.output());
  }

  /**
   * Test the valid output if there is space in the content.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputWithSpaceContent() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('>').input(' ')
            .input(' ').input(' ').input('<').input('/').input('a').input('>');
    assertEquals("Status:Valid", xmlValidator.output());
  }

  /**
   * Test the transition from empty status to incomplete status according to the input.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputTransitFromEmptyToIncomplete() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    assertEquals("Status:Empty", xmlValidator.output());
    xmlValidator.input('<').input('a').input('>');
    assertEquals("Status:Incomplete", xmlValidator.output());
  }

  /**
   * Test the transition from incomplete status to valid status according to the input.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testOutputTransitFromIncompleteToValid() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('>');
    assertEquals("Status:Incomplete", xmlValidator.output());
    xmlValidator.input('<').input('/').input('a').input('>');
    assertEquals("Status:Valid", xmlValidator.output());
  }

  /**
   * Test the valid tag name with 0-9,a-z,A-Z,:,-,_.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test
  public void testValidOutputWithValidTagName() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('A').input('5').input(':').input('-')
            .input('_').input('>').input('<').input('/').input('a').input('A').input('5')
            .input(':').input('-').input('_').input('>');
    assertEquals("Status:Valid", xmlValidator.output());
  }

  /**
   * Test invalid tag name whose first character is a number.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidTagStartNumber() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('0').input('A').input('5').input(':').input('-').input('_')
            .input('>').input('<').input('/').input('O').input('A').input('5').input(':')
            .input('-').input('_').input('>');
  }

  /**
   * Test invalid tag name whose first character is -.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidTagStartLine() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('-').input('A').input('5').input(':').input('-').input('_')
            .input('>').input('<').input('/').input('-').input('A').input('5').input(':')
            .input('-').input('_').input('>');
  }

  /**
   * Test invalid tag name who contains invalid character.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidTagChar() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('[').input('5').input(':').input('-')
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
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('>').input('<')
            .input('<').input('/').input('a').input('>');
  }

  /**
   * Test if the > appeared not as the end of a tag.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidContentRight() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('>').input('>')
            .input('<').input('/').input('a').input('>');
  }

  /**
   * Test for the tags is not matched.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testInvalidTagMatch() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('>').input('<').input('b').input('>')
            .input('<').input('/').input('a').input('>');
  }

  /**
   * Test if there are multiple root nodes.
   *
   * @throws InvalidXMLException the exception appeared if the input if invalid
   */
  @Test(expected = InvalidXMLException.class)
  public void testMultipleRoot() throws InvalidXMLException {
    xmlValidator = new XMLValidator();
    xmlValidator.input('<').input('a').input('>').input('<').input('/').input('a').input('>')
            .input('<').input('b').input('>').input('<').input('/').input('b').input('>');
  }

  /**
   * Test if the parser can return the right exception.
   */
  @Test
  public void testException() {
    xmlValidator = new XMLValidator();
    try {
      xmlValidator.input('<').input('a').input('>').input('<').input('/').input('a').input('>')
              .input('<').input('b').input('>').input('<').input('/').input('b').input('>');
      fail("no exception here");
    } catch (InvalidXMLException e) {
      assertEquals("multiple root node appears", e.getMessage());
    }
  }
}