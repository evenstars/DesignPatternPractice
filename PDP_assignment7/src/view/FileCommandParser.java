package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Command parser based on file.
 */
public class FileCommandParser implements CommandParser {

  /**
   * File containing commands.
   */
  private File file;

  /**
   * Constructor given a path and initialize the file.
   *
   * @param path given path of the file containing commands
   */
  public FileCommandParser(String path) {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("Script path cannot be null or empty.");
    }
    file = new File(path);
  }

  @Override
  public List<String> parseCommands() {
    List<String> result = new ArrayList<>();
    try {
      FileReader reader = new FileReader(file);
      Scanner scanner = new Scanner(reader);
      while (scanner.hasNextLine()) {
        String command = scanner.nextLine();
        if (!command.isEmpty()) {
          result.add(command);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return result;
  }
}
