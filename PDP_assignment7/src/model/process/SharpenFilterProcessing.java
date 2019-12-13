package model.process;

import java.awt.image.BufferedImage;

/**
 * The SharpenFilterProcessing class extends AbstractImageProcessorWithFilter and offer
 * the process method to process the given image using sharpen Filter kernel.
 */
public class SharpenFilterProcessing extends AbstractImageProcessorWithFilter {
  SharpenFilter sharpenFilter;

  /**
   * Constructor with given image as input that use constructor of
   * AbstractImageProcessorWithFilter class and initialize a Sharpen Filter kernel.
   */
  public SharpenFilterProcessing(BufferedImage bufferedImage) {
    super(bufferedImage);
    sharpenFilter = new SharpenFilter();
  }

  @Override
  public BufferedImage process() {
    double[][] filter = sharpenFilter.getFilter();
    return processWithFilter(filter);
  }
}
