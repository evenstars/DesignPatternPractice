package image;

/**
 * Class for generating pixel.
 */
public class PixelGenerator {

  /**
   * Generate rbga pixel.
   *
   * @param a transparent
   * @param r red
   * @param g green
   * @param b blue
   * @return generated pixel
   */
  public static int aRGBGenerator(int a, int r, int g, int b) {
    return (a << 24) | (r << 16) | (g << 8) | b;
  }
}
