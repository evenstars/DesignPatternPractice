import com.sun.javafx.scene.control.skin.IntegerFieldSkin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The controller now implements the Features interface. This means each of
 * those functions will give control to the controller.
 */
public class Controller implements Features {
  private IModel model;
  private IView view;

  public Controller(IModel m) {
    model = m;
  }

  public void setView(IView v) {
    view = v;
    //give the feature callbacks to the view
   view.setFeatures(this);
  }



  @Override
  public void processInput(String text) {
    model.setString(text);

    //clear input textfield
    view.clearInputString();
    //finally echo the string in view
    text = model.getString();
    view.setEchoOutput(text);

    //set focus back to main frame so that keyboard events work
    view.resetFocus();

  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void toggleColor() {
    view.toggleColor();
  }

  @Override
  public void makeUppercase() {
    String text = model.getString();
    text = text.toUpperCase();
    view.setEchoOutput(text);
  }

  @Override
  public void restoreCase() {
    String text = model.getString();
    view.setEchoOutput(text);
  }
}