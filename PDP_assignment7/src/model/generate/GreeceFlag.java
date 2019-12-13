package model.generate;

import java.awt.image.BufferedImage;

import util.PixelGenerator;
import model.AbstractImageGenerator;

/**
 * Class for flag of the Greece.
 */
public class GreeceFlag extends AbstractImageGenerator implements Flag {

  /**
   * Height proportion.
   */
  private static int heightPortion = 2;

  /**
   * Width proportion.
   */
  private static int widthPortion = 3;

  /**
   * Blue here.
   */
  private static int[] BLUE = new int[]{0, 85, 164};

  /**
   * White here.
   */
  private static int[] WHITE = new int[]{255, 255, 255};

  /**
   * Color here.
   */
  private static int[][] colors = new int[][]{BLUE, WHITE};

  /**
   * Constructor with the width and height of the flag. If the proportion is incorrect, throw
   * exception.
   *
   * @param width  width of the flag
   * @param height height of the flag
   */
  public GreeceFlag(int width, int height) {
    super(width, height);
    if ((width <= 0 || height <= 0)) {
      throw new IllegalArgumentException("Width and height cannot be zero or negative.");
    }
    if ((width / widthPortion) != (height / heightPortion)) {
      throw new IllegalArgumentException("Illegal portion, which should be " + widthPortion +
              " / " + heightPortion);
    }
  }

  /**
   * Generate the flag. If there is remaining room, fill this room with the red color.
   *
   * @return generated flag
   */
  @Override
  public BufferedImage generate() {
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage newImage = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    int stripeSize = height / 9;
    //generate layer 1
    for (int h = 0; h < height; h += stripeSize) {
      int colorIdx = (h / stripeSize) & 1;
      int hEnd = Math.min(height, h + stripeSize);
      if (height - h < stripeSize) {
        colorIdx = 1 - colorIdx;
      }
      int[] color = colors[colorIdx];
      for (int i = h; i < hEnd; i++) {
        for (int w = 0; w < width; w++) {
          newImage.setRGB(w, i, PixelGenerator.colorARGBGenerator(255, color[0], color[1], color[2]));
        }
      }
    }
    //generate layer 2
    int layer2Size = 5 * stripeSize;
    for (int i = 0; i < layer2Size; i++) {
      for (int j = 0; j < layer2Size; j++) {
        newImage.setRGB(i, j, PixelGenerator.colorARGBGenerator(255, BLUE[0], BLUE[1], BLUE[2]));
      }
    }
    //generate layer 3
    for (int h = 2 * stripeSize; h < 3 * stripeSize; h++) {
      for (int w = 0; w < 5 * stripeSize; w++) {
        newImage.setRGB(w, h, PixelGenerator.colorARGBGenerator(255, WHITE[0], WHITE[1], WHITE[2]));
      }
    }
    for (int w = 2 * stripeSize; w < 3 * stripeSize; w++) {
      for (int h = 0; h < 5 * stripeSize; h++) {
        newImage.setRGB(w, h, PixelGenerator.colorARGBGenerator(255, WHITE[0], WHITE[1], WHITE[2]));
      }
    }
    return newImage;
  }

  @Override
  public double getWidthToHeightPortion() {
    return (double) widthPortion / (double) heightPortion;
  }
}
