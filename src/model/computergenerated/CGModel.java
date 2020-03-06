package model.computergenerated;

import model.Image;

/**
 * This interface represents model that create computer generated images such as, flags,
 * checkerboard pattern and rainbow pattern.
 */
public interface CGModel {

  /**
   * Generates the specified computer generated image.
   *
   * @return Image, output image
   */
  Image generate();

}
