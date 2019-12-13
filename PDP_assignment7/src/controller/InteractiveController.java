package controller;

/**
 * Controller for interactive way, which should config the listener of view firstly.
 */
public interface InteractiveController {

  /**
   * Config the listeners in the view. Each listener implies how the controller and view will
   * collaborate together like generating and executing one command. If in that process, there is
   * exception, then remind user about that.
   */
  void configViewListener();
}
