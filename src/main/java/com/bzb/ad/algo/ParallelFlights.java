package com.bzb.ad.algo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ParallelFlights {

  private static class Flight {

    private final int start;
    private final int end;

    public Flight(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public static int maxParallelFlights(List<Flight> flights) {
    Collections.sort(flights, Comparator.comparingInt(f -> f.start));
    PriorityQueue<Flight> flightsInTheAir = new PriorityQueue((Comparator<Flight>) (f1, f2) -> f1.end - f2.end);

    int maxFlights = 0;
    for (Flight flight : flights) {
      while (!flightsInTheAir.isEmpty() && flight.start > flightsInTheAir.peek().end) {
        flightsInTheAir.poll();
      }
      flightsInTheAir.add(flight);
      maxFlights = Math.max(flightsInTheAir.size(), maxFlights);
    }
    return maxFlights;
  }
}
