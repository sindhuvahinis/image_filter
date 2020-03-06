package model.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import model.Image;
import model.ImageModel;
import model.Point;
import util.ColorUtil;
import util.PointUtil;

/**
 * Applies mosaic filter to the given image. For the given seeds, mosaic algorithm is applied.
 */
public class Mosaic implements ImageModel {

  private int seeds;

  /**
   * Takes the number of seeds as the parameter and sets it.
   *
   * @param seeds - number of seeds.
   */
  public Mosaic(int seeds) {
    this.seeds = seeds;
  }

  /**
   * Mosaic algorithm goes as follows: 1. Send the image data array to kmeans-clustering model to
   * get the cluster map. 2. Get the average color of each cluster and update the whole points in
   * the cluster with the same pixel value.
   *
   * @param image - user given image.
   * @return mosaic filtered image.
   */
  @Override
  public Image apply(Image image) {
    int[][] imageData = image.getData();
    List<Point> data = PointUtil.convertIntArrayToListOfPoints(imageData);
    Map<Point, List<Point>> map = computeResult(data);

    for (Map.Entry<Point, List<Point>> entry : map.entrySet()) {
      Point pointKey = entry.getKey();
      List<Point> list = entry.getValue();

      int averageColor = getAverage(list, imageData);
      imageData[(int) pointKey.getX()][(int) pointKey.getY()] = averageColor;

      for (Point point : list) {
        imageData[(int) point.getX()][(int) point.getY()] = averageColor;
      }

    }

    return new Image(image.getFileName(), image.getFormat(), imageData, image.getType(),
            image.getWidth(), image.getHeight());
  }


  /******************************* PRIVATE HELPER METHODS. *******************************/
  private int getAverage(List<Point> list, int[][] imageData) {

    int redSum = 0;
    int alphaSum = 0;
    int blueSum = 0;
    int greenSum = 0;

    for (Point point : list) {
      int pixelColor = imageData[(int) point.getX()][(int) point.getY()];
      int redChannel = ColorUtil.getRedChannel(pixelColor);
      int blueChannel = ColorUtil.getBlueChannel(pixelColor);
      int greenChannel = ColorUtil.getGreenChannel(pixelColor);
      int alphaChannel = ColorUtil.getAlphaChannel(pixelColor);
      redSum += redChannel;
      blueSum += blueChannel;
      greenSum += greenChannel;
      alphaSum += alphaChannel;
    }

    int alphaAverage = alphaSum / list.size();
    int redAverage = redSum / list.size();
    int blueAverage = blueSum / list.size();
    int greenAverage = greenSum / list.size();

    return ColorUtil.getRGBAValue(alphaAverage, redAverage, greenAverage, blueAverage);
  }

  // does the k-means algorithm without iteration
  private Map<Point, List<Point>> computeResult(List<Point> data) {

    List<Point> centers = getRandomKPoints(data);
    Map<Integer, List<Point>> clusters = new HashMap<>();
    classifyClusters(centers, clusters, data);

    Map<Point, List<Point>> clusterMap = new HashMap<>();
    for (Integer i : clusters.keySet()) {
      clusterMap.put(centers.get(i), clusters.get(i));
    }
    return clusterMap;
  }

  // gets the random k points before training the model
  private List<Point> getRandomKPoints(List<Point> data) {
    Random random = new Random();
    Set<Integer> hashSet = new HashSet<>();
    while (hashSet.size() < seeds) {
      hashSet.add(random.nextInt(data.size()));
    }
    return hashSet.stream().map(data::get).collect(Collectors.toList());
  }

  // calc distance between two points
  private double getDistance(Point a, Point b) {
    return Math.sqrt(Math.pow(a.getY() - b.getY(), 2) + Math.pow(a.getX() - b.getX(), 2));
  }

  // classify the clusters to either of the k centers
  private void classifyClusters(List<Point> centers, Map<Integer, List<Point>> clusters,
                                List<Point> data) {
    for (Point point : data) {
      double minDistance = Double.POSITIVE_INFINITY;
      int index = 0;
      for (int i = 0; i < centers.size(); i++) {
        Point center = centers.get(i);
        double distance = getDistance(point, center);
        if (distance < minDistance) {
          minDistance = distance;
          index = i;
        }
      }
      if (clusters.get(index) != null) {
        List<Point> tempList = clusters.get(index);
        tempList.add(point);
        clusters.put(index, tempList);
      } else {
        List<Point> tempList = new ArrayList<>();
        tempList.add(point);
        clusters.put(index, tempList);
      }
    }
  }

}
