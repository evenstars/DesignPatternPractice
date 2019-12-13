package config;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton pattern config reader for reading the properties file. Do not support concurrency.
 */
public class ConfigReader {

  /**
   * Properties file.
   */
  private static Properties properties;

  /**
   * Method for getting properties instance. If not initialize, initialize first.
   *
   * @return properties instance
   */
  public static Properties getInstance() {
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
