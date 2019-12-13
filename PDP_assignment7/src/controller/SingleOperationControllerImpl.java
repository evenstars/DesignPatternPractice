package controller;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import image.ImageIOUtil;
import model.ImageGenerator;
import model.ImageProcessor;

/**
 * Class for executing single operation according to the command is IO, generate method or process
 * method. This is implemented with factory method pattern.
 */
public abstract class SingleOperationControllerImpl implements SingleOperationController {

  /**
   * Commands belonging to generate methods.
   */
  private static Set<String> generateOperations;

  /**
   * Commands belonging to process methods.
   */
  private static Set<String> processOperations;

  static {
    generateOperations = new HashSet<>();
    processOperations = new HashSet<>();
    generateOperations.add("FLAG");
    generateOperations.add("RAINBOW");
    generateOperations.add("CHECKERBOARD");
    processOperations.add("MOSAIC");
    processOperations.add("DITHER");
    processOperations.add("GREYSCALE");
    processOperations.add("SEPIA");
    processOperations.add("BLUR");
    processOperations.add("SHARPEN");
  }

  /**
   * Register a generate method.
   *
   * @param generator new generator method
   * @return whether add successfully
   */
  public static boolean registerGenerator(String generator) {
    if (generator == null || generator.isEmpty()) {
      throw new IllegalArgumentException("generator cannot be empty or null");
    }
    return generateOperations.add(generator.toUpperCase());
  }

  /**
   * Register a process method.
   *
   * @param processor new process method
   * @return whether add successfully
   */
  public static boolean registerProcessor(String processor) {
    if (processor == null || processor.isEmpty()) {
      throw new IllegalArgumentException("processor cannot be empty or null");
    }
    return generateOperations.add(processor.toUpperCase());
  }

  /**
   * Middle image between operation.
   */
  protected BufferedImage tempImage;

  /**
   * Constructor who only initialize the tempIimage to null.
   */
  public SingleOperationControllerImpl() {
    tempImage = null;
  }

  @Override
  public BufferedImage executeOperation(String command) {
    if (command == null || command.isEmpty()) {
      throw new IllegalArgumentException("Command cannot be empty or null.");
    }
    String[] parseCommand = command.split("\\s+");
    String operation = parseCommand[0].toUpperCase();
    if (operation.equals("LOAD")) {
      tempImage = loadImage(getUrl(command));
    } else if (operation.equals("SAVE")) {
      saveImage(getUrl(command));
    } else if (generateOperations.contains(operation)) {
      ImageGenerator generator = createGenerator(parseCommand);
      tempImage = generator.generate();
    } else if (processOperations.contains(operation)) {
      ImageProcessor processor = createProcessor(parseCommand);
      tempImage = processor.process();
    } else {
      throw new IllegalArgumentException("No such command.");
    }
    return tempImage;
  }

  /**
   * Get the path according to single space
   * @param command given command
   * @return url
   */
  private static String getUrl(String command){
    int spaceIdx = command.indexOf(" ");
    String url = command.substring(spaceIdx+1);
    System.out.println(url);
    return url;
  }

  /**
   * Load image from given path. If path is invalid, throw exception.
   *
   * @param path given path to load image
   * @return loaded bufferImage
   */
  private BufferedImage loadImage(String path) {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("No image input path.");
    }
    return ImageIOUtil.read(path);
  }

  /**
   * Save image from given path. If path is invalid, or tempImage is null, throw exception.
   *
   * @param path given path to save image
   * @return saved bufferImage
   */
  private boolean saveImage(String path) {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("No image output path.");
    }
    if (tempImage == null) {
      throw new IllegalArgumentException("Image is not initialized.");
    }
    String[] formatParts = path.split("\\.");
    if (formatParts.length != 2) {
      throw new IllegalArgumentException("Wrong output path.");
    }
    String format = formatParts[1];
    return ImageIOUtil.write(path, format, tempImage);
  }

  /**
   * Factory method to create processor according to commands.
   *
   * @param commands commands, first element is command name,and others are parameters
   * @return corresponding image processor
   */
  abstract ImageProcessor createProcessor(String[] commands);

  /**
   * Factory method to create generator according to commands.
   *
   * @param commands commands, first element is command name,and others are parameters
   * @return corresponding image generator
   */
  abstract ImageGenerator createGenerator(String[] commands);

  public boolean isInitialized(){
    return tempImage!=null;
  }
}
