package model.process;

import java.awt.image.BufferedImage;

import util.PixelGenerator;
import model.AbstractImageProcessor;

/**
 * The abstract class that extends AbstractImageProcessor and offer method to process
 * images using color transformers like Greyscale Transformer.
 */
public abstract class AbstractImageProcessorWithColorTransformer
        extends AbstractImageProcessor {

  protected int transformerRows;
  protected int transformerCols;

  /**
   * Construction method with given image uses the one in AbstractImageProcessor class.
   *
   * @param bufferedImage the given image.
   */
  public AbstractImageProcessorWithColorTransformer(BufferedImage bufferedImage) {
    super(bufferedImage);
  }

  /**
   * The method helps to process images using any color transformer.
   *
   * @param transformer the given color transformer.
   * @return image processed by color transformer.
   */
  protected BufferedImage processWithColorTransformer(double[][] transformer) {
    BufferedImage resultImage = new BufferedImage(imageWidth, imageHeight,
            image.getType());
    transformerRows = transformer.length;
    transformerCols = transformer[0].length;
    for (int imageRowIndex = 0; imageRowIndex < imageHeight; imageRowIndex++) {
      for (int imageColIndex = 0; imageColIndex < imageWidth; imageColIndex++) {
        double pixelRed = 0;
        double pixelGreen = 0;
        double pixelBlue = 0;
        int pixel = image.getRGB(imageColIndex, imageRowIndex);
        int alpha = (pixel >> 24) & 0xff;
        double red = (pixel >> 16) & 0xff;
        double green = (pixel >> 8) & 0xff;
        double blue = (pixel) & 0xff;
        pixelRed = red * transformer[0][0] + green * transformer[0][1]
                + blue * transformer[0][2];
        pixelGreen = red * transformer[1][0] + green * transformer[1][1]
                + blue * transformer[1][2];
        pixelBlue = red * transformer[2][0] + green * transformer[2][1]
                + blue * transformer[2][2];
        pixelBlue = clampValue(pixelBlue);
        pixelRed = clampValue(pixelRed);
        pixelGreen = clampValue(pixelGreen);
        int newPixel = PixelGenerator.colorARGBGenerator(alpha, (int) pixelRed,
                (int) pixelGreen, (int) pixelBlue);
        resultImage.setRGB(imageColIndex, imageRowIndex, newPixel);
      }
    }
    return resultImage;
  }

}
