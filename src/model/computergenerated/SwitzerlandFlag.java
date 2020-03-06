package model.computergenerated;


import model.Image;
import util.ImageUtil;

/**
 * Generates switzerland flag in the predefined proportions.
 */
public class SwitzerlandFlag implements CGModel {

  private int imageSize;
  private int imageProportion = 32;
  private final int white = (255 << 24) | (255 << 16) | (255 << 8) | 255;
  private final int red = (255 << 24) | (255 << 16);

  /**
   * Initializes the image size and should be multiple of given proportion.
   *
   * @param imageSize size of the image, user input.
   */
  public SwitzerlandFlag(int imageSize) {
    if (imageSize % imageProportion != 0) {
      throw new IllegalArgumentException("Flag size should be multiple of 32 ");
    }
    this.imageSize = imageSize;
  }

  /**
   * Generates the switzerland flag with the predefined proportions.
   *
   * @return the switzerland flag.
   */
  @Override
  public Image generate() {
    int[][] result = new int[imageSize][imageSize];

    int color;
    double proportion = imageSize >> 5;

    for (int i = 0; i < imageSize; i++) {
      for (int j = 0; j < imageSize; j++) {
        if ((proportion * 6 < i && i < 14 * proportion
                && j > proportion * 13 && j < 19 * proportion)
                || (i > proportion * 13 && i < 19 * proportion && j > proportion * 6
                && j < 26 * proportion)
                || (i > proportion * 18 && i < 26 * proportion && j > proportion * 13
                && j < 19 * proportion)) {
          color = white;
        } else {
          color = red;
        }
        result[j][i] = color;
      }
    }
    return new Image("swiss.png", "png", result, ImageUtil.IMAGE_TYPE,
            imageSize, imageSize);
  }
}
