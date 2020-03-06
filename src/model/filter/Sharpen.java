package model.filter;

import model.Image;

/**
 * Sharpens the image given by the user. Extends the AbstractImageModel, an abstract class.
 */
public class Sharpen extends AbstractImageModel {
  private double[][] filter;

  /**
   * Initializes the filter for sharpening the image.
   */
  public Sharpen() {
    filter = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125},
    };
  }

  /**
   * Applies the sharpen filter to the image.
   *
   * @param image - user given image.
   * @return sharpened image.
   */
  @Override
  public Image apply(Image image) {
    int[][] res = super.apply(image.getData(), filter);
    return new Image(image.getFileName(), image.getFormat(), res, image.getType(),
            image.getWidth(), image.getHeight());
  }
}
