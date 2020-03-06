package model;

/**
 * Represents the image with their file name, format and their integer array representation.
 */
public class Image {

  private String fileName;
  private final String format;
  private final int[][] data;
  private final int type;
  private final int width;
  private final int height;

  /**
   * Initialized with the user given image format and dimensions.
   *
   * @param fileName - file name of the image.
   * @param format   - format of the image.
   * @param data     - integer representation of the image.
   * @param type     - type of the image.
   * @param width    - width of the image.
   * @param height   - height of the image.
   */
  public Image(String fileName, String format, int[][] data, int type, int width, int height) {
    this.fileName = fileName;
    this.format = format;
    this.data = data;
    this.type = type;
    this.width = width;
    this.height = height;
  }

  public Image(Image image, int[][] data) {
    this(image.fileName, image.format, data, image.type, image.width, image.height);
  }

  /**
   * Returns the file name of the image.
   *
   * @return file name.
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Returns the format of the image.
   *
   * @return format.
   */
  public String getFormat() {
    return format;
  }

  /**
   * Returns the integer representation of the array.
   *
   * @return integer array.
   */
  public int[][] getData() {
    int[][] copyOfData = new int[data.length][data[0].length];
    for (int i = 0; i < data.length; i++) {
      System.arraycopy(data[i], 0, copyOfData[i], 0, data[i].length);
    }
    return copyOfData;
  }

  /**
   * Returns the type of the image.
   *
   * @return image type.
   */
  public int getType() {
    return type;
  }

  /**
   * Returns the width of the image.
   *
   * @return image width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the image.
   *
   * @return image height.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the file name of the image.
   *
   * @param fileName name of the file.
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

}
