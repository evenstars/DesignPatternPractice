package model.process;

import java.awt.image.BufferedImage;

/**
 * The GreyscaleTransformerProcessing class extends AbstractImageProcessorWithColorTransformer
 * and offer the process method to process the given image using greyscale color
 * transformer matrix.
 */
public class GreyscaleTransformerProcessing
        extends AbstractImageProcessorWithColorTransformer {

  protected GreyscaleTransformer greyscaleTrans;


  /**
   * Constructor with given input image that use constructor of
   * AbstractImageProcessorWithColorTransformer class and initialize a greyscale color
   * transformer matrix.
   */
  public GreyscaleTransformerProcessing(BufferedImage bufferedImage) {
    super(bufferedImage);
    greyscaleTrans = new GreyscaleTransformer();
  }

  @Override
  public BufferedImage process() {
    double[][] transformer = greyscaleTrans.getTransformer();
    return processWithColorTransformer(transformer);
  }
}
