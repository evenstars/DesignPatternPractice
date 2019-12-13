package image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.ConfigReader;

/**
 * Utilization class for IO, which could read or write from config document.
 */
public class ImageIOUtil {

  /**
   * Read from config file.
   *
   * @return image read from location of the config
   */
  public static BufferedImage read() {
    String url = ConfigReader.getConfigReader().getProperty("imagePath");
    return read(url);
  }

  /**
   * Read from specified url.
   *
   * @param url specified url
   * @return read image
   */
  public static BufferedImage read(String url) {
    File imageFile = new File(url);
    BufferedImage image = null;
    try {
      image = ImageIO.read(imageFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }

  /**
   * Write from config file.
   *
   * @return image written from location of the config
   */
  public static boolean write(String format, BufferedImage image) {
    String outputPath = ConfigReader.getConfigReader().getProperty("outputImagePath");
    outputPath = outputPath + '.' + format;
    return write(outputPath, format, image);
  }

  /**
   * Write from specified url.
   *
   * @param url specified url
   * @return write image
   */
  public static boolean write(String url, String format, BufferedImage image) {
    File target = new File(url);
    try {
      return ImageIO.write(image, format, target);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      return false;
    }
  }
}
