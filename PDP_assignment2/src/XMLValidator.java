/**
 * This class is extends the AbstractXMLParser, and is used for outputting simple prompts about the
 * input XML.
 */
public class XMLValidator extends AbstractXMLParser {

  /**
   * The construction function, invoking parent class's cunctruction function.
   */
  public XMLValidator() {
    super();
  }

  /**
   * Accept a single character as input, and return the new parser as a result of handling this
   * character.
   *
   * @param c the input character
   * @return the parser after handling the provided character
   * @throws InvalidXMLException if the input causes the XML to be invalid
   */
  @Override
  public XMLParser input(char c) throws InvalidXMLException {
    if (super.getCurrentStatus() == StatusEnum.ERROR) {
      return null;
    }
    super.process(c);
    return this;
  }

  /**
   * Provide the output of the parser, given all the inputs it has been provided so far. The content
   * and format of this output is defined by individual implementations.
   *
   * @return the output of the parser as a String object
   */
  @Override
  public String output() {
    if (super.getCurrentStatus() == StatusEnum.ERROR) {
      return null;
    }
    StatusEnum statusEnum = super.getCurrentStatus();
    if (statusEnum == StatusEnum.UNINITIALED) {
      return "Status:Empty";
    } else if (statusEnum == StatusEnum.FINISHED) {
      return "Status:Valid";
    } else {
      return "Status:Incomplete";
    }
  }
}
