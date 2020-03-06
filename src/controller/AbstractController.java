package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import model.ImageModel;
import model.colortransformation.Dither;
import model.colortransformation.GreyScale;
import model.colortransformation.SepiaTone;
import model.computergenerated.CGModel;
import model.computergenerated.CheckerBoardPattern;
import model.computergenerated.FranceFlag;
import model.computergenerated.GreeceFlag;
import model.computergenerated.Orientation;
import model.computergenerated.RainbowPattern;
import model.computergenerated.SwitzerlandFlag;
import model.filter.Blur;
import model.filter.Mosaic;
import model.filter.Sharpen;

/**
 * Abstract controller calls the respective controller based on the user command.
 */
public abstract class AbstractController implements ApplicationController {

  /**
   * The respective model objects to be called for generating computer generated images are stored
   * in this map.
   */
  protected static final Map<String, Function<String[], CGModel>> cgModelCommands = new HashMap<>();


  /**
   * The respective model objects to be called for filtering images are stored in this map.
   */
  protected static final Map<String, Function<String[], ImageModel>> imageModelCommands =
          new HashMap<>();

  static {
    imageModelCommands.put("blur", command -> new Blur());
    imageModelCommands.put("sharpen", command -> new Sharpen());
    imageModelCommands.put("mosaic", commands -> {
      String[] arguments = commands[1].split("=");
      return new Mosaic(Integer.parseInt(arguments[1]));
    });
    imageModelCommands.put("grey", commands -> new GreyScale());
    imageModelCommands.put("dither", commands -> new Dither());
    imageModelCommands.put("sepia", commands -> new SepiaTone());

    cgModelCommands.put("checkerboard", commands -> {
      String[] arguments = commands[1].split("=");
      int width = Integer.parseInt(arguments[1]);
      return new CheckerBoardPattern(width);
    });
    cgModelCommands.put("rainbow", commands -> {

      String[] widthArgument = commands[1].split("=");
      String[] otherArgument = commands[2].split("=");
      String[] orientationArgument = commands[3].split("=");
      Orientation orientation = Orientation.getOrientation(orientationArgument[1]);

      return new RainbowPattern(Integer.parseInt(widthArgument[1]),
              Integer.parseInt(otherArgument[1]), orientation);
    });
    cgModelCommands.put("france", commands -> {
      String[] arguments = commands[1].split("=");
      int width = Integer.parseInt(arguments[1]);
      return new FranceFlag(width);
    });
    cgModelCommands.put("greece", commands -> {
      String[] arguments = commands[1].split("=");
      int width = Integer.parseInt(arguments[1]);
      return new GreeceFlag(width);
    });
    cgModelCommands.put("switzerland", commands -> {
      String[] arguments = commands[1].split("=");
      int width = Integer.parseInt(arguments[1]);
      return new SwitzerlandFlag(width);
    });
  }

  /**
   * The main function takes care of handling calling the appropriate controllers either the script
   * or interactive environment.
   *
   * @param args - arguments
   * @throws Exception - illegal argument or file format exception could be thrown.
   */
  public static void main(String[] args) throws Exception {
    String command = args[0];

    Map<String, Function<String, ApplicationController>> knownCommands = new HashMap<>();
    knownCommands.put("-script", CommandController::new);
    knownCommands.put("-interactive", filePath -> new ViewController());

    Function<String, ApplicationController> functionCommand = knownCommands.getOrDefault(command,
            null);

    if (functionCommand == null) {
      throw new IllegalArgumentException("Invalid command name");
    }

    String filePath = validateAndGetFilePath(args, command);

    ApplicationController applicationController = functionCommand.apply(filePath);

    // the controller to be run
    applicationController.run();

  }

  /************************* PRIVATE METHODS. **********************************/

  private static String validateAndGetFilePath(String[] args, String command) {
    String filePath = null;
    if (command.equals("-script")) {
      if (args.length < 2) {
        throw new IllegalArgumentException("File path is needed");
      }
      filePath = args[1];
    }
    return filePath;
  }
}
