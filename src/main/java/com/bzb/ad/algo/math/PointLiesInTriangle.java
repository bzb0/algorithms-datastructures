package com.bzb.ad.algo.math;

public class PointLiesInTriangle {

  public static class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  /**
   * Heron's formula for calculating triangle's area.
   */
  public static double area(Point p1, Point p2, Point p3) {
    return Math.abs((p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)) / 2.0);
  }

  /**
   * Checks if the point P lies inside the triangle defined by the points p1, p2 and p3.
   *
   * @param p1 Triangle point 1.
   * @param p2 Triangle point 2.
   * @param p3 Triangle point 3.
   * @param p The point.
   * @return <code>true</code>> if the point lies inside the triangle, <code>false</code> otherwise.
   */
  public static boolean isInside(Point p1, Point p2, Point p3, Point p) {
    /* Check if the area of ABC is same as the sum of PBC + APC + ABP  */
    return (area(p1, p2, p3) == area(p, p2, p3) + area(p1, p, p3) + area(p1, p2, p));
  }
}
