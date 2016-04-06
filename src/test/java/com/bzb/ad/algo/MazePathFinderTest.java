package com.bzb.ad.algo;

import org.junit.Assert;
import org.junit.Test;

public class MazePathFinderTest {

  @Test
  public void existsPath_findsAPathFromAStartAndEndPosition() {
    int[][] maze = new int[][]{new int[]{0, 1, 1, 0, 0, 0, 0}, new int[]{0, 1, 1, 0, 0, 0, 0}, new int[]{0, 0, 1, 1, 1, 0, 0},
        new int[]{0, 0, 1, 1, 1, 0, 0}, new int[]{0, 0, 1, 0, 0, 0, 0}, new int[]{0, 0, 1, 0, 0, 0, 0},
        new int[]{0, 0, 1, 1, 1, 1, 0}, new int[]{0, 0, 0, 0, 0, 0, 0}};

    Assert.assertTrue(MazePathFinder.existsPath(maze, 0, 0, 0, 6));
  }
}
