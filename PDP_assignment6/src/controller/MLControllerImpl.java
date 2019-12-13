package controller;


import model.MLModel;
import view.MLView;

/**
 * Implementation class for MLController.
 */
public class MLControllerImpl implements MLController {

  /**
   * Given view component.
   */
  private MLView mlView;

  /**
   * Given model component.
   */
  private MLModel mlModel;

  /**
   * Constructor, initializing the view and model, which cannot be null.
   *
   * @param mlView  given view
   * @param mlModel given model
   */
  public MLControllerImpl(MLView mlView, MLModel mlModel) {
    if (mlView == null || mlModel == null) {
      throw new IllegalArgumentException("Model and view cannot be null.");
    }
    this.mlView = mlView;
    this.mlModel = mlModel;
  }

  @Override
  public void calculate() {
    mlModel.initialize(mlView.input());
    mlView.display(mlModel.fit());
  }
}
