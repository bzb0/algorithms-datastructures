package com.bzb.ad.algo.graph;

import com.bzb.ad.data.graph.DirectedGraph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class UniformCostSearch {

  /**
   * Implementation of the uniform-cost search algorithm. Returns the path with the lowest weight from the source to the target vertex.
   *
   * @param graph The graph to perform the uniform-cost search on.
   * @param source The source vertex.
   * @param target The target vertex.
   * @param <V> The vertex type.
   * @param <E> The edge type.
   * @return A path of vertices from the source to the target.
   */
  public static <V extends Comparable<V>, E extends WeightedEdge<V>> List<V> uniformCostSearch(DirectedGraph<V, E> graph, V source, V target) {
    PriorityQueue<UniformCostVertex<V>> queue = new PriorityQueue<>();
    Set<V> visited = new HashSet<>();

    queue.add(new UniformCostVertex<V>(source, 0, Collections.singletonList(source)));
    visited.add(source);

    while (!queue.isEmpty()) {
      UniformCostVertex<V> currentCostVertex = queue.remove();
      V currentVertex = currentCostVertex.getVertex();
      if (currentVertex.equals(target)) {
        return currentCostVertex.getPathVertices();
      }

      for (E neighborEdge : graph.getEdgesFrom(currentVertex).values()) {
        V neighbor = neighborEdge.getTarget();
        final UniformCostVertex<V> neighbourCost = queue.stream()
            .filter(v -> v.getVertex().equals(neighbor))
            .findAny()
            .orElse(null);

        if (!visited.contains(neighbor) && neighbourCost == null) {
          queue.add(createUniformCostVertex(currentCostVertex, neighborEdge));
          visited.add(neighbor);
        } else if (neighbourCost != null && neighbourCost.getCost() > (neighborEdge.getWeight() + currentCostVertex.getCost())) {
          // current path is shorter than previous path
          queue.remove(neighbourCost);
          queue.add(createUniformCostVertex(currentCostVertex, neighborEdge));
        }
      }
    }
    return Collections.emptyList();
  }

  /**
   * Creates a new uniform cost vertex based on the current cost vertex.
   *
   * @param source The current cost vertex in the graph traversal.
   * @param neighborEdge Holds the neighboring vertex and the weight of the edge.
   * @return A new uniform cost vertex for the neighboring vertex with a path that includes the neighboring vertex.
   */
  private static <V extends Comparable<V>, E extends WeightedEdge<V>> UniformCostVertex<V> createUniformCostVertex(
      UniformCostVertex<V> source, E neighborEdge) {
    List<V> path = new ArrayList<>(source.getPathVertices());
    path.add(neighborEdge.getTarget());
    return new UniformCostVertex<V>(neighborEdge.getTarget(), source.getCost() + neighborEdge.getWeight(), path);
  }
}
