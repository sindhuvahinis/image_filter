package model;

import java.util.Objects;

/**
 * This class represents a point in 2D space.
 */
public final class Point {

  private final double X;
  private final double Y;

  /**
   * Initializes the object to a specified position.
   *
   * @param x - x co-ordinate of the point.
   * @param y - y co-ordinate of the point.
   */
  public Point(double x, double y) {
    X = x;
    Y = y;
  }

  /**
   * Copy constructor for point.
   *
   * @param point - the other object which has to be cloned.
   */
  public Point(Point point) {
    this(point.X, point.Y);
  }

  /**
   * getter for x-coordinate.
   *
   * @return - double value of x-coordinate.
   */
  public double getX() {
    return X;
  }

  /**
   * getter for y-coordinate.
   *
   * @return - double value of y-coordinate.
   */
  public double getY() {
    return Y;
  }

  /**
   * string representation of the point.
   *
   * @return - the string representation.
   */
  @Override
  public String toString() {
    return String.format("(%f %f )", X, Y);
  }

  /**
   * method to compare equality of other object with this object.
   *
   * @param other - the object to be compared.
   * @return - boolean result of equality.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }
    Point point = (Point) other;
    return Double.compare(point.X, X) == 0
            && Double.compare(point.Y, Y) == 0;
  }

  /**
   * returns the unique hash of object.
   *
   * @return - unique int hash value.
   */
  @Override
  public int hashCode() {
    return Objects.hash(X, Y);
  }
}
