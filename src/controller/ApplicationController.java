package controller;

/**
 * Represents the controller of the application, which calls the respective models.
 */
public interface ApplicationController {

  /**
   * Calls the respective models based on the commands provided by the user.
   * @throws Exception exception
   */
  void run() throws Exception;
}
