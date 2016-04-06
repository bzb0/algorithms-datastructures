package com.bzb.ad.algo;

public class MazePathFinder {

  /**
   * Checks if a path between a starting and target position in a maze exist. Value 0 means an empty position/field (traversable field) and 1 means
   * non-traversable field (obstacle).
   *
   * @param maze The maze.
   * @param startX Starting position (x coordinate).
   * @param startY Starting position (x coordinate).
   * @param destX Target position (x coordinate).
   * @param destY Target position (x coordinate).
   * @return <code>true</code> if there is a path from the start to the target position, <code>false</code> otherwise.
   */
  public static boolean existsPath(int[][] maze, int startX, int startY, int destX, int destY) {
    // Allowed moves
    Point[] moves = new Point[]{new Point(-1, 0), new Point(0, -1), new Point(1, 0), new Point(0, 1)};

    // Marking the start position as visited
    maze[startX][startY] = 2;
    if (existPath0(startX, startY, maze, moves, destX, destY)) {
      maze[startX][startY] = 3;
      return true;
    }
    return false;
  }

  /**
   * Recursive functions, which searches for a path from a start position to a target position.
   *
   * @param x Start position (x coordinate).
   * @param y Start position (y coordinate).
   * @param maze The maze.
   * @param moves List with possible moves.
   * @param destX Target position (x coordinate).
   * @param destY Target position (y coordinate).
   * @return <code>true</code> if a path exist between the start and the end position, <code>false</code> otherwise.
   */
  private static boolean existPath0(int x, int y, int[][] maze, Point[] moves, int destX, int destY) {
    // We have reached the destination
    if (x == destX && y == destY) {
      return true;
    }

    boolean pathFound = false;
    for (int k = 0; k < moves.length; k++) {
      int nextX = x + moves[k].x;
      int nextY = y + moves[k].y;

      if (isMovePlausible(nextX, nextY, maze)) {
        // Marking the field as visited
        maze[nextX][nextY] = 2;
        pathFound |= true;
        // Marking a position as part of the solution
        if (existPath0(nextX, nextY, maze, moves, destX, destY)) {
          maze[nextX][nextY] = 3;
        }
      } else {
        pathFound |= false;
      }
    }

    if (pathFound) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the move is plausible.
   *
   * @param x The X coordinate.
   * @param y The Y coordinate.
   * @param maze The maze.
   * @return <code>true</code> if the move is allowed/plausible, <code>false</code> otherwise.
   */
  private static boolean isMovePlausible(int x, int y, int maze[][]) {
    if ((x >= 0 && x < maze.length) && (y >= 0 && y < maze[0].length) && maze[x][y] == 0) {
      return true;
    }
    return false;
  }

  private static class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
      super();
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      final StringBuilder sb = new StringBuilder("Point [");
      sb.append("x=").append(x);
      sb.append(", y=").append(y);
      sb.append("]");
      return sb.toString();
    }
  }
}
