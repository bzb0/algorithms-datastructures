package com.bzb.ad.algo.graph;

import com.bzb.ad.data.graph.DirectedGraph;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class UniformCostSearchTest {

  @Test
  public void uniformCostSearch_FindsShortestPathBetweenSAndG() {
    String source = "S";
    String target = "G";
    DirectedGraph<String, WeightedEdge<String>> graph = new DirectedGraph<>(Arrays.asList("A", "B", "C", "D", "G", "S"));
    graph.addEdge("S", "A", new WeightedEdge<>("S", "A", 2));
    graph.addEdge("S", "B", new WeightedEdge<>("S", "B", 3));
    graph.addEdge("A", "C", new WeightedEdge<>("A", "C", 2));
    graph.addEdge("A", "D", new WeightedEdge<>("A", "D", 4));
    graph.addEdge("B", "D", new WeightedEdge<>("B", "D", 1));
    graph.addEdge("B", "G", new WeightedEdge<>("B", "G", 5));
    graph.addEdge("D", "C", new WeightedEdge<>("D", "C", 3));
    graph.addEdge("D", "G", new WeightedEdge<>("D", "G", 2));

    List<String> result = UniformCostSearch.uniformCostSearch(graph, source, target);

    Assert.assertEquals(Arrays.asList("S", "B", "D", "G"), result);
  }

  @Test
  public void uniformCostSearch_FindsShortestPathBetweenSAndT() {
    String source = "S";
    String target = "T";
    DirectedGraph<String, WeightedEdge<String>> graph = new DirectedGraph<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "S", "T"));
    graph.addEdge("S", "A", new WeightedEdge<>("S", "A", 20));
    graph.addEdge("S", "B", new WeightedEdge<>("S", "B", 2));
    graph.addEdge("S", "C", new WeightedEdge<>("S", "C", 1));
    graph.addEdge("A", "G", new WeightedEdge<>("A", "G", 1));
    graph.addEdge("G", "T", new WeightedEdge<>("G", "T", 1));
    graph.addEdge("B", "D", new WeightedEdge<>("B", "D", 1));
    graph.addEdge("C", "D", new WeightedEdge<>("C", "D", 1));
    graph.addEdge("C", "E", new WeightedEdge<>("C", "E", 1));
    graph.addEdge("D", "F", new WeightedEdge<>("D", "F", 30));
    graph.addEdge("E", "F", new WeightedEdge<>("E", "F", 30));
    graph.addEdge("F", "T", new WeightedEdge<>("F", "T", 20));

    List<String> result = UniformCostSearch.uniformCostSearch(graph, source, target);

    Assert.assertEquals(Arrays.asList("S", "A", "G", "T"), result);
  }

  @Test
  public void uniformCostSearch_ReturnsEmptyListIfNoPathExists() {
    String source = "C";
    String target = "G";
    DirectedGraph<String, WeightedEdge<String>> graph = new DirectedGraph<>(Arrays.asList("C", "G"));

    List<String> result = UniformCostSearch.uniformCostSearch(graph, source, target);

    Assert.assertEquals(Collections.emptyList(), result);
  }
}
