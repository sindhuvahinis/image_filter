package model.colortransformation;

import model.Image;
import model.ImageModel;
import util.ColorUtil;

/**
 * Transformation the color tone of the image to SepiaTone. Implements the ImageModel interface.
 */
public class SepiaTone implements ImageModel {

  private final double[][] transformationMatrix;

  /**
   * Initializes the transformation matrix to be applied to the image.
   */
  public SepiaTone() {
    transformationMatrix =
            new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
  }

  /**
   * Applies the transformation matrix to the image.
   *
   * @param image - user given image.
   * @return - transformed sepiatone image.
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
        double newRedDouble = red * transformationMatrix[0][0] + green * transformationMatrix[0][1]
                + blue * transformationMatrix[0][2];
        double newGreenDouble = red * transformationMatrix[1][0]
                + green * transformationMatrix[1][1] + blue * transformationMatrix[1][2];
        double newBlueDouble = red * transformationMatrix[2][0]
                + green * transformationMatrix[2][1] + blue * transformationMatrix[2][2];

        int newRed = ColorUtil.clampValue(newRedDouble);
        int newGreen = ColorUtil.clampValue(newGreenDouble);
        int newBlue = ColorUtil.clampValue(newBlueDouble);

        int alpha = ColorUtil.getAlphaChannel(imageData[i][j]);
        int pixel = ColorUtil.getRGBAValue(alpha, newRed, newGreen, newBlue);
        result[i][j] = pixel;
      }
    }
    return new Image(image.getFileName(), image.getFormat(), result, image.getType(),
            image.getWidth(), image.getHeight());
  }

}
