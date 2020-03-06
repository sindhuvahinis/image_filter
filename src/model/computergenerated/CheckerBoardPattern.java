package model.computergenerated;

import model.Image;
import util.ImageUtil;

/**
 * CheckerBoardPattern generates the chess pattern image.
 */
public class CheckerBoardPattern implements CGModel {

  private static final int WHITE_RGB_COLOR = (255 << 24) | (255 << 16) | (255 << 8) | 255;
  private static final int BLACK_RGB_COLOR = (255 << 24);

  private int squareSize;
  private int imageSize;

  private final int numberOfSquares = 8;

  /**
   * Constructor initializes the square size and image size.
   *
   * @param squareSize - size of the each square.
   */
  public CheckerBoardPattern(int squareSize) {
    this.squareSize = squareSize;
    this.imageSize = numberOfSquares * squareSize;
  }

  /**
   * Generates the chess board pattern image.
   *
   * @return checkerboard image.
   */
  @Override
  public Image generate() {
    int[][] result = new int[imageSize][imageSize];
    CheckBoardColor checkBoardColor = CheckBoardColor.WHITE;
    for (int i = 0; i < imageSize; i++) {
      checkBoardColor = checkBoardColor.getNextColor(i, squareSize);
      for (int j = 0; j < imageSize; j++) {
        checkBoardColor = checkBoardColor.getNextColor(j, squareSize);
        result[i][j] = checkBoardColor.getRgbValue();
      }
    }

    return new Image("", "png", result,
            ImageUtil.IMAGE_TYPE, imageSize, imageSize);
  }

  /**
   * This enum represents the colors in the checkerboard. This enum is accessible only to the
   * CheckerBoard class.
   */
  private enum CheckBoardColor {
    WHITE(WHITE_RGB_COLOR),
    BLACK(BLACK_RGB_COLOR);

    private int rgbValue;

    CheckBoardColor(int rgbValue) {
      this.rgbValue = rgbValue;
    }

    private int getRgbValue() {
      return rgbValue;
    }

    private CheckBoardColor getNextColor(int index, int squareSize) {
      if (index % squareSize != 0) {
        return this;
      }
      return equals(BLACK) ? WHITE : BLACK;
    }
  }
}
