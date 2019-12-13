package model.process;

/**
 * This class implements Filter interface and offer a way to get a sharpen filter kernel.
 */
public class SharpenFilter implements Filter {
  private double[][] sharpenFilter;

  /**
   * Default constructor that initialize a sharpen filter kernel.
   */
  public SharpenFilter() {
    sharpenFilter = new double[5][5];
    int filterRows = sharpenFilter.length;
    int filterCols = sharpenFilter[0].length;
    for (int row = 0; row < filterRows; row++) {
      for (int col = 0; col < filterCols; col++) {
        if (row == 0 || col == 0 || row == filterRows - 1 || col == filterCols - 1) {
          sharpenFilter[row][col] = (double) (-1) / 8;
        } else {
          if (row == 2 && col == 2) {
            sharpenFilter[row][col] = 1;
          } else {
            sharpenFilter[row][col] = (double) 1 / 4;
          }
        }
      }
    }
  }

  @Override
  public double[][] getFilter() {
    return sharpenFilter;
  }
}
