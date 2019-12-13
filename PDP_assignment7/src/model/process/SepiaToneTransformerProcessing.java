package model.process;

import java.awt.image.BufferedImage;

/**
 * The SepiaToneTransformerProcessing class extends AbstractImageProcessorWithColorTransformer
 * and offer the process method to process the given image using sepia tone color
 * transformer matrix.
 */
public class SepiaToneTransformerProcessing
        extends AbstractImageProcessorWithColorTransformer {

  protected SepiaToneTransformer sepiaTrans;

  /**
   * Constructor with given input image that use constructor of
   * AbstractImageProcessorWithColorTransformer class and initialize a sepia tone color
   * transformer matrix.
   */
  public SepiaToneTransformerProcessing(BufferedImage bufferedImage) {
    super(bufferedImage);
    sepiaTrans = new SepiaToneTransformer();
  }

  @Override
  public BufferedImage process() {
    double[][] transformer = sepiaTrans.getTransformer();
    return processWithColorTransformer(transformer);
  }

}
