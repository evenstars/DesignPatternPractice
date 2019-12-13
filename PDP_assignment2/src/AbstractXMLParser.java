import java.util.Stack;

/**
 * The abstract xml parser class implements XML parser, which implements the process function that
 * could analysis the states of the xml parser depending on the input characters.
 */
abstract class AbstractXMLParser implements XMLParser {

  /**
   * Stack used for retrieving parent XMLNode.
   */
  private Stack<XMLNode> stack;

  /**
   * The current level of XMLNode.
   */
  private XMLNode current;

  /**
   * The fake root, which contains only one child node that is the root node.
   */
  private XMLNode fakeRoot;

  /**
   * Current status, the values are int the StatesEnum class.
   */
  private StatusEnum currentStatus;

  /**
   * The construction function of this XML parser.
   */
  public AbstractXMLParser() {
    stack = new Stack<>();
    fakeRoot = current = new XMLNode(false);
    currentStatus = StatusEnum.UNINITIALED;
  }

  /**
   * Process the input characters, and decide the states of the current XMLNode.
   *
   * @param c the input character
   * @throws InvalidXMLException the exception contains the prompt information.
   */
  public void process(char c) throws InvalidXMLException {
    if (currentStatus == StatusEnum.UNINITIALED) {
      // uninitialized status
      if (c != '<') {
        currentStatus = StatusEnum.ERROR;
        throw new InvalidXMLException(ExceptionsEnum.ERROR_XML_START.getMessage());
      }
      currentStatus = StatusEnum.WAITING;
      process(c);
    } else if (currentStatus == StatusEnum.WAITING) {
      // waiting for input status
      if (c == '<') {
        currentStatus = StatusEnum.UNDECIDE_TAG;
      } else {
        XMLNode content = XMLNode.getContentInstance();
        current.getTagChildren().add(content);
        stack.push(current);
        current = content;
        currentStatus = StatusEnum.CONTENT;
        process(c);
      }
    } else if (currentStatus == StatusEnum.CONTENT) {
      // input content status
      if (c == '>') {
        currentStatus = StatusEnum.ERROR;
        throw new InvalidXMLException(ExceptionsEnum.INVALID_CONTENT.getMessage());
      } else if (c == '<') {
        current.finished();
        current = stack.pop();
        currentStatus = StatusEnum.WAITING;
        process(c);
      } else {
        current.input(c);
      }
    } else if (currentStatus == StatusEnum.UNDECIDE_TAG) {
      // undecided start or end tag
      if (c == '/') {
        currentStatus = StatusEnum.END;
      } else {
        XMLNode next = XMLNode.getTagInstance();
        current.getTagChildren().add(next);
        stack.add(current);
        current = next;
        currentStatus = StatusEnum.START;
        process(c);
      }
    } else if (currentStatus == StatusEnum.START) {
      // start tag
      if (!Character.isDigit(c) && !Character.isUpperCase(c) && !Character.isLetter(c)
              && c != '-' && c != '_' && c != ':' && c != '>') {
        currentStatus = StatusEnum.ERROR;
        throw new InvalidXMLException(ExceptionsEnum.INVALID_START_CONTENT.getMessage());
      }
      if (current.getStringBuilder().length() == 0 && (Character.isDigit(c) || c == '-')) {
        currentStatus = StatusEnum.ERROR;
        throw new InvalidXMLException(ExceptionsEnum.INVALID_START_FIRST_CHAR.getMessage());
      }
      if (c == '>') {
        if (current.getStringBuilder().length() == 0) {
          currentStatus = StatusEnum.ERROR;
          throw new InvalidXMLException(ExceptionsEnum.EMPTY_TAG.getMessage());
        }
        current.finished();
        currentStatus = StatusEnum.WAITING;
      } else {
        current.input(c);
      }
    } else if (currentStatus == StatusEnum.END) {
      // end tag
      String startName = current.getName();
      int idx = current.getStringBuilder().length();
      if (idx == startName.length() && c == '>') {
        current.setIsFinished(true);
        current = stack.pop();
        currentStatus = current == fakeRoot ? StatusEnum.FINISHED : StatusEnum.WAITING;
      } else if (idx >= startName.length() || startName.charAt(idx) != c) {
        currentStatus = StatusEnum.ERROR;
        throw new InvalidXMLException(ExceptionsEnum.INVALID_END.getMessage());
      } else {
        current.input(c);
      }
    } else if (currentStatus == StatusEnum.FINISHED) {
      // finished
      currentStatus = StatusEnum.ERROR;
      throw new InvalidXMLException(ExceptionsEnum.MULTIPLE_ROOT.getMessage());
    }
  }

  /**
   * Get the current status.
   *
   * @return the enumerate class StatusEnum
   */
  public StatusEnum getCurrentStatus() {
    return currentStatus;
  }

  /**
   * Get the fake root, which only could contain root node.
   *
   * @return the fake XMLNode root
   */
  public XMLNode getFakeRoot() {
    return fakeRoot;
  }
}

