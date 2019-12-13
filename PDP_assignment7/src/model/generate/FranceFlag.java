package model.generate;

import java.awt.image.BufferedImage;

import util.PixelGenerator;
import model.AbstractImageGenerator;

/**
 * Generate France flag in this class.
 */
public class FranceFlag extends AbstractImageGenerator implements Flag {

  /**
   * The height proportion.
   */
  private static int heightPortion = 2;

  /**
   * The width proportion.
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
   * Red here.
   */
  private static int[] RED = new int[]{239, 65, 53};

  /**
   * Color set with given colors above.
   */
  private static int[][] colors = new int[][]{BLUE, WHITE, RED};

  /**
   * Constructor with the width and height, if the proportion is incorrect, throw new Exception.
   *
   * @param width  width of the flag
   * @param height height of the flag
   */
  public FranceFlag(int width, int height) {
    super(width, height);
    if ((width <= 0 || height <= 0)) {
      throw new IllegalArgumentException("Width and height cannot be zero or negative.");
    }
    if ((width / widthPortion) != (height / heightPortion)) {
      throw new IllegalArgumentException("Illegal portion, which should be "
              + widthPortion + " / " + heightPortion);
    }
  }

  /**
   * Generate the flag. If there is remaining room, fill this room with the red color.
   *
   * @return generated flag image
   */
  @Override
  public BufferedImage generate() {
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage newImage = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    int stripeSize = width / 3;
    for (int w = 0; w < width; w += stripeSize) {
      int colorIdx = w / stripeSize;
      colorIdx = Math.min(2, colorIdx);
      int wEnd = Math.max(width, w + stripeSize);
      int[] color = colors[colorIdx];
      for (int i = w; i < wEnd; i++) {
        for (int j = 0; j < height; j++) {
          newImage.setRGB(i, j, PixelGenerator.colorARGBGenerator(255, color[0], color[1], color[2]));
        }
      }
    }
    return newImage;
  }

  @Override
  public double getWidthToHeightPortion() {
    return (double) widthPortion / (double) heightPortion;
  }
}
