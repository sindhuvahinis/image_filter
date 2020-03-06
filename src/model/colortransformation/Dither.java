package model.colortransformation;

import model.Image;
import model.ImageModel;
import util.ColorUtil;

/**
 * Dithers the given image by using Floyd–Steinberg_dithering algorithm.
 */
public class Dither implements ImageModel {


  /**
   * Initially, image is converted to greyscale, and then, Floyd–Steinberg_dithering algorithm is
   * applied. i.e, new color is extracted from either one of the three channels and set.
   *
   * @param image - user given image.
   * @return - dithered image
   */
  @Override
  public Image apply(Image image) {

    //apply greyscale
    ImageModel greyScale = new GreyScale();
    Image greyScaleImage = greyScale.apply(image);

    int[][] imageData = greyScaleImage.getData();
    int imageWidth = greyScaleImage.getWidth();
    int imageHeight = greyScaleImage.getHeight();

    for (int y = 0; y < imageHeight; y++) {
      for (int x = 0; x < imageWidth; x++) {
        int alpha = ColorUtil.getAlphaChannel(imageData[y][x]);

        int oldRedColor = ColorUtil.getRedChannel(imageData[y][x]);
        int newRedColor = Math.min(oldRedColor, 255 - oldRedColor) == oldRedColor ? 0 : 255;

        int error = oldRedColor - newRedColor;
        imageData[y][x] = ColorUtil.getRGBAValue(alpha, newRedColor, newRedColor, newRedColor);

        if (x + 1 < imageWidth) {
          int q = x + 1;
          imageData[y][q] = applyErrorFunction(imageData[y][q], error, 7.0 / 16);
        }
        if (y + 1 < imageHeight && x - 1 >= 0) {
          int p = y + 1;
          int q = x - 1;
          imageData[p][q] = applyErrorFunction(imageData[p][q], error, 3.0 / 16);
        }
        if (y + 1 < imageHeight) {
          int p = y + 1;
          imageData[p][x] = applyErrorFunction(imageData[p][x], error, 5.0 / 16);
        }
        if (y + 1 < imageHeight && x + 1 < imageWidth) {
          int p = y + 1;
          int q = x + 1;
          imageData[p][q] = applyErrorFunction(imageData[p][q], error, 1.0 / 16);

        }
      }
    }
    return new Image(image, imageData);
  }

  private int applyErrorFunction(int imageData, int error, double fraction) {
    int redChannel = ColorUtil.getRedChannel(imageData);
    redChannel = ColorUtil.clampValue((redChannel + (fraction * error)));
    return ColorUtil.getRGBAValue(ColorUtil.getAlphaChannel(imageData),
            redChannel, ColorUtil.getGreenChannel(imageData),
            ColorUtil.getBlueChannel(imageData));
  }

}
