package model.process;

import java.awt.image.BufferedImage;

import util.PixelGenerator;
import model.AbstractImageProcessor;

/**
 * The abstract class that extends AbstractImageProcessor and offer method to process
 * images using filters like Blur Filter.
 */
public abstract class AbstractImageProcessorWithFilter extends AbstractImageProcessor {
  protected int filterRows;
  protected int filterCols;
  // distance from the top to center with out counting center line.
  protected int topExCenterDist;
  protected int leftExCenterDist;

  /**
   * Construction method with given image uses the one in AbstractImageProcessor class.
   *
   * @param bufferedImage the given image.
   */
  public AbstractImageProcessorWithFilter(BufferedImage bufferedImage) {
    super(bufferedImage);
  }

  /**
   * The method helps to process image with given filter like Blur Filter.
   *
   * @param filter the given filter.
   * @return image processed by the given filter.
   */
  protected BufferedImage processWithFilter(double[][] filter) {
    BufferedImage resultImage = new BufferedImage(imageWidth, imageHeight,
            image.getType());
    filterRows = filter.length;
    filterCols = filter[0].length;
    topExCenterDist = filterRows / 2;
    leftExCenterDist = filterCols / 2;
    for (int imageRowIndex = 0; imageRowIndex < imageHeight; imageRowIndex++) {
      for (int imageColIndex = 0; imageColIndex < imageWidth; imageColIndex++) {
        // do not need substract (topExCenterDist - 1), because the definition of topExCenterDist.
        int loopInImageRow = imageRowIndex - topExCenterDist;
        int loopInImageCol = imageColIndex - leftExCenterDist;
        double pixelRed = 0;
        double pixelGreen = 0;
        double pixelBlue = 0;

        for (int kernelRow = 0; kernelRow < filterRows; kernelRow++) {
          for (int kernelCol = 0; kernelCol < filterCols; kernelCol++) {
            if (kernelCol + loopInImageCol < 0 || kernelCol + loopInImageCol >= imageWidth
                    || kernelRow + loopInImageRow < 0
                    || kernelRow + loopInImageRow >= imageHeight) {
              continue;
            }
            int pixel = image.getRGB(kernelCol + loopInImageCol,
                    kernelRow + loopInImageRow);
            double red = (pixel >> 16) & 0xff;
            double green = (pixel >> 8) & 0xff;
            double blue = (pixel) & 0xff;
            double filterCorrespondingVal = filter[kernelRow][kernelCol];
            red *= filterCorrespondingVal;
            green *= filterCorrespondingVal;
            blue *= filterCorrespondingVal;
            pixelRed += red;
            pixelGreen += green;
            pixelBlue += blue;
          }
        }
        pixelBlue = clampValue(pixelBlue);
        pixelRed = clampValue(pixelRed);
        pixelGreen = clampValue(pixelGreen);
        int newPixel = PixelGenerator.colorARGBGenerator(
                (image.getRGB(imageColIndex, imageRowIndex) >> 24) & 0xff,
                (int) pixelRed, (int) pixelGreen, (int) pixelBlue);
        resultImage.setRGB(imageColIndex, imageRowIndex, newPixel);
      }
    }
    return resultImage;
  }
}
