package controller;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.ButtonListener;
import view.InteractiveView;
import view.InteractiveViewImpl;

/**
 * Implementation class, which could set up listener according to given view and executor.
 */
public class InteractiveControllerImpl implements InteractiveController {

  /**
   * View of the controller.
   */
  private InteractiveView view;

  /**
   * Executor, which could execute single command.
   */
  private SingleOperationController executor;

  /**
   * Constructor who takes a view and executor as its component variables. If the arguments are
   * null, throw exception.
   *
   * @param view     given view used to interactive with users
   * @param executor executor which could execute single command
   */
  public InteractiveControllerImpl(InteractiveView view, SingleOperationController executor) {
    if (view == null || executor == null) {
      throw new IllegalArgumentException("View and executor cannot be null.");
    }
    this.view = view;
    this.executor = executor;
    configViewListener();
  }

  /**
   * Helper function for process image function that could execute command and exhibit image, if
   * there is exception, remind user about that.
   *
   * @param command command of process
   */
  private void processHelper(String command) {
    InteractiveViewImpl realView = (InteractiveViewImpl) view;
    try {
      BufferedImage image = executor.executeOperation(command);
      view.exhibitImage(image);
    } catch (IllegalArgumentException e) {
      JOptionPane.showMessageDialog(realView, e.getMessage(),
              "Error"
              , JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void configViewListener() {
    ButtonListener listener = new ButtonListener();
    //assembly listener
    //load
    listener.setButtonClickAction("LOAD", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        final JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & GIF Images", "jpg", "gif");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog((InteractiveViewImpl) view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String path = f.getAbsolutePath();
          String command = "load" + ' ' + path;
          try {
            BufferedImage image = executor.executeOperation(command);
            view.exhibitImage(image);
          } catch (Exception e) {
            JOptionPane.showMessageDialog(realView, e.getMessage(),
                    "Error"
                    , JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

    // save
    listener.setButtonClickAction("SAVE", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        final JFileChooser fchooser = new JFileChooser(".");
        int retvalue = fchooser.showSaveDialog((InteractiveViewImpl) view);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          String path = f.getAbsolutePath();
          JLabel fileSaveDisplay = new JLabel("File path will appear here");
          fileSaveDisplay.setText(f.getAbsolutePath());
          String command = "SAVE" + " " + path;
          try {
            BufferedImage image = executor.executeOperation(command);
          } catch (Exception e) {
            JOptionPane.showMessageDialog(realView, e.getMessage(),
                    "Error"
                    , JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });

    // process
    // blur
    listener.setButtonClickAction("BLUR", () -> processHelper("BLUR"));
    // sharpen
    listener.setButtonClickAction("SHARPEN", () -> processHelper("SHARPEN"));
    // greyscale
    listener.setButtonClickAction("GREYSCALE", () -> processHelper("GREYSCALE"));
    // sepia
    listener.setButtonClickAction("SEPIA", () -> processHelper("SEPIA"));
    // dither
    listener.setButtonClickAction("DITHER", () -> processHelper("DITHER"));
    // mosaic // notice that this has more arguments.
    listener.setButtonClickAction("MOSAIC", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        realView.activeMosaicDialog();
      }
    });
    // mosaic Ok
    listener.setButtonClickAction("MOSAIC_OK", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        StringBuilder command = new StringBuilder("MOSAIC ");
        command.append(realView.getSeedNums());
        try {
          BufferedImage image = executor.executeOperation(command.toString());
          view.exhibitImage(image);
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(realView, e.getMessage(),
                  "Error"
                  , JOptionPane.ERROR_MESSAGE);
        }
        realView.clearMosaicDialog();
      }
    });

    //generate
    //flag
    listener.setButtonClickAction("FLAG", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        realView.activeFlagDialog();
      }
    });
    //flag ok
    listener.setButtonClickAction("FLAG_OK", new Runnable() {
      @Override
      public void run() {
        StringBuilder command = new StringBuilder();
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        command.append("FLAG ");
        command.append(realView.getFlagCountry());
        command.append(' ');
        int width = realView.getFlagWidth();
        int height = realView.getFlagHeight();
        command.append(width + " " + height);
        try {
          BufferedImage image = executor.executeOperation(command.toString());
          view.exhibitImage(image);
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(realView, e.getMessage(), "somethine wrong"
                  , JOptionPane.ERROR_MESSAGE);
        }
        realView.clearFlagArg();
      }
    });

    //rainbow
    listener.setButtonClickAction("RAINBOW", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        realView.activeRainbowDialog();
      }
    });

    listener.setButtonClickAction("RAINBOW_OK", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        StringBuilder command = new StringBuilder();
        command.append("RAINBOW ");
        command.append(realView.getRainbowWidth());
        command.append(' ');
        command.append(realView.getRainbowHeight());
        command.append(' ');
        command.append(realView.getRainbowStripe());
        command.append(' ');
        command.append(realView.getIsHorizontal());
        try {
          BufferedImage image = executor.executeOperation(command.toString());
          realView.exhibitImage(image);
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(realView, e.getMessage(), "somethine wrong"
                  , JOptionPane.ERROR_MESSAGE);
        }
        realView.clearRainbowDialog();
      }
    });

    //checherboard
    listener.setButtonClickAction("BOARD", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        realView.activeCheckerboard();
      }
    });

    listener.setButtonClickAction("CHECKER_OK", new Runnable() {
      @Override
      public void run() {
        InteractiveViewImpl realView = (InteractiveViewImpl) view;
        StringBuilder command = new StringBuilder();
        command.append("CHECKERBOARD ");
        command.append(realView.getCheckerSize());
        command.append(' ');
        command.append(realView.getCheckerSquareSize());
        try {
          BufferedImage image = executor.executeOperation(command.toString());
          realView.exhibitImage(image);
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(realView, e.getMessage(), "somethine wrong"
                  , JOptionPane.ERROR_MESSAGE);
        }
        realView.clearChecker();
      }
    });

    view.addButtonListener(listener);
  }
}
