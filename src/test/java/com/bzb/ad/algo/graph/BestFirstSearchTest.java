package com.bzb.ad.algo.graph;

import com.bzb.ad.data.graph.DirectedGraph;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class BestFirstSearchTest {

  @Test
  public void bestFirstSearch_FindsTheFirstPathFromSourceToTarget() {
    DirectedGraph<String, Double> graph = new DirectedGraph<>(Arrays.asList("A", "B", "C", "D", "G", "S"));
    graph.addEdge("S", "A", 1d);
    graph.addEdge("A", "S", 1d);
    graph.addEdge("S", "B", 1d);
    graph.addEdge("B", "S", 1d);
    graph.addEdge("A", "C", 1d);
    graph.addEdge("C", "A", 1d);
    graph.addEdge("A", "D", 1d);
    graph.addEdge("D", "A", 1d);
    graph.addEdge("B", "D", 1d);
    graph.addEdge("D", "B", 1d);
    graph.addEdge("B", "G", 1d);
    graph.addEdge("G", "B", 1d);
    graph.addEdge("D", "C", 1d);
    graph.addEdge("C", "D", 1d);
    graph.addEdge("D", "G", 1d);
    graph.addEdge("G", "D", 1d);
    graph.addEdge("C", "G", 1d);
    graph.addEdge("G", "C", 1d);

    List<String> path = BestFirstSearch.bestFirstSearch(graph, "S", "G");

    Assert.assertEquals(Arrays.asList("S", "B", "G"), path);
  }
}
