package util;

/**
 * Class for generating pixel.
 */
public class PixelGenerator {

  /**
   * Generate rbga pixel.
   *
   * @param alpha transparent
   * @param red red
   * @param green green
   * @param blue blue
   * @return generated pixel
   */
  public static int colorARGBGenerator(int alpha, int red, int green, int blue) {
    return (alpha << 24) | (red << 16) | (green << 8) | blue;
  }
}
