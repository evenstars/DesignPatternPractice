package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

/**
 * This class implements InteractiveView and extends JFrame and will add button listener
 * to the buttons in the JFrame and exhibit image on the JFrame.
 */
public class InteractiveViewImpl extends JFrame implements InteractiveView {

  private JLabel image;

  private JMenuItem[] fileOpeItem;

  private JMenuItem[] processItem;
  private JMenuItem[] generateItem;

  private JDialog flagDialog;
  private JButton flagOk;
  private JComboBox<String> flagCombox;
  private String[] flags;
  private JFormattedTextField inputFlagWidth;
  private JFormattedTextField inputFlagHeight;

  private JDialog rainbowDialog;
  private JButton rainbowOk;
  private JFormattedTextField rainbowWidth;
  private JFormattedTextField rainbowHeight;
  private JFormattedTextField rainbowSize;
  private JCheckBox rainbowHorizontal;

  private JDialog checkerboardDialog;
  private JFormattedTextField checkerSize;
  private JFormattedTextField checkerSquareSize;
  private JButton checkerOK;

  private JDialog mosaicDialog;
  private JFormattedTextField seedNums;
  private JButton mosaicOK;

  /**
   * Constructor method that will initialize the JFrame, set up menu bar, configure
   * dialogs that will be used and display image.
   */
  public InteractiveViewImpl() {
    //initialize component
    super();
    setTitle("Image genius");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //menu bar
    setUpMenuBar();

    //flag dialog
    setUpFlagDialog();

    //rainbow dialog
    setUpRainbowDialog();

    //check board dialog
    setUpCheckerboard();

    //image
    setUpImageDisplay();

    // mosaic dialog
    setUpMosaicDialog();

    setVisible(true);
  }

  /**
   * This method will help to initialize Mosaic Dialog.
   */
  private void setUpMosaicDialog() {
    mosaicDialog = new JDialog(this, "mosaic argument");
    mosaicDialog.setSize(250, 150);
    Container container = mosaicDialog.getContentPane();
    mosaicDialog.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
    JLabel inputSeed = new JLabel("input seed number");

    seedNums = new JFormattedTextField(getNumericFormat());
    seedNums.setColumns(12);
    JPanel groupSeed = new JPanel();
    groupSeed.add(inputSeed);
    groupSeed.add(seedNums);
    mosaicOK = new JButton("ok");
    mosaicOK.setActionCommand("MOSAIC_OK");
    container.add(groupSeed);
    container.add(mosaicOK);
    mosaicDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * This method will help to initialize Checkerboard Dialog.
   */
  private void setUpCheckerboard() {
    checkerboardDialog = new JDialog(this, "checker board argument");
    checkerboardDialog.setSize(250, 150);
    Container container = checkerboardDialog.getContentPane();
    checkerboardDialog.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
    JLabel inputSize = new JLabel("input size");
    JLabel inputSquareSize = new JLabel("square size");
    checkerSize = new JFormattedTextField(getNumericFormat());
    checkerSize.setColumns(12);
    checkerSquareSize = new JFormattedTextField(getNumericFormat());
    checkerSquareSize.setColumns(12);

    JPanel groupSize = new JPanel();
    groupSize.add(inputSize);
    groupSize.add(checkerSize);
    JPanel groupSquareSize = new JPanel();
    groupSquareSize.add(inputSquareSize);
    groupSquareSize.add(checkerSquareSize);

    checkerOK = new JButton("ok");
    checkerOK.setActionCommand("CHECKER_OK");
    container.add(groupSize);
    container.add(groupSquareSize);
    container.add(checkerOK);
    checkerboardDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Getter method helps to get size of checker.
   *
   * @return int value of size of checker board.
   */
  public int getCheckerSize() {
    return Integer.valueOf(checkerSize.getText());
  }

  /**
   * Getter method helps to get size of checker square.
   *
   * @return int value of size of checker board.
   */
  public int getCheckerSquareSize() {
    return Integer.valueOf(checkerSquareSize.getText());
  }

  /**
   * helper method to clear entire checker board.
   */
  public void clearChecker() {
    checkerboardDialog.dispose();
  }

  /**
   * Helper method to help initialize checker board.
   */
  public void activeCheckerboard() {
    checkerboardDialog.setVisible(true);
  }

  /**
   * This method will help to initialize Rainbow Dialog.
   */
  private void setUpRainbowDialog() {
    rainbowDialog = new JDialog(this, "rainbow argument");
    rainbowDialog.setSize(250, 225);
    Container container = rainbowDialog.getContentPane();
    rainbowDialog.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

    JLabel inputWidth = new JLabel("input width");
    JLabel inputHeight = new JLabel("input height");
    JLabel inputStripeSize = new JLabel("stripe size");
    rainbowWidth = new JFormattedTextField(getNumericFormat());
    rainbowWidth.setColumns(12);
    rainbowHeight = new JFormattedTextField(getNumericFormat());
    rainbowHeight.setColumns(12);
    rainbowSize = new JFormattedTextField(getNumericFormat());
    rainbowSize.setColumns(12);
    rainbowHorizontal = new JCheckBox("is horizontal?");
    rainbowHorizontal.setSelected(false);
    rainbowOk = new JButton("ok");
    rainbowOk.setActionCommand("RAINBOW_OK");

    JPanel groupWidth = new JPanel();
    groupWidth.add(inputWidth);
    groupWidth.add(rainbowWidth);

    JPanel groupHeight = new JPanel();
    groupHeight.add(inputHeight);
    groupHeight.add(rainbowHeight);

    JPanel groupStripe = new JPanel();
    groupStripe.add(inputStripeSize);
    groupStripe.add(rainbowSize);

    container.add(groupWidth);
    container.add(groupHeight);
    container.add(groupStripe);
    container.add(rainbowHorizontal);
    container.add(rainbowOk);
    rainbowDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Getter method helps to get number of input seeds during mosaic processing.
   *
   * @return int value of number of input seeds.
   */
  public int getSeedNums() {
    return Integer.valueOf(seedNums.getText().replaceAll(",", ""));
  }

  /**
   * Getter method helps to get height of rainbow.
   *
   * @return int value of height of rainbow.
   */
  public int getRainbowHeight() {
    return Integer.valueOf(rainbowHeight.getText());
  }

  /**
   * Getter method helps to get width of rainbow.
   *
   * @return int value of width of rainbow.
   */
  public int getRainbowWidth() {
    return Integer.valueOf(rainbowWidth.getText());
  }

  /**
   * Getter method helps to get width of each stripe in rainbow.
   *
   * @return int value of width of each stripe in rainbow.
   */
  public int getRainbowStripe() {
    return Integer.valueOf(rainbowSize.getText());
  }

  /**
   * Getter method helps to get if the rainbow is horizontal.
   *
   * @return true if the rainbow is horizontal, false otherwise.
   */
  public boolean getIsHorizontal() {
    return rainbowHorizontal.isSelected();
  }

  /**
   * Helper method clears the entire Rainbow Dialog.
   */
  public void clearRainbowDialog() {
    rainbowDialog.dispose();
  }

  /**
   * Helper method initializes the Rainbow Dialog.
   */
  public void activeRainbowDialog() {
    rainbowDialog.setVisible(true);
  }

  /**
   * Helper method initializes the Mosaic Dialog.
   */
  public void activeMosaicDialog() {
    mosaicDialog.setVisible(true);
  }

  /**
   * Helper method clears the entire mosaic Dialog.
   */
  public void clearMosaicDialog() {
    mosaicDialog.dispose();
  }

  /**
   * This method will help to initialize Flag Dialog.
   */
  private void setUpFlagDialog() {
    flagDialog = new JDialog(this, "flag argument");
    flagDialog.setSize(250, 200);
    flagDialog.setLayout(new FlowLayout());
    Container container = flagDialog.getContentPane();

    //combox
    JLabel flagCountry = new JLabel("choose a country");
    flagCombox = new JComboBox<>();
    flags = new String[]{"France", "Greece", "Switzerland"};
    for (int i = 0; i < flags.length; i++) {
      flagCombox.addItem(flags[i]);
    }
    flagCombox.setActionCommand("FLAG_CHOOSE");

    //input width
    JLabel flagWidth = new JLabel("input width");
    inputFlagWidth = new JFormattedTextField(getNumericFormat());
    inputFlagWidth.setColumns(12);

    //height
    JLabel flagHeight = new JLabel("input height");
    inputFlagHeight = new JFormattedTextField(getNumericFormat());
    inputFlagHeight.setColumns(12);

    //ok button
    flagOk = new JButton("ok");
    flagOk.setActionCommand("FLAG_OK");
    flagOk.setSize(20, 10);

    JPanel groupCountry = new JPanel();
    JPanel groupWidth = new JPanel();
    JPanel groupHeight = new JPanel();
    groupCountry.add(flagCountry);
    groupCountry.add(flagCombox);
    groupWidth.add(flagWidth);
    groupWidth.add(inputFlagWidth);
    groupHeight.add(flagHeight);
    groupHeight.add(inputFlagHeight);
    container.add(groupCountry);
    container.add(groupWidth);
    container.add(groupHeight);
    container.add(flagOk);
    flagDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  /**
   * Getter method helps to get the nationality of the desired flag.
   *
   * @return the nationality of the flag.
   */
  public String getFlagCountry() {
    int idx = flagCombox.getSelectedIndex();
    String country = flags[idx].toUpperCase();
    System.out.println(country);
    return country;
  }

  /**
   * Helper method clears the entire flag dialog.
   */
  public void clearFlagArg() {
    flagDialog.dispose();
  }

  /**
   * Getter method helps to get the width of the desired flag.
   *
   * @return the width of the flag.
   */
  public int getFlagWidth() {
    int res = Integer.valueOf(inputFlagWidth.getText());
    System.out.println(res);
    return res;
  }

  /**
   * Getter method helps to get the height of the desired flag.
   *
   * @return the height of the flag.
   */
  public int getFlagHeight() {
    int res = Integer.valueOf(inputFlagHeight.getText());
    System.out.println(res);
    return res;
  }

  /**
   * Helper method helps to initialize country flag.
   */
  public void activeFlagDialog() {
    flagDialog.setVisible(true);
  }

  /**
   * Helper method helps to get input from the interactive view.
   *
   * @return the gotten input.
   */
  private NumberFormatter getNumericFormat() {
    NumberFormat intFormat = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(intFormat);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setAllowsInvalid(false);
    return formatter;
  }

  /**
   * Helper method helps to set up the menu bar and add items to the menu bar.
   */
  private void setUpMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu fileOpe = new JMenu("File");
    String[] fileOpeList = new String[]{"load", "save"};
    fileOpeItem = new JMenuItem[fileOpeList.length];
    for (int i = 0; i < fileOpeList.length; i++) {
      fileOpeItem[i] = new JMenuItem(fileOpeList[i]);
      fileOpeItem[i].setActionCommand(fileOpeList[i].toUpperCase());
      fileOpe.add(fileOpeItem[i]);
    }
    menuBar.add(fileOpe);

    JMenu processOpen = new JMenu("process image");
    String[] processOpenList = new String[]{"blur", "sharpen", "greyscale", "sepia",
            "dither", "mosaic"};
    processItem = new JMenuItem[processOpenList.length];
    for (int i = 0; i < processOpenList.length; i++) {
      processItem[i] = new JMenuItem(processOpenList[i]);
      processItem[i].setActionCommand(processOpenList[i].toUpperCase());
      processOpen.add(processItem[i]);
    }
    menuBar.add(processOpen);
    setJMenuBar(menuBar);

    JMenu generateOpe = new JMenu("generate image");
    String[] generateOpeList = new String[]{"flag", "rainbow", "board"};
    generateItem = new JMenuItem[generateOpeList.length];
    for (int i = 0; i < generateOpeList.length; i++) {
      generateItem[i] = new JMenuItem(generateOpeList[i]);
      generateItem[i].setActionCommand(generateOpeList[i].toUpperCase());
      generateOpe.add(generateItem[i]);
    }
    menuBar.add(generateOpe);
    setJMenuBar(menuBar);
  }

  /**
   * Helper method helps to set up the items to display image.
   */
  private void setUpImageDisplay() {
    Container container = this.getContentPane();
    image = new JLabel();
    JPanel imagePanel = new JPanel(new BorderLayout());
    imagePanel.setBorder(BorderFactory.createTitledBorder("result image"));
    image.setText("please load or generate a picture first");
    JScrollPane imageScroll = new JScrollPane(image);
    image.setHorizontalAlignment(JLabel.CENTER);
    imageScroll.setPreferredSize(new Dimension(500, 500));
    imagePanel.add(imageScroll, BorderLayout.CENTER);
    container.add(imagePanel);
  }

  @Override
  public void exhibitImage(BufferedImage image) {
    this.image.setText("");
    this.image.setIcon(new ImageIcon(image));
  }

  @Override
  public void addButtonListener(ActionListener listener) {
    for (JMenuItem fileOpe : fileOpeItem) {
      fileOpe.addActionListener(listener);
    }
    for (JMenuItem processOpen : processItem) {
      processOpen.addActionListener(listener);
    }
    mosaicOK.addActionListener(listener);

    for (JMenuItem generateOpe : generateItem) {
      generateOpe.addActionListener(listener);
    }
    flagOk.addActionListener(listener);
    rainbowOk.addActionListener(listener);
    checkerOK.addActionListener(listener);
  }
}
