package util;

/**
 * Util class for the color of the pixels in the images.
 */
public class ColorUtil {

  /**
   * Return the red channel of the given pixel.
   *
   * @param pixel pixel input.
   * @return integer value of red channel.
   */
  public static int getRedChannel(int pixel) {
    return (pixel >> 16) & 0xff;
  }

  /**
   * Return the green channel of the given pixel.
   *
   * @param pixel pixel input
   * @return green channel - integer value.
   */
  public static int getGreenChannel(int pixel) {
    return (pixel >> 8) & 0xff;
  }

  /**
   * Return the blue channel of the given pixel.
   *
   * @param pixel pixel input
   * @return blue channel - integer value.
   */
  public static int getBlueChannel(int pixel) {
    return pixel & 0xff;
  }

  /**
   * Return the alpha channel of the given pixel.
   *
   * @param pixel pixel input
   * @return alpha channel - integer value.
   */
  public static int getAlphaChannel(int pixel) {
    return (pixel >> 24) & 0xff;
  }

  /**
   * Returns the combined rbg value.
   *
   * @param alpha alpha channel.
   * @param red   red channel.
   * @param green green channel.
   * @param blue  blue channel.
   * @return pixel value.
   */
  public static int getRGBAValue(int alpha, int red, int green, int blue) {
    return (alpha << 24) | (red << 16) | (green << 8) | blue;
  }

  /**
   * Returns the clamped value, if its beyond the limits.
   *
   * @param value - value to be clamped
   * @return - return value after clamping.
   */
  public static int clampValue(double value) {
    return (int) (value > 255 ? 255 : Math.max(0, value));
  }
}
