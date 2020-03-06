package model.computergenerated;

import model.Image;
import util.ImageUtil;

/**
 * Generates greece flag in the predefined proportions.
 */
public class GreeceFlag implements CGModel {

  private int imageWidth;
  private int imageHeight;
  private final int white = (255 << 24) | (255 << 16) | (255 << 8) | 255;
  private final int blue = (255 << 24) | (13 << 16) | (94 << 8) | 175;

  /**
   * Constructor calculates the image height based on the predefined proportions, and image width
   * given by the user.
   *
   * @param imageWidth - width of the flag.
   */
  public GreeceFlag(int imageWidth) {
    if (imageWidth % 27 != 0) {
      throw new IllegalArgumentException("flag width should be a multiple of 27");
    }
    this.imageWidth = imageWidth;
    this.imageHeight = imageWidth / 3 * 2;
  }

  /**
   * Generates the greece flag with the predefined proportions.
   *
   * @return the greece flag.
   */
  @Override
  public Image generate() {
    int[][] result = new int[imageHeight][imageWidth];
    int singleUnit = imageWidth / 27;
    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {

        if ((i < singleUnit * 4 && 4 * singleUnit <= j && j < 6 * singleUnit)
                || (2 * singleUnit <= i && i < 4 * singleUnit && 10 * singleUnit <= j
                && j < imageWidth * singleUnit)
                || (4 * singleUnit <= i && i < 6 * singleUnit && j < 10 * singleUnit)
                || (6 * singleUnit <= i && i < 10 * singleUnit && 4 * singleUnit <= j
                && j < 6 * singleUnit)
                || (6 * singleUnit <= i && i < 8 * singleUnit && 10 * singleUnit <= j
                && j < imageWidth * singleUnit)
                || (10 * singleUnit <= i && i < 12 * singleUnit) || (14 * singleUnit <= i
                && i < 16 * singleUnit)) {
          result[i][j] = white;
        } else {
          result[i][j] = blue;
        }
      }
    }
    return new Image("greece.png", "png", result, ImageUtil.IMAGE_TYPE,
            imageWidth, imageHeight);
  }
}
