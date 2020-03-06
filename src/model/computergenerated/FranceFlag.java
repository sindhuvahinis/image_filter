package model.computergenerated;

import model.Image;
import util.ImageUtil;

/**
 * Generates france flag in the predefined proportions.
 */
public class FranceFlag implements CGModel {

  private final double heightProportion = 2.0;
  private final double widthProportion = 3.0;
  private int width;
  private int height;

  private final int blue = (255 << 24) | (85 << 8) | 164;
  private final int white = (255 << 24) | (255 << 16) | (255 << 8) | 255;
  private final int red = (255 << 24) | (239 << 16) | (65 << 8) | 53;

  /**
   * Constructor that takes width and calculates the height from the predefined proportions. Width
   * should be the mutliple of 3, otherwise throws exception.
   *
   * @param width - width of the france flag.
   */
  public FranceFlag(int width) {
    if (width % widthProportion != 0) {
      throw new IllegalArgumentException("Width should be the multiple of 3.");
    }

    this.width = width;
    this.height = (int) (heightProportion / widthProportion * width);
  }

  /**
   * Generates the france flag in the given proportions.
   *
   * @return france flag image
   */
  @Override
  public Image generate() {
    int[][] result = new int[height][width];

    for (int i = 0; i < width; i++) {
      int color = i < width / 3 ? blue : i < (width * (2.0 / 3.0)) ? white : red;
      for (int j = 0; j < height; j++) {
        result[j][i] = color;
      }
    }
    return new Image("france.png", "png", result,
            ImageUtil.IMAGE_TYPE, width, height);
  }

}
