package model;

import java.awt.image.BufferedImage;

/**
 * Interface for processing the given image.
 */
public interface ImageProcessor {

  /**
   * Process function of this interface.
   *
   * @return processed bufferedImage image
   */
  BufferedImage process();

}
