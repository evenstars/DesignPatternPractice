package model.generate;

import java.awt.image.BufferedImage;

import util.PixelGenerator;
import model.AbstractImageGenerator;

/**
 * Colorful rainbow whose color are ROYGLIV.
 */
public class ColorRainbow extends AbstractImageGenerator implements Rainbow {

  /**
   * Size of the strip.
   */
  private int stripeSize;

  /**
   * Whether the direction is horizontal.
   */
  private boolean isHorizontal;

  /**
   * The colors used in this class. These color from index 0 - 6 are red, orange, yellow,
   * green ,blue, indigo and violet.
   */
  private static int[][] rainbowColors
      = new int[][]{{255, 0, 0}, {255, 127, 0}, {255, 255, 0}, {0, 255, 0}, {0, 0, 255},
        {75, 0, 130}, {148, 0, 211}};

  /**
   * Constructor of the rainbow.
   *
   * @param width        width of the image
   * @param height       height
   * @param stripeSize   stripe size
   * @param isHorizontal the direction, whether it is horizontal
   */
  public ColorRainbow(int width, int height, int stripeSize, boolean isHorizontal) {
    super(width, height);
    if (stripeSize <= 0) {
      throw new IllegalArgumentException("wrong stripe size.");
    }
    this.isHorizontal = isHorizontal;
    this.stripeSize = stripeSize;
  }

  /**
   * Generate the rainbow. Here if the direction is horizontal, then from left to right,
   * the order of the colors are ROYGBIV. But if the direction is vertical, then from top
   * to down, the order of the colors are ROYGBIV. If there is remaining place without
   * filling, the the color is violet. If the there is no enough space for all the
   * rainbows, then display the stripes in the legal range.
   *
   * @return generated image
   */
  @Override
  public BufferedImage generate() {
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage newImage = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    int out;
    int in;
    int w;
    int h;
    if (isHorizontal) {
      out = height;
      in = width;
    } else {
      out = width;
      in = height;
    }
    for (int a = 0; a < out; a++) {
      for (int b = 0; b < in; b++) {
        int colorIdx = a / stripeSize;
        colorIdx = colorIdx < 7 ? colorIdx : 6;
        int[] color = rainbowColors[colorIdx];
        int colorPixel = PixelGenerator.colorARGBGenerator(255, color[0], color[1], color[2]);
        if (isHorizontal) {
          w = b;
          h = a;
        } else {
          w = a;
          h = b;
        }
        newImage.setRGB(w, h, colorPixel);
      }
    }
    return newImage;
  }

  @Override
  public int getStripeSize() {
    return stripeSize;
  }

  @Override
  public boolean isHorizontal() {
    return isHorizontal;
  }

  @Override
  public void setDirection(boolean isHorizontal) {
    this.isHorizontal = isHorizontal;
  }
}
