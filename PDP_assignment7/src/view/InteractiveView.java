package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * View that could add button listener to the buttons in the view and exhibit image.
 */
public interface InteractiveView {

  /**
   * Exhibit the newest image.
   *
   * @param image newest image
   */
  void exhibitImage(BufferedImage image);

  /**
   * Add all the buttons the listener.
   *
   * @param listener given listener
   */
  void addButtonListener(ActionListener listener);
}
