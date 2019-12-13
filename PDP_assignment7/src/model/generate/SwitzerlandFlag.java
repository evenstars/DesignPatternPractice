package model.generate;

import java.awt.image.BufferedImage;

import util.PixelGenerator;
import model.AbstractImageGenerator;

/**
 * Class for flag of the Switzerland.
 */
public class SwitzerlandFlag extends AbstractImageGenerator implements Flag {
  /**
   * Height proportion.
   */
  private static int heightPortion = 1;

  /**
   * Width proportion.
   */
  private static int widthPortion = 1;

  /**
   * White here.
   */
  private static int[] WHITE = new int[]{255, 255, 255};

  /**
   * Red here.
   */
  private static int[] RED = new int[]{255, 0, 0};

  /**
   * Colors used here.
   */
  private static int[][] colors = new int[][]{WHITE, RED};

  /**
   * Constructor with the width and height of the flag. If the proportion is incorrect, throw
   * exception.
   *
   * @param width  width of the flag
   * @param height height of the flag
   */
  public SwitzerlandFlag(int width, int height) {
    super(width, height);
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
    int piece = width / 32;
    int horHStart = piece * 13;
    int horHEnd = piece * 19;
    int horWStart = piece * 6;
    int horWEnd = piece * 26;
    int verHStart = piece * 6;
    int verHEnd = piece * 26;
    int verWStart = piece * 13;
    int verWEnd = piece * 19;
    for (int h = 0; h < height; h++) {
      for (int w = 0; w < width; w++) {
        newImage.setRGB(w, h, PixelGenerator.colorARGBGenerator(255, RED[0], RED[1], RED[2]));
      }
    }
    for (int h = horHStart; h < horHEnd; h++) {
      for (int w = horWStart; w < horWEnd; w++) {
        newImage.setRGB(w, h, PixelGenerator.colorARGBGenerator(255, WHITE[0], WHITE[1], WHITE[2]));
      }
    }
    for (int h = verHStart; h < verHEnd; h++) {
      for (int w = verWStart; w < verWEnd; w++) {
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
