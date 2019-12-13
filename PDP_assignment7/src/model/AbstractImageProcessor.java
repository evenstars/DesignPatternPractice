package model;

import java.awt.image.BufferedImage;

/**
 * Abstract class implementing the imageProcessor interface and have the IO function here.
 */
public abstract class AbstractImageProcessor implements ImageProcessor {
  /**
   * BufferedImage that will be processed by subclass or empty image.
   */
  protected BufferedImage image;
  private final int permittedMax = 255;
  private final int permittedMin = 0;
  protected int imageWidth;
  protected int imageHeight;


  /**
   * The helper function help tp get the width and height of the input image.
   */
  private void initializeProcessor() {
    imageWidth = image.getWidth();
    imageHeight = image.getHeight();
  }

  /**
   * Constructor that will get the width and height of the input image which is loaded through
   * config.properties file. Throw IllegalArgumentException if image is null.
   *
   * @param image the given image
   */
  public AbstractImageProcessor(BufferedImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be empty.");
    }
    this.image = image;
    initializeProcessor();
  }

  /**
   * Helper function help to clamp value of each channel.
   *
   * @param value given int channel value.
   * @return clamped int value.
   */
  protected int clampValue(int value) {
    value = Math.min(permittedMax, value);
    return Math.max(permittedMin, value);
  }

  /**
   * Helper function help to clamp value of each channel.
   *
   * @param value given double channel value.
   * @return clamped double value.
   */
  protected double clampValue(double value) {
    value = Math.min(permittedMax, value);
    return Math.max(permittedMin, value);
  }
}
