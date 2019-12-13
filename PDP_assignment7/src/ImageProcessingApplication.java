import controller.BatchOperationController;
import controller.BatchOperationControllerImpl;
import controller.InteractiveController;
import controller.InteractiveControllerImpl;
import controller.SingleOpeControllerFactory;
import controller.SingleOperationController;
import view.CommandParser;
import view.FileCommandParser;
import view.InteractiveView;
import view.InteractiveViewImpl;

/**
 * Driven class.
 */
public class ImageProcessingApplication {

  /**
   * Driven main function. The comments and commented codes illustrate how to use the driver
   * function.
   *
   * @param args no argument
   */
  public static void main(String[] args) {
    if (args.length == 0)
      return;
    if (args[0].equals("-interactive")) {
      InteractiveView view = new InteractiveViewImpl();
      InteractiveController controller = new InteractiveControllerImpl(view,
              new SingleOpeControllerFactory());
    } else if (args[0].equals("-script")) {
      String path = args[1];
      CommandParser parser = new FileCommandParser(path);
      SingleOperationController executor = new SingleOpeControllerFactory();
      BatchOperationController controller = new BatchOperationControllerImpl(parser, executor);
      controller.executeBatch();
    }
    System.out.println("finished");
    //Change config.properties file to modify the saved place.
    //AbstractImageGenerator greeceFlag = new GreeceFlag(300,300);
    //greeceFlag.generate();
    //greeceFlag.writeImage("BMP");
    //System.out.print("finished");

    //Change config.properties file to modify the saved place.
    //AbstractImageGenerator franceFlag = new FranceFlag(300,300);
    //franceFlag.generate();
    //franceFlag.writeImage("BMP");
    //System.out.print("finished");

    //Change config.properties file to modify the saved place.
    //AbstractImageGenerator switzerlandFlag = new SwitzerlandFlag(300,300);
    //switzerlandFlag.generate();
    //switzerlandFlag.writeImage("BMP");
    //System.out.print("finished");

    //When we need load image:
    //BufferedImage bufferedImage = ImageIOUtil.read();

    //Change config.properties file to modify the input image and saved place.
    //int i = 0;
    //while(i<5){
    //ImageProcessor processor = new BlurFilterProcessing(bufferedImage);
    //bufferedImage = processor.process();
    //i++;
    //}

    //Change config.properties file to modify the input image and saved place.
    //ImageProcessor processor = new SharpenFilterProcessing(bufferedImage);
    //bufferedImage = processor.process();
    //processor = new SharpenFilterProcessing(bufferedImage);
    //bufferedImage = processor.process();

    //Change config.properties file to modify the input image and saved place.
    //ImageProcessor processor = new GreyscaleTransformerProcessing(bufferedImage);
    //processor.process();

    //Change config.properties file to modify the input image and saved place.
    //ImageProcessor processor = new MosaicImage(1000,bufferedImage);
    //BufferedImage image = processor.process();
    //String format = ConfigReader.getConfigReader().getProperty("outputFormat");
    //ImageIOUtil.write(format, image);
    //System.out.println("finished");

//    CommandParser parser = new FileCommandParser(args[0]);
//    SingleOperationController executor = new SingleOpeControllerFactory();
//    BatchOperationController controller = new BatchOperationControllerImpl(parser,executor);
//    controller.executeBatch();
//    SwingFeaturesFrame frame = new SwingFeaturesFrame();
//    frame.setVisible(true);
  }
}
