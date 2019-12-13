package controller;

import org.junit.Test;

import view.InteractiveViewImpl;

/**
 * Test class for InteractiveControllerImpl class.
 */
public class InteractiveControllerImplTest {

  /**
   * Test helper variable.
   */
  private InteractiveControllerImpl controller;

  /**
   * Test if input view is null. whether the controller throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullViewController(){
    controller = new InteractiveControllerImpl(null,new SingleOpeControllerFactory());
  }

  /**
   * Test if input executor is null, whether the controller throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullExecutorController(){
    controller = new InteractiveControllerImpl(new InteractiveViewImpl(),null);
  }

}