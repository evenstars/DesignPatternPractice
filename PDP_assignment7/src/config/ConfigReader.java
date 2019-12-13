package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config reader supporting single object.
 */
public class ConfigReader {

  /**
   * Properties data used for reading config.
   */
  private static Properties properties;

  /**
   * Get the single object reader. If null, initialize it.
   *
   * @return the config reader
   */
  public static Properties getConfigReader() {
    if (properties == null) {
      properties = new Properties();
      try {
        InputStream inputStream = new FileInputStream("src/config/config.properties");
        properties.load(inputStream);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return properties;
  }
}
