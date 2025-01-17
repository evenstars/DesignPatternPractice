package model;

import java.awt.image.BufferedImage;

/**
 * Abstract class implementing the imageGenerator interface and have the IO function here.
 */
public abstract class AbstractImageGenerator implements ImageGenerator {
  /**
   * BufferedImage generated by subclass or empty image.
   */
  protected BufferedImage image;

  /**
   * Constructor with width and height of this image. When width and height are negative or zero,
   * throw exception.
   *
   * @param width  width of this image
   * @param height height of this image
   */
  public AbstractImageGenerator(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height cannot be negative or zero");
    }
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }
}
