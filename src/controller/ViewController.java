package controller;

import java.awt.image.BufferedImage;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.UIManager;

import model.Image;
import model.ImageModel;
import model.computergenerated.CGModel;
import util.ImageUtil;
import view.ImageView;
import view.ViewCaller;


/**
 * View controller calls the ImageView model to load the GUI
 * and calls the respective model that are inputted by user.
 */
public class ViewController extends AbstractController {

  private Image loadedImage;
  private Image outputImage;

  /**
   * Calls the Image view controller.
   * Based on the user given input, the corresponding model is invoked,
   * and the output image is given back.
   *
   * @throws Exception illegal argument exception will be thrown.
   */
  @Override
  public void run() throws Exception {
    ImageView.setDefaultLookAndFeelDecorated(false);
    ImageView imageView = new ImageView();

    imageView.setCallBack(new ViewCaller() {
      @Override
      public void callback(String filePath, String[] commands) {
        try {
          imageView.setMainImage(handleCommand(commands));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

      @Override
      public void loadAndSave(String filePath, String command) throws Exception {
        handleLoadAndSave(filePath, command);
      }
    });

    imageView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    imageView.setVisible(true);

    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  }


  /************************* PRIVATE METHODS. **********************************/

  private void handleLoadAndSave(String inputFilePath, String command) throws Exception {

    if (command.equals("load")) {
      if (inputFilePath == null) {
        throw new IllegalArgumentException("Please enter a valid file path");
      }

      loadedImage = ImageUtil.getImage(inputFilePath);

    } else if (command.equals("save")) {
      if (outputImage == null) {
        throw new Exception("No image is loaded yet to save. ");
      }

      boolean isSuccess = ImageUtil.writeImageToFile(outputImage, inputFilePath);
      System.out.println(inputFilePath + " written " + isSuccess);
    }
  }

  private BufferedImage handleCommand(String[] commands) throws Exception {

    String command = commands[0];
    Function<String[], ImageModel> imageModelCommandFunction =
            imageModelCommands.getOrDefault(command, null);
    Function<String[], CGModel> cgModelCommandFunction =
            cgModelCommands.getOrDefault(command, null);

    if (imageModelCommandFunction != null) {
      ImageModel imageModel = imageModelCommandFunction.apply(commands);
      outputImage = imageModel.apply(loadedImage);

    } else if (cgModelCommandFunction != null) {
      CGModel cgModel = cgModelCommandFunction.apply(commands);
      outputImage = cgModel.generate();
    } else {
      throw new IllegalArgumentException("Invalid commands passed.");
    }

    return ImageUtil.getBufferedImage(outputImage);

  }
}
