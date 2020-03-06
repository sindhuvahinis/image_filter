package model;

/**
 * Represents the filter or color transformation model of the images.
 */
public interface ImageModel {
  /**
   * Applies the filter or transformation to the image.
   *
   * @param image - user given image.
   * @return filtered/transformed image.
   */
  Image apply(Image image);
}
