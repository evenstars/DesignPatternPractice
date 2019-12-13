package view;

import java.io.IOException;
import java.util.List;

import config.ConfigReader;
import util.DisplayDataBag;
import util.ImagePlotter;
import util.InputFileUtil;

/**
 * Class extends the MLViewAbstract class which could display the result.
 */
public class LinearRegressionView extends MLViewAbstract {

  /**
   * Plot tool.
   */
  private ImagePlotter imagePlotter;

  /**
   * Constructor which parses the input data.
   */
  public LinearRegressionView() {
    super(new InputFileUtil(ConfigReader.getInstance().getProperty("inputPath")));
    imagePlotter = new ImagePlotter();
  }

  @Override
  public void display(DisplayDataBag result) {
    imagePlotter.setWidth(500);
    imagePlotter.setHeight(500);

    imagePlotter.setDimensions(-400, 400, -400, 400);
    List bagResult = result.getBag();
    double[] pars = (double[]) bagResult.get(0);
    double a = pars[0], b = pars[1], c = pars[2];
    List<double[]> points = (List<double[]>) bagResult.get(1);
    for (double[] point : points) {
      imagePlotter.addPoint((int) point[0], (int) point[1]);
    }
    double x1 = -400;
    double x2 = 400;
    double y1 = (-a * x1 - c) / b;
    double y2 = (-a * x2 - c) / b;
    imagePlotter.addLine((int) Math.round(x1), (int) Math.round(y1), (int) Math.round(x2),
            (int) Math.round(y2));
    try {
      imagePlotter.write(ConfigReader.getInstance().getProperty("outputPath"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
