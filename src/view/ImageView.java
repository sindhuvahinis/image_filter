package view;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.BoxLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import util.ApplicationConstants;

/**
 * This model is for view of this application.
 * It takes care of the GUI and the corresponding operation user does on it.
 */
public class ImageView extends JFrame implements ActionListener, ItemListener,
        ListSelectionListener {

  private JPanel mainPanel;
  private JLabel imageLabel;

  private String filePath;
  private String outputFilePath;

  private ViewCaller callBack;
  private Icon imageIcon;

  /**
   * Constructor constructs the main panel and the screen of the GUI.
   * It initializes the buttons and toolbars.
   */
  public ImageView() {
    super();
    // main title
    setTitle("Image View");
    setSize(500, 500);

    // main panel settings
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainPanel.setVisible(true);
    add(mainPanel);

    // menu bar
    JMenuBar menuBar = new JMenuBar();

    JMenu filterMenu = new JMenu("Filter");
    JMenu transformMenu = new JMenu("Transform");
    JMenu patternsMenu = new JMenu("Patterns");
    JMenu flagsMenu = new JMenu("Flags");

    menuBar.add(filterMenu);
    menuBar.add(transformMenu);
    menuBar.add(patternsMenu);
    menuBar.add(flagsMenu);
    menuBar.setSelected(filterMenu);

    setJMenuBar(menuBar);

    // Defining custom buttons for filter
    JButton blurButton = getToolBarButton("  Blur  ", "res/assets/blur.png");
    JButton sharpenButton = getToolBarButton("Sharpen", "res/assets/sharpen.png");
    JButton mosaicButton = getToolBarButton("Mosaic", "res/assets/mosaic.png");

    JToolBar filterToolbar = new JToolBar();
    filterToolbar.add(blurButton);
    filterToolbar.addSeparator();
    filterToolbar.add(sharpenButton);
    filterToolbar.addSeparator();
    filterToolbar.add(mosaicButton);

    filterMenu.add(filterToolbar);

    // Defining custom buttons for transform
    JButton greyscaleButton = getToolBarButton("Grey", "res/assets/greyscale.png");
    JButton sepia = getToolBarButton("Sepia", "res/assets/sepia.png");
    JButton dither = getToolBarButton("Dither", "res/assets/dither.png");

    JToolBar transformToolbar = new JToolBar();
    transformToolbar.add(greyscaleButton);
    transformToolbar.addSeparator();
    transformToolbar.add(sepia);
    transformToolbar.addSeparator();
    transformToolbar.add(dither);

    transformMenu.add(transformToolbar);

    // Defining custom buttons for patterns
    JButton rainbow = getToolBarButton("Rainbow", "res/assets/rainbow.png");
    JButton checkerboard = getToolBarButton("Checkerboard", "res/assets/checkerboard.png");

    JToolBar patternToolbar = new JToolBar();
    patternToolbar.add(rainbow);
    patternToolbar.addSeparator();
    patternToolbar.add(checkerboard);

    patternsMenu.add(patternToolbar);

    // Defining custom buttons for flags
    JButton franceFlag = getToolBarButton("France", "res/assets/flags.png");
    JButton greeceFlag = getToolBarButton("Greece", "res/assets/flags.png");
    JButton switzerlandFlag = getToolBarButton("Switzerland", "res/assets/flags.png");

    JToolBar flagsToolbar = new JToolBar();
    flagsToolbar.add(franceFlag);
    flagsToolbar.addSeparator();
    flagsToolbar.add(greeceFlag);
    flagsToolbar.addSeparator();
    flagsToolbar.add(switzerlandFlag);

    flagsMenu.add(flagsToolbar);

    // image view
    imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon(filePath));
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));

    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Display"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imagePanel.add(imageScrollPane);

    mainPanel.add(imagePanel);

    // button view
    JPanel buttonLayout = new JPanel();
    buttonLayout.setLayout(new BoxLayout(buttonLayout, BoxLayout.LINE_AXIS));

    JButton loadButton = new JButton("Load");
    loadButton.setActionCommand("load");
    loadButton.addActionListener(this);

    JButton saveButton = new JButton("Save");
    saveButton.setActionCommand("save");
    saveButton.addActionListener(this);

    buttonLayout.add(loadButton);
    buttonLayout.add(saveButton);

    mainPanel.add(buttonLayout);

  }

  /**
   * Setter to set the callback local object.
   * @param callBack accepts the callback object of ViewCaller.
   */
  public void setCallBack(ViewCaller callBack) {
    this.callBack = callBack;
  }

  /**
   * Sets the image in the main panel of the screen.
   * @param filePath input file path
   */
  public void setMainImage(String filePath) {
    this.filePath = filePath;
    this.imageIcon = new ImageIcon(filePath);
    imageLabel.setIcon(imageIcon);
  }

  /**
   * Sets the image in the main panel of the screen.
   * @param image image to be displayed on the screen.
   */
  public void setMainImage(BufferedImage image) {
    this.imageIcon = new ImageIcon(image);
    imageLabel.setIcon(imageIcon);
  }

  /**
   * When any action is performed on the button, the corresponding operation will be
   * done in it.
   * It will give the corresponding command to the controller and the controller calls
   * the model.
   * @param event - action event that is performed by the user.
   */
  @Override
  public void actionPerformed(ActionEvent event) {
    switch (event.getActionCommand()) {
      case ApplicationConstants.LOAD_STR:
        loadImage();
        try {
          if (filePath != null) {
            callBack.loadAndSave(filePath, ApplicationConstants.LOAD_STR);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
        break;
      case ApplicationConstants.SAVE_STR:
        if (saveImageValidation()) {
          saveImage();
          try {
            callBack.loadAndSave(outputFilePath, ApplicationConstants.SAVE_STR);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
        break;
      case ApplicationConstants.BLUR_STR:
        if (filePathValidation(filePath)) {
          callBack.callback(filePath, new String[]{ApplicationConstants.BLUR_STR});
        }
        break;
      case ApplicationConstants.MOSAIC_STR:
        if (filePathValidation(filePath)) {
          callBack.callback(filePath,
              new String[]{ApplicationConstants.MOSAIC_STR, "seeds=" + getMosaicSeeds()});
        }
        break;
      case ApplicationConstants.SHARPEN_STR:
        if (filePathValidation(filePath)) {
          callBack.callback(filePath, new String[]{ApplicationConstants.SHARPEN_STR});
        }
        break;
      case ApplicationConstants.GREY_STR:
        if (filePathValidation(filePath)) {
          callBack.callback(filePath, new String[]{ApplicationConstants.GREY_STR});
        }
        break;
      case ApplicationConstants.SEPIA_STR:
        if (filePathValidation(filePath)) {
          callBack.callback(filePath, new String[]{ApplicationConstants.SEPIA_STR});
        }
        break;
      case ApplicationConstants.DITHER_STR:
        if (filePathValidation(filePath)) {
          callBack.callback(filePath, new String[]{ApplicationConstants.DITHER_STR});
        }
        break;
      case ApplicationConstants.RAINBOW_STR:
        String[] args = getRainbowOptions();
        callBack.callback(null,
            new String[]{ApplicationConstants.RAINBOW_STR, "" + "width=" + args[0],
                         "other=" + args[1], "orientation=" + args[2].toLowerCase()});
        break;
      case ApplicationConstants.CHECKERBOARD_STR:
        callBack.callback(null,
            new String[]{ApplicationConstants.CHECKERBOARD_STR, "width=" + getCheckerBoardSize()});
        break;
      case ApplicationConstants.FRANCE_STR:
        callBack.callback(null,
            new String[]{ApplicationConstants.FRANCE_STR, "width=" + getFranceWidth()});
        break;
      case ApplicationConstants.GREECE_STR:
        callBack.callback(null,
            new String[]{ApplicationConstants.GREECE_STR, "width=" + getGreeceWidth()});
        break;
      case ApplicationConstants.SWITZERLAND_STR:
        callBack.callback(null,
            new String[]{ApplicationConstants.SWITZERLAND_STR, "width=" + getSwissWidth()});
        break;
      default:
        loadImage();
        break;
    }
  }

  /**
   * Item State change method. Currently not implemented.
   * @param e event done by user.
   */
  @Override
  public void itemStateChanged(ItemEvent e) {
    // does nothing
  }

  /**
   * Value changed method. Currently not implemented.
   * @param e event done by user.
   */
  @Override
  public void valueChanged(ListSelectionEvent e) {
    // does nothing
  }

  /************************* PRIVATE METHODS. **********************************/

  private String[] getRainbowOptions() {
    JPanel rainBowDialogPanel = new JPanel();

    JLabel widthLabel = new JLabel("width/height");
    JTextField widthField = new JTextField(5);

    JLabel otherLabel = new JLabel("other");
    JTextField otherField = new JTextField(5);

    JComboBox<String> jComboBox = new JComboBox<>(new String[]{"Horizontal", "Vertical"});


    rainBowDialogPanel.add(widthLabel);
    rainBowDialogPanel.add(widthField);
    rainBowDialogPanel.add(otherLabel);
    rainBowDialogPanel.add(otherField);
    rainBowDialogPanel.add(jComboBox);

    String width = "";
    String other = "";
    String orientation = "";

    int result = JOptionPane.showConfirmDialog(mainPanel, rainBowDialogPanel);
    if (result == JOptionPane.OK_OPTION) {

      width = widthField.getText();
      other = otherField.getText();
      orientation = (String) jComboBox.getSelectedItem();
    }

    return new String[]{width, other, orientation.toLowerCase()};
  }

  private String getMosaicSeeds() {
    return (String) JOptionPane.showInputDialog(mainPanel, "Enter the number "
            + "of seeds", "Mosaic options",
            JOptionPane.PLAIN_MESSAGE, null, null, "1000");
  }

  private String getCheckerBoardSize() {
    return (String) JOptionPane.showInputDialog(mainPanel, "Enter the square size",
            "Checkerboard options", JOptionPane.PLAIN_MESSAGE,
            null, null, "50");
  }

  private String getFranceWidth() {
    return (String) JOptionPane.showInputDialog(mainPanel,
         "Enter the width of flag (In multiples of 2)",
         "Flag options", JOptionPane.PLAIN_MESSAGE, null,
         null, "300");
  }

  private String getGreeceWidth() {
    return (String) JOptionPane.showInputDialog(mainPanel,
            "Enter the width of flag (In multiples" + " of 27)",
            "Flag options", JOptionPane.PLAIN_MESSAGE, null,
            null, "540");
  }

  private String getSwissWidth() {
    return (String) JOptionPane.showInputDialog(mainPanel,
            "Enter the width of flag (In multiples" + " of 32)",
            "Flag options", JOptionPane.PLAIN_MESSAGE, null,
            null, "640");
  }

  private boolean filePathValidation(String filepath) {
    if (filepath == null) {
      JOptionPane.showMessageDialog(mainPanel, "Please load an image first!", "No Image",
              JOptionPane.ERROR_MESSAGE);
      return false;
    } else {
      return true;
    }
  }

  private boolean saveImageValidation() {
    if (imageIcon == null) {
      JOptionPane.showMessageDialog(mainPanel, "Please load an image first!", "No Image",
              JOptionPane.ERROR_MESSAGE);
      return false;
    } else {
      return true;
    }
  }

  private void saveImage() {
    JFileChooser jFileChooser = new JFileChooser(".");
    jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int returnValue = jFileChooser.showSaveDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = jFileChooser.getSelectedFile();
      String directoryPath = file.getAbsolutePath();
      outputFilePath = directoryPath;
      System.out.println(directoryPath);
    }
  }

  private void loadImage() {
    JFileChooser jFileChooser = new JFileChooser(".");
    FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image files",
            "jpg", "bmp", "png");
    jFileChooser.setFileFilter(fileNameExtensionFilter);
    jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int returnValue = jFileChooser.showSaveDialog(this);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File file = jFileChooser.getSelectedFile();
      setMainImage(file.getAbsolutePath());
    }
  }

  private JButton getToolBarButton(String label, String iconPath) {
    JButton tempButton = new JButton(label, new ImageIcon(iconPath, label));
    tempButton.setActionCommand(label.toLowerCase());
    tempButton.setRolloverEnabled(true);
    tempButton.setVerticalTextPosition(SwingConstants.BOTTOM);
    tempButton.setHorizontalTextPosition(SwingConstants.CENTER);
    tempButton.addActionListener(this);
    return tempButton;
  }
}

