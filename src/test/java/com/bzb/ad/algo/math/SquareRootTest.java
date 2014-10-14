package com.bzb.ad.algo.math;

import org.junit.Assert;
import org.junit.Test;

public class SquareRootTest {

  @Test
  public void squareRootSmallNumber() {
    double val = 100;
    double result = SquareRoot.squareRoot(val);
    Assert.assertEquals(10.0, result, 0.0000001);
  }

  @Test
  public void squareRootBigNumber() {
    double val = 244703449;
    double result = SquareRoot.squareRoot(val);
    Assert.assertEquals(15643, result, 0.0000001);
  }
}
