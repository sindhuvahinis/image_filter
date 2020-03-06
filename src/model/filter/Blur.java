package model.filter;

import model.Image;

/**
 * Blurs the image given by the user. Extends the AbstractImageModel, an abstract class.
 */
public class Blur extends AbstractImageModel {
  private double[][] filter;

  /**
   * Initialized the filter for blurring the image.
   */
  public Blur() {
    filter = new double[][]{
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}
    };
  }

  /**
   * Applies the filter to the user given image.
   *
   * @param image - user given image.
   * @return blurred image.
   */
  @Override
  public Image apply(Image image) {
    int[][] res = super.apply(image.getData(), filter);
    return new Image(image.getFileName(), image.getFormat(), res, image.getType(),
            image.getWidth(), image.getHeight());
  }
}
