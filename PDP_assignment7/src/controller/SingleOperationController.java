package controller;

import java.awt.image.BufferedImage;

/**
 * Interface for executing one single operation with a command.
 */
public interface SingleOperationController {

  /**
   * Execute one single operation with one command in string format.
   *
   * @param command string format of command
   * @return bufferedImage after executing the command
   */
  BufferedImage executeOperation(String command);
}
