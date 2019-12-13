package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A listener collector which is a map that could store multiple listener according to their
 * command.
 */
public class ButtonListener implements ActionListener {

  /**
   * Listener map store collection.
   */
  private Map<String, Runnable> buttonClickAction;

  /**
   * Constructor which could initialize the map.
   */
  public ButtonListener() {
    buttonClickAction = new HashMap<>();
  }

  /**
   * Add one entry to the map, the key is the command initialized in view. and the action the what
   * will be done after invoking the listener.
   *
   * @param key     the command access to the view component
   * @param actions the action access to the component
   */
  public void setButtonClickAction(String key, Runnable actions) {
    if (key == null || key.isEmpty() || actions == null) {
      throw new IllegalArgumentException("Illegal argument.");
    }
    buttonClickAction.put(key, actions);
  }

  /**
   * Invoke the action according to the command of the action.
   *
   * @param e action
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    Runnable action = buttonClickAction.get(e.getActionCommand());
    if (action != null) {
      action.run();
    }
  }
}
