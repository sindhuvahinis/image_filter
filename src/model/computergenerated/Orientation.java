package model.computergenerated;

/**
 * Orientation represents whether the image/pattern has to be, vertical or horizontal.
 */
public enum Orientation {
  HORIZONTAL("horizontal"),
  VERTICAL("vertical");

  private String displayString;

  /**
   * Takes the display string.
   *
   * @param displayString - display string of the orientation.
   */
  Orientation(String displayString) {
    this.displayString = displayString;
  }

  /**
   * Getter method for the display string of the orientation.
   *
   * @return display string.
   */
  public String getDisplayString() {
    return displayString;
  }

  /**
   * gets the corresponding orientation for the given string.
   *
   * @param displayString - input string representation of the orientation.
   * @return corresponding enum to input string.
   */
  public static Orientation getOrientation(String displayString) {
    if (displayString.equals(HORIZONTAL.displayString)) {
      return HORIZONTAL;
    } else if (displayString.equals(VERTICAL.displayString)) {
      return VERTICAL;
    } else {
      throw new IllegalArgumentException("Invalid argument passed.");
    }
  }
}
