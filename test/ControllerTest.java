import org.junit.Test;

import controller.ApplicationController;
import controller.CommandController;
import controller.ViewController;

/**
 * Includes testcases for the controllers.
 */
public class ControllerTest {

  @Test
  public void testScriptWithValidCommands() throws Exception {
    ApplicationController applicationController =
            new CommandController("/Users/darshangowda/IdeaProjects/Assignment7/res/commands" +
                    "/input-commands.txt");
    try {
      applicationController.run();
    } catch(Exception ex) {
      ex.printStackTrace();
      throw new Exception("Exception occurred while reading and executing the script");
    }
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOperationWithoutLoadingImage() throws Exception {
    ApplicationController applicationController = new CommandController("/Users/darshangowda/IdeaProjects/Assignment7/test/invalid-input-commands-2.txt");
    applicationController.run();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testOperationSaveWithoutAnyImage() throws Exception {
    ApplicationController applicationController = new CommandController("/Users/darshangowda/IdeaProjects/Assignment7/test/invalid-input-commands-2.txt");
    applicationController.run();
  }

  @Test
  public void testViewControllerInitializing() throws Exception {
    ApplicationController applicationController = new ViewController();
    try {
    applicationController.run();
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new Exception("Exception occurred while initialing the GUI");
    }
  }
}
