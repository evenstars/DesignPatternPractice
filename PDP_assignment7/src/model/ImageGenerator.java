package model;

import java.awt.image.BufferedImage;

/**
 * Interface for generating image.
 */
public interface ImageGenerator {

  /**
   * Generate function of this interface.
   *
   * @return generated bufferedImage image
   */
  BufferedImage generate();
}
