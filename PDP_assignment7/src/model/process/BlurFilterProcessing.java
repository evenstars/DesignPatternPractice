package model.process;

import java.awt.image.BufferedImage;

/**
 * The BlurFilterProcessing class extends AbstractImageProcessorWithFilter and offer the
 * process method to process the given image using blur Filter kernel.
 */
public class BlurFilterProcessing extends AbstractImageProcessorWithFilter {
  protected BlurFilter blurFilter;

  /**
   * Constructor with given image as input that use constructor of
   * AbstractImageProcessorWithFilter class and initialize a Blur Filter kernel.
   */
  public BlurFilterProcessing(BufferedImage bufferedImage) {
    super(bufferedImage);
    blurFilter = new BlurFilter();
  }

  @Override
  public BufferedImage process() {
    double[][] filter = blurFilter.getFilter();
    return processWithFilter(filter);
  }

}
