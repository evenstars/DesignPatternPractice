package model.process;

import java.awt.image.BufferedImage;

import model.AbstractImageProcessor;
import util.PixelGenerator;

/**
 * The image will be dithered using the following methods: Firstly, we convert our image to
 * greyscale. Then we use Floyd-Steinberg algorithm to create "black and white" images. This is a
 * kind of image processing algorithms so it extends AbstractImageProcessor.
 */
public class DitheringTransformerProcessing extends AbstractImageProcessor {

  /**
   * The GreyscaleTransformerProcessing will help to process original image and generate a new
   * greyscale image for the future use.
   */
  private GreyscaleTransformerProcessing greyscaleTransformerProcessing;

  /**
   * Construction method with given image uses the one in AbstractImageProcessor class. It also
   * initializes a GreyscaleTransformerProcessing Object.
   *
   * @param bufferedImage the given image.
   */
  public DitheringTransformerProcessing(BufferedImage bufferedImage) {
    super(bufferedImage);
    greyscaleTransformerProcessing = new GreyscaleTransformerProcessing(bufferedImage);
  }

  @Override
  public BufferedImage process() {
    BufferedImage greyImage = greyscaleTransformerProcessing.process();
    BufferedImage newImage = new BufferedImage(imageWidth, imageHeight,
            greyImage.getType());
    for (int imageRowIndex = 0; imageRowIndex < imageHeight; imageRowIndex++) {
      for (int imageColIndex = 0; imageColIndex < imageWidth; imageColIndex++) {
        int pixel = greyImage.getRGB(imageColIndex, imageRowIndex);
        newImage.setRGB(imageColIndex, imageRowIndex, pixel);
      }
    }
    for (int imageRowIndex = 0; imageRowIndex < imageHeight; imageRowIndex++) {
      for (int imageColIndex = 0; imageColIndex < imageWidth; imageColIndex++) {
        int pixel = newImage.getRGB(imageColIndex, imageRowIndex);
        double oldRedColor = (pixel >> 16) & 0xff; // use red channel as old color
        double newRedColor;
        newRedColor = adjustColor(oldRedColor);
        double errorRed = oldRedColor - newRedColor;
        int newPixel = PixelGenerator.colorARGBGenerator(
                (int) (pixel >> 24) & 0xff, (int) newRedColor, (int) newRedColor,
                (int) newRedColor);
        newImage.setRGB(imageColIndex, imageRowIndex, newPixel);
        addError(imageRowIndex, imageColIndex + 1, errorRed, newImage,
                7.0 / 16.0);
        addError(imageRowIndex + 1, imageColIndex - 1, errorRed, newImage,
                3.0 / 16.0);
        addError(imageRowIndex + 1, imageColIndex, errorRed, newImage,
                5.0 / 16.0);
        addError(imageRowIndex + 1, imageColIndex + 1, errorRed, newImage,
                1.0 / 16.0);
      }
    }
    return newImage;
  }

  /**
   * The method checks if the given row and column is legal or not.
   *
   * @param row the given row.
   * @param col the given column.
   * @return True if the given row and column is legal. Otherwise return false.
   */
  private boolean checkInRange(int row, int col) {
    return (row >= 0 && row < imageHeight && col >= 0 && col < imageWidth);
  }

  /**
   * To get new color from the old color. Return 255 if int value of old color is close to 255 and
   * return 0 if int value of old color is close to 0.
   *
   * @param oldColor the given int value of old color.
   * @return 0 or 255 depending on which one the value of old color is closer to.
   */
  private int adjustColor(double oldColor) {
    if (oldColor > 127) {
      return 255;
    } else {
      return 0;
    }
  }

  /**
   * The method conveys the residual quantization error of a pixel onto its neighboring pixels so
   * that dithering can be achieved.
   *
   * @param row        the given row.
   * @param col        the given column.
   * @param errorRed   the given difference between old red color and new red color.
   * @param newImage   the given image that will be modified.
   * @param fractional the given specific fractional number to convey error.
   */
  private void addError(int row, int col, double errorRed,
                        BufferedImage newImage, double fractional) {
    if (checkInRange(row, col)) {
      int pixelBuffer = newImage.getRGB(col, row);
      int clampedRed = clampValue(((pixelBuffer >> 16) & 0xff) + (int) (fractional * errorRed));
      int clampedGreen = clampValue(((pixelBuffer >> 8) & 0xff) + (int) (fractional * errorRed));
      int clampedBlue = clampValue(((pixelBuffer) & 0xff) + (int) (fractional * errorRed));
      int newPixel = PixelGenerator.colorARGBGenerator(
              (pixelBuffer >> 24) & 0xff, clampedRed, clampedGreen, clampedBlue);
      newImage.setRGB(col, row, newPixel);
    }
  }


}
