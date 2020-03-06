package model.filter;

import model.ImageModel;

/**
 * Abstract class that implements image model for the filers.
 */
public abstract class AbstractImageModel implements ImageModel {

  /**
   * Applies the filter on the given image on each channels separately.
   *
   * @param image  - image given by user.
   * @param filter - filter to be applied.
   * @return integer array representation of image.
   */
  public int[][] apply(int[][] image, double[][] filter) {
    int[][] result = new int[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int redChannel = (int) getPixelValue(image, filter, i, j, 16);
        redChannel = redChannel > 255 ? 255 : Math.max(redChannel, 0);
        int greenChannel = (int) getPixelValue(image, filter, i, j, 8);
        greenChannel = greenChannel > 255 ? 255 : Math.max(greenChannel, 0);
        int blueChannel = (int) getPixelValue(image, filter, i, j, 0);
        blueChannel = blueChannel > 255 ? 255 : Math.max(blueChannel, 0);

        int a = (image[i][j] >> 24) & 0xff;
        int p = (a << 24) | (redChannel << 16) | (greenChannel << 8) | blueChannel;
        result[i][j] = p;
      }
    }
    return result;
  }

  /***************************** PRIVATE METHODS. *************************************/

  private double getPixelValue(int[][] array, double[][] filter, int x, int y, int shiftBy) {
    double sum = 0;
    int bound = filter.length / 2;
    for (int i = x - bound, filterI = 0; i <= x + bound; i++, filterI++) {
      for (int j = y - bound, filterJ = 0; j <= y + bound; j++, filterJ++) {
        if (i >= 0 && j >= 0 && i < array.length && j < array[0].length) {
          double f = filter[filterI][filterJ];
          int s = (array[i][j] >> shiftBy);
          sum = sum + ((s & 0xff) * f);
        }
      }
    }
    return sum;
  }

}
