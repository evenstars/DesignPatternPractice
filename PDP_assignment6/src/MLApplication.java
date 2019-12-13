import controller.MLController;
import controller.MLControllerImpl;
import model.LinearRegressionModel;
import model.MLModel;
import view.LinearRegressionView;
import view.MLView;

/**
 * Entrance of the program.
 */
public class MLApplication {

  /**
   * Entrance method.
   *
   * @param args args
   */
  public static void main(String[] args) {
    MLView linearRegressionView = new LinearRegressionView();
    MLModel linearRegressionModel = new LinearRegressionModel();
    MLController mlController = new MLControllerImpl(linearRegressionView, linearRegressionModel);
    mlController.calculate();
    System.out.println("Finished");
  }
}
