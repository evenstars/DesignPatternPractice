package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class for the inputUtil which relies on the file stream.
 */
public class InputFileUtil implements InputUtil {

  /**
   * BuggerReader helper.
   */
  private BufferedReader bufferedReader;

  /**
   * Constructor specifying the file location with relative path starting from src.
   *
   * @param location file relative location
   */
  public InputFileUtil(String location) {
    File file = new File(location);
    try {
      bufferedReader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<double[]> parse() {
    List<double[]> data = new ArrayList<>();
    String curLine;
    try {
      while ((curLine = bufferedReader.readLine()) != null) {
        String[] point = curLine.split(" ");
        data.add(new double[]{Double.parseDouble(point[0]), Double.parseDouble(point[1])});
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }
}
