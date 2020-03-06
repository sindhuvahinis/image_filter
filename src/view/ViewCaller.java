package view;

/**
 * View Caller calls the corresponding callback methods in the controller.
 */
public interface ViewCaller {
  /**
   * Calls back the controller to do certain operations of the image.
   * @param filePath filepath of the image
   * @param commands command to be done.
   */
  void callback(String filePath, String[] commands);

  /**
   * Load and saves the image. Calls the operation in controller.
   * @param filePath file path of the image.
   * @param commands command to be done.
   * @throws Exception exception on illegal argument.
   */
  void loadAndSave(String filePath, String commands) throws Exception;
}
