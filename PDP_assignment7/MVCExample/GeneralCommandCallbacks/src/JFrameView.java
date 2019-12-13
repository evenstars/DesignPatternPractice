import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JFrameView extends JFrame implements IView {
  private JLabel display;
  private JButton echoButton, exitButton,toggleButton;
  private JTextField input;

  public JFrameView(String caption, Controller controller) {
    super(caption);

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setResizable(false);
//		this.setMinimumSize(new Dimension(300,300));


    this.setLayout(new FlowLayout());

    display = new JLabel("To be displayed");
    //label = new JLabel(new ImageIcon("Jellyfish.JPG"));


    this.add(display);

    //the textfield
    input = new JTextField(10);
    this.add(input);

    //echobutton
    echoButton = new JButton("Echo");
    echoButton.setActionCommand("Echo Button");
    this.add(echoButton);


    //togglebutton
    toggleButton = new JButton("Toggle color");
    toggleButton.setActionCommand("Toggle color button");
    this.add(toggleButton);

    //exit button
    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    this.add(exitButton);


    pack();
    setVisible(true);

  }


  /**
   * Accept the set of callbacks from the controller, and hook up as needed
   * to various things in this view.
   * @param f the set of feature callbacks as a Features object
   */
  @Override
  public void setFeatures(Features f) {
    //process input is tied to the echo button
    echoButton.addActionListener(l->f.processInput(input.getText()));
    //exit program is tied to the exit button
    exitButton.addActionListener(l->f.exitProgram());
    //toggle color is tied to a toggle button. Originally this functionality was
    //exposed only by a key press. Having a set of callbacks to call gives
    // the view full control over which UI elements to map to which features.
    toggleButton.addActionListener(l->f.toggleColor());

    //toggle color, make upper case and restore case are tied to the keyboard.
    this.addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
          case 't':
            f.toggleColor();
            break;
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case 'c':
            f.makeUppercase();
            break;
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
          case 'c':
            f.restoreCase();
            break;
        }
      }
    });
  }


  /*
      In order to make this frame respond to keyboard events, it must be within strong focus.
      Since there could be multiple components on the screen that listen to keyboard events,
      we must set one as the "currently focussed" one so that all keyboard events are
      passed to that component. This component is said to have "strong focus".

      We do this by first making the component focusable and then requesting focus to it.
      Requesting focus makes the component have focus AND removes focus from whoever had it
      before.
       */
  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void toggleColor() {
    if (this.display.getForeground().equals(Color.RED))
      this.display.setForeground(Color.BLACK);
    else
      this.display.setForeground(Color.RED);
  }


  @Override
  public void setEchoOutput(String s) {
    display.setText(s);
  }


  @Override
  public void clearInputString() {
    input.setText("");
  }


}
