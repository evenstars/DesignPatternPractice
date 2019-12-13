package controller;

import model.ImageGenerator;
import model.ImageProcessor;
import model.generate.CheckerBoard;
import model.generate.ColorRainbow;
import model.generate.FranceFlag;
import model.generate.GreeceFlag;
import model.generate.SwitzerlandFlag;
import model.process.BlurFilterProcessing;
import model.process.DitheringTransformerProcessing;
import model.process.GreyscaleTransformerProcessing;
import model.process.MosaicImage;
import model.process.SepiaToneTransformerProcessing;
import model.process.SharpenFilterProcessing;

/**
 * Factory class implements the ways to get professor and generator.
 */
public class SingleOpeControllerFactory extends SingleOperationControllerImpl {

  @Override
  ImageProcessor createProcessor(String[] commands) {
    ImageProcessor processor = null;
    String operation = commands[0].toUpperCase();
    if (operation.equals("MOSAIC")) {
      if (commands.length != 2) {
        throw new IllegalArgumentException("Error command.");
      }
      int seedNums = Integer.valueOf(commands[1]);
      processor = new MosaicImage(seedNums, tempImage);
    } else if (operation.equals("DITHER")) {
      if (commands.length != 1) {
        throw new IllegalArgumentException("Error command.");
      }
      processor = new DitheringTransformerProcessing(tempImage);
    } else if (operation.equals("GREYSCALE")) {
      if (commands.length != 1) {
        throw new IllegalArgumentException("Error command.");
      }
      processor = new GreyscaleTransformerProcessing(tempImage);
    } else if (operation.equals("SEPIA")) {
      if (commands.length != 1) {
        throw new IllegalArgumentException("Error command.");
      }
      processor = new SepiaToneTransformerProcessing(tempImage);
    } else if (operation.equals("BLUR")) {
      if (commands.length != 1) {
        throw new IllegalArgumentException("Error command.");
      }
      processor = new BlurFilterProcessing(tempImage);
    } else if (operation.equals("SHARPEN")) {
      if (commands.length != 1) {
        throw new IllegalArgumentException("Error command.");
      }
      processor = new SharpenFilterProcessing(tempImage);
    } else {
      throw new IllegalArgumentException("no such processor");
    }
    return processor;
  }

  @Override
  ImageGenerator createGenerator(String[] commands) {
    ImageGenerator generator = null;
    String operation = commands[0].toUpperCase();
    if (operation.equals("RAINBOW")) {
      if (commands.length != 5) {
        throw new IllegalArgumentException("Error command.");
      }
      int width = Integer.valueOf(commands[1]);
      int height = Integer.valueOf(commands[2]);
      int stripeSize = Integer.valueOf(commands[3]);
      boolean isHorizontal = Boolean.valueOf(commands[4]);
      generator = new ColorRainbow(width, height, stripeSize, isHorizontal);
    } else if (operation.equals("CHECKERBOARD")) {
      if (commands.length != 3) {
        throw new IllegalArgumentException("Error command.");
      }
      int size = Integer.valueOf(commands[1]);
      int squareSize = Integer.valueOf(commands[2]);
      generator = new CheckerBoard(size, squareSize);
    } else if (operation.equals("FLAG")) {
      if (commands.length != 4) {
        throw new IllegalArgumentException("Error command.");
      }
      String country = commands[1];
      int width = Integer.valueOf(commands[2]);
      int height = Integer.valueOf(commands[3]);
      if (country.equals("FRANCE")) {
        generator = new FranceFlag(width, height);
      } else if (country.equals("GREECE")) {
        generator = new GreeceFlag(width, height);
      } else if (country.equals("SWITZERLAND")) {
        generator = new SwitzerlandFlag(width, height);
      }
    } else {
      throw new IllegalArgumentException("no such generator");
    }
    return generator;
  }
}
