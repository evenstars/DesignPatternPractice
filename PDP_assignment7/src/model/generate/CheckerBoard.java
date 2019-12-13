package model.generate;

import java.awt.image.BufferedImage;

import util.PixelGenerator;
import model.AbstractImageGenerator;

/**
 * CheckerBoard type of board, and whose color only contains white and black. The left top corner
 * always start from white. The board and the squares here have to be square.
 */
public class CheckerBoard extends AbstractImageGenerator implements Board {

  /**
   * Size of the squares.
   */
  private int squareSize;

  /**
   * Colors used here, the first is white and the second is black.
   */
  private static int[][] colors = new int[][]{{255, 255, 255}, {0, 0, 0}};

  /**
   * Constructor for the checkerboard.
   *
   * @param size       size the the checkerboard
   * @param squareSize square size
   */
  public CheckerBoard(int size, int squareSize) {
    super(size, size);
    if (squareSize <= 0) {
      throw new IllegalArgumentException("The checkerboard size and the square size " +
              "cannot be negtive or zero.");
    }
    this.squareSize = squareSize;
  }

  /**
   * Generate the checkerboard. The left top corner or the start point is white. If there is no
   * enough room for new square, then fill the incomplete square with the corresponding colors.
   */
  @Override
  public BufferedImage generate() {
    int height = image.getHeight();
    int width = image.getWidth();
    BufferedImage newImage = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);
    for (int h = 0; h < height; h += squareSize) {
      for (int w = 0; w < width; w += squareSize) {
        int hEnd = Math.min(h + squareSize, height);
        int wEnd = Math.max(w + squareSize, width);
        int hIdx = h / squareSize;
        int wIdx = w / squareSize;
        int[] color = (hIdx & 1) == (wIdx & 1) ? colors[0] : colors[1];
        for (int i = h; i < hEnd; i++) {
          for (int j = w; j < wEnd; j++) {
            try {
              newImage.setRGB(j, i, PixelGenerator
                      .colorARGBGenerator(255, color[0], color[1], color[2]));
            } catch (Exception e) {
              System.out.println(e.getStackTrace());
            }
          }
        }
      }
    }
    return newImage;
  }

  @Override
  public int getSquareSize() {
    return squareSize;
  }
}
