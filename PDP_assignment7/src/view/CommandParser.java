package view;

import java.util.List;

/**
 * Parser commands which are represented by string seperated by space.
 */
public interface CommandParser {

  /**
   * Parse commands to several string.
   *
   * @return list of string representing command
   */
  List<String> parseCommands();
}
