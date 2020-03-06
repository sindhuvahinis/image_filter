package model.computergenerated;

import java.util.Arrays;

import model.Image;
import util.ImageUtil;

/**
 * Generates the rainbow pattern image with the user specified, width and height of the stripes.
 */
public class RainbowPattern implements CGModel {

  private static final int VIOLET_RGB_VALUE = (255 << 24) | (127 << 16) | 255;
  private static final int INDIGO_RGB_VALUE = (255 << 24) | (75 << 16) | 130;
  private static final int BLUE_RGB_VALUE = (255 << 24) | 255;
  private static final int GREEN_RGB_VALUE = (255 << 24) | (255 << 8);
  private static final int YELLOW_RGB_VALUE = (255 << 24) | (255 << 16) | (255 << 8);
  private static final int ORANGE_RGB_VALUE = (255 << 24) | (255 << 16) | (165 << 8);
  private static final int RED_RGB_VALUE = (255 << 24) | (255 << 16);

  private int imageWidth;
  private int imageHeight;
  private int stripeWidth;
  private int stripeHeight;
  private Orientation orientation;

  /**
   * Constructor initializes the width and height of the pattern, based of the orientation specified
   * by the user.
   *
   * @param widthOrHeight - width or height of the stripes.
   * @param other         - the other dimension of the stripe.
   * @param orientation   - horizontal or vertical.
   */
  public RainbowPattern(int widthOrHeight, int other, Orientation orientation) {
    this.orientation = orientation;

    if (orientation == Orientation.HORIZONTAL) {
      imageWidth = other;
      imageHeight = widthOrHeight * 7;
      stripeWidth = other;
      stripeHeight = widthOrHeight;
    } else if (orientation == Orientation.VERTICAL) {
      imageWidth = widthOrHeight * 7;
      imageHeight = other;
      stripeWidth = widthOrHeight;
      stripeHeight = other;
    }
  }

  /**
   * Generates the rainbow pattern based on the orientations.
   *
   * @return rainbow pattern image.
   */
  @Override
  public Image generate() {
    int[][] result = new int[imageHeight][imageWidth];

    int x = 0;
    if (orientation == Orientation.HORIZONTAL) {
      for (RainbowColors rainbowColors : RainbowColors.values()) {
        for (int i = 0; i < stripeHeight; i++) {
          Arrays.fill(result[x], rainbowColors.getRgbValue());
          x++;
        }
      }
    } else {
      for (RainbowColors rainbowColors : RainbowColors.values()) {
        for (int i = 0; i < stripeWidth; i++) {
          for (int j = 0; j < stripeHeight; j++) {
            result[j][x] = rainbowColors.getRgbValue();
          }
          x++;
        }
      }
    }

    return new Image("", "png",
            result, ImageUtil.IMAGE_TYPE, imageWidth, imageHeight);
  }

  /**
   * Represents the rainbow colors to create the rainbow pattern. Accessible only to rainbow pattern
   * class.
   */
  private enum RainbowColors {
    VIOLET(VIOLET_RGB_VALUE),
    INDIGO(INDIGO_RGB_VALUE),
    BLUE(BLUE_RGB_VALUE),
    GREEN(GREEN_RGB_VALUE),
    YELLOW(YELLOW_RGB_VALUE),
    ORANGE(ORANGE_RGB_VALUE),
    RED(RED_RGB_VALUE);

    private int rgbValue;

    RainbowColors(int rgbValue) {
      this.rgbValue = rgbValue;
    }

    private int getRgbValue() {
      return rgbValue;
    }
  }

}
