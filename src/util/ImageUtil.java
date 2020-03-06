package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Image;

/**
 * Util class for the images.
 */
public class ImageUtil {

  /**
   * Image type for storing the image.
   */
  public static final int IMAGE_TYPE = BufferedImage.TYPE_4BYTE_ABGR;

  /**
   * Get the path of the file and convert them into image object.
   *
   * @param path path of the file.
   * @return Image object.
   * @throws IOException while reading the file.
   */
  public static Image getImage(String path) throws IOException {
    File file = new File(path);
    BufferedImage image = ImageIO.read(file);
    int width = image.getWidth();
    int height = image.getHeight();
    int[][] result = new int[height][width];

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        result[row][col] = image.getRGB(col, row);
      }
    }
    String fileName = file.getName();
    return new Image(fileName, getExtension(fileName), result, image.getType(),
            image.getWidth(), image.getHeight());
  }

  /**
   * Write the image to the file.
   *
   * @param imagePojo Image object.
   * @param filePath  path to store the output file.
   * @throws IOException while writing the file.
   */
  public static boolean writeImageToFile(Image imagePojo, String filePath) throws IOException {

    BufferedImage image = getBufferedImage(imagePojo);

    File outputFile = new File(filePath);

    return ImageIO.write(image, imagePojo.getFormat(), outputFile);

  }

  /**
   * Generates the buffered image.
   * @param imagePojo image object.
   * @return return the image in buffered image format.
   */
  public static BufferedImage getBufferedImage(Image imagePojo) {
    int[][] result = imagePojo.getData();
    BufferedImage image = new BufferedImage(imagePojo.getWidth(), imagePojo.getHeight(),
            imagePojo.getType());

    for (int y = 0; y < result.length; y++) {
      for (int x = 0; x < result[0].length; x++) {
        image.setRGB(x, y, result[y][x]);
      }
    }
    return image;
  }

  /*************************** PRIVATE METHODS. **************************/
  private static String getExtension(String fileName) {
    String[] temp = fileName.split("\\.");
    return temp[temp.length - 1];
  }
}