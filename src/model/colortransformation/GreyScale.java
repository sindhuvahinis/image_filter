package model.colortransformation;

import model.Image;
import model.ImageModel;
import util.ColorUtil;

/**
 * Transformation the color tone of the image to SepiaTone. Implements the ImageModel interface.
 */
public class GreyScale implements ImageModel {

  private final double redCoefficient = 0.2126;
  private final double greenCoefficient = 0.7152;
  private final double blueCoefficient = 0.0722;

  /**
   * Applies the transformation coefficient to the each channel of the image.
   *
   * @param image - user given image.
   * @return - transformed greyscale image.
   */
  @Override
  public Image apply(Image image) {
    int[][] imageData = image.getData();
    int[][] result = new int[image.getHeight()][image.getWidth()];

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int red = ColorUtil.getRedChannel(imageData[i][j]);
        int green = ColorUtil.getGreenChannel(imageData[i][j]);
        int blue = ColorUtil.getBlueChannel(imageData[i][j]);
        double newPixelDouble =
                red * redCoefficient + green * greenCoefficient + blue * blueCoefficient;
        int newPixelVal = ColorUtil.clampValue(newPixelDouble);

        int alpha = ColorUtil.getAlphaChannel(imageData[i][j]);
        int pixel = ColorUtil.getRGBAValue(alpha, newPixelVal, newPixelVal, newPixelVal);
        result[i][j] = pixel;
      }
    }
    return new Image(image.getFileName(), image.getFormat(), result, image.getType(),
            image.getWidth(), image.getHeight());
  }
}
