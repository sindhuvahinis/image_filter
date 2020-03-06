package util;

import java.util.ArrayList;
import java.util.List;

import model.Point;

/**
 * Util class for the pixel co-ordinates.
 */
public class PointUtil {

  /**
   * Util function to convert indices of the pixel to 2d points.
   *
   * @param imageData - 2d image data as array.
   * @return list of 2d Points.
   */
  public static List<Point> convertIntArrayToListOfPoints(int[][] imageData) {
    List<Point> list = new ArrayList<>();
    for (int i = 0; i < imageData.length; i++) {
      for (int j = 0; j < imageData[0].length; j++) {
        list.add(new Point(i, j));
      }
    }

    return list;
  }

}
