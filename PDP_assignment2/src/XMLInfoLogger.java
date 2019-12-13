import java.util.List;

/**
 * This class is extends the AbstractXMLParser, and is used for outputting log about the input XML.
 */
public class XMLInfoLogger extends AbstractXMLParser {

  /**
   * The construction function, invoking parent class's cunctruction function.
   */
  public XMLInfoLogger() {
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
    XMLNode fakeRoot = super.getFakeRoot();
    List<XMLNode> nexts = fakeRoot.getTagChildren();
    if (nexts.isEmpty() || (nexts.size() == 1 && nexts.get(0).getName() == null)) {
      return "";
    }
    XMLNode realRoot = nexts.get(0);
    StringBuilder stringBuilder = new StringBuilder();
    jointResult(realRoot, stringBuilder);
    return stringBuilder.toString();
  }

  private void jointResult(XMLNode root, StringBuilder stringBuilder) {
    stringBuilder.append("Started:");
    stringBuilder.append(root.getName());
    stringBuilder.append("\n");
    List<XMLNode> nexts = root.getTagChildren();
    for (int i = 0; i < nexts.size(); i++) {
      if (nexts.get(i).isTag() && nexts.get(i).getName() != null) {
        jointResult(nexts.get(i), stringBuilder);
      } else if (root.getIsFinished() || (i != nexts.size() - 1
              && nexts.get(i + 1).getName() != null)) {
        stringBuilder.append("Characters:");
        stringBuilder.append(nexts.get(i).getContent());
        stringBuilder.append("\n");
      }
    }
    if (root.getIsFinished()) {
      stringBuilder.append("Ended:");
      stringBuilder.append(root.getName());
      stringBuilder.append("\n");
    }
  }

}
