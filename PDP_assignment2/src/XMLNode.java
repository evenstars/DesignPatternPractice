import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store data, the data means sub tag or characters content. One XMLNode
 * object only could represent single type of data.
 */
public class XMLNode {

  /**
   * String buffer used to cache character in single-thread environment.
   */
  private StringBuilder stringBuilder;

  /**
   * The tag name if this object is a tag.
   */
  private String name;

  /**
   * The character content if this object is a character series.
   */
  private String content;

  /**
   * Whether this object is a tag.
   */
  private Boolean isTag;

  /**
   * Whether this object is a character content.
   */
  private Boolean isContent;

  /**
   * If this object is a tag, whether the end tag is appeared.
   */
  private Boolean isFinished;

  /**
   * The children elements of this object, if this object is a tag.
   */
  private List<XMLNode> tagChildren;

  /**
   * Get the tag children if this object is a tag.
   *
   * @return A List representing the children objects
   */
  public List<XMLNode> getTagChildren() {
    return tagChildren;
  }

  /**
   * Get the tag instance.
   *
   * @return The tag instance
   */
  public static XMLNode getTagInstance() {
    XMLNode data = new XMLNode(false);
    return data;
  }

  /**
   * Create the character content instance.
   *
   * @return The character content instance
   */
  public static XMLNode getContentInstance() {
    XMLNode data = new XMLNode(true);
    return data;
  }

  /**
   * Construction function for this XMLNode class, specifying the type of this object.
   *
   * @param isContent whether this object is a tag or a character content.
   */
  public XMLNode(Boolean isContent) {
    this.isContent = isContent;
    this.isTag = !isContent;
    stringBuilder = new StringBuilder();
    name = content = null;
    if (this.isTag) {
      tagChildren = new ArrayList<>();
    }
    isFinished = false;
  }

  /**
   * Input one character.
   *
   * @param character character inputted
   */
  public void input(Character character) {
    stringBuilder.append(character);
  }

  /**
   * Get the string buffer until now.
   *
   * @return the stringBuilder the object used.
   */
  public StringBuilder getStringBuilder() {
    return stringBuilder;
  }

  /**
   * Finished input for tag name or character content.
   */
  public void finished() {
    if (isContent) {
      this.content = stringBuilder.toString();
    } else {
      this.name = stringBuilder.toString();
    }
    stringBuilder.setLength(0);
  }

  /**
   * Return the type of this object.
   *
   * @return Whether this object is character content
   */
  public Boolean isContent() {
    return this.isContent;
  }

  /**
   * Return the type of this object.
   *
   * @return Whether this object is a tag
   */
  public Boolean isTag() {
    return this.isTag;
  }

  /**
   * Get the tag name if this tag is a tag.
   *
   * @return the string type tag name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the tag name if this tag is a tag.
   *
   * @return the string type tag name
   */
  public String getContent() {
    return content;
  }

  /**
   * Get whether the tag is finished, which means the end tag is appeared.
   *
   * @return boolean type representing whether the end tag appeared.
   */
  public Boolean getIsFinished() {
    return isFinished;
  }

  /**
   * Set that the end tag has appeared.
   *
   * @param isFinished the boolean type variable representing whether the end tag has appeared.
   */
  public void setIsFinished(Boolean isFinished) {
    this.isFinished = isFinished;
  }
}
