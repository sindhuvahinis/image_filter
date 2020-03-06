package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.function.Function;

import model.Image;
import model.ImageModel;
import model.computergenerated.CGModel;
import util.ImageUtil;

/**
 * Command controller takes the input file and runs it to execute the command in the file.
 */
public class CommandController extends AbstractController {
  private Image loadedImage;
  private Image outputImage;
  private String scriptFilePath;

  /**
   * Constructor takes the image file path.
   * @param scriptFilePath script file path
   */
  public CommandController(String scriptFilePath) {
    this.scriptFilePath = scriptFilePath;
  }

  /**
   * Handles the commands given in the script and does the corresponding operations.
   * @throws Exception exception thrown if the commands in the script are inputted wrongly.
   */
  @Override
  public void run() throws Exception {
    handleCommands();
  }

  /************************* PRIVATE METHODS. **********************************/

  private void handleCommands() throws Exception {

    File file = new File(scriptFilePath);
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

    String line;
    while ((line = bufferedReader.readLine()) != null) {
      String[] commands = line.split("\\s+");
      String command = commands[0];
      Function<String[], ImageModel> imageModelCommandFunction =
              imageModelCommands.getOrDefault(command, null);
      Function<String[], CGModel> cgModelCommandFunction =
              cgModelCommands.getOrDefault(command, null);

      if (command.equals("load")) {
        String fileName = commands[1];
        loadedImage = ImageUtil.getImage(fileName);

      } else if (command.equals("save")) {
        if (outputImage == null) {
          throw new IllegalArgumentException("No image is loaded yet to save. ");
        }

        String fileName = commands[1];
        boolean isSuccess = ImageUtil.writeImageToFile(outputImage, fileName);
        System.out.println(fileName + " written " + isSuccess);

      } else {
        if (loadedImage == null) {
          throw new IllegalArgumentException("Please load image");
        }

        if (imageModelCommandFunction != null) {
          ImageModel imageModel = imageModelCommandFunction.apply(commands);
          outputImage = imageModel.apply(loadedImage);

        } else if (cgModelCommandFunction != null) {
          CGModel cgModel = cgModelCommandFunction.apply(commands);
          outputImage = cgModel.generate();
        } else {
          throw new IllegalArgumentException("Invalid commands passed.");
        }
      }
    }
  }
}
