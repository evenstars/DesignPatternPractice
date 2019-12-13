package view;

import java.util.List;

import util.InputUtil;

/**
 * Abstract class implements the MLview, which could parse the input.
 */
public abstract class MLViewAbstract implements MLView {

  /**
   * The tool for parsing.
   */
  private InputUtil inputUtil;

  /**
   * Initialize the parsing tool.
   *
   * @param inputUtil parsing tool instance
   */
  public MLViewAbstract(InputUtil inputUtil) {
    this.inputUtil = inputUtil;
  }

  @Override
  public List<double[]> input() {
    return inputUtil.parse();
  }
}
