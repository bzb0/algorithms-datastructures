package com.bzb.ad.algo.graph;

import com.bzb.ad.data.graph.DirectedGraph;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class BestFirstSearch {

  /**
   * Implementation of the Best First Search algorithm.
   *
   * @param graph The input graph.
   * @param source The source vertex.
   * @param target The target vertex.
   * @param <V> The vertex type.
   * @param <E> The edge type.
   * @return A path of vertices from the source to the target.
   */
  public static <V extends Comparable<V>, E> List<V> bestFirstSearch(DirectedGraph<V, E> graph, V source, V target) {
    PriorityQueue<V> queue = new PriorityQueue<>();
    HashSet<V> visited = new HashSet<>();

    queue.add(source);
    visited.add(source);

    List<V> pathNodes = new ArrayList<>();
    while (!queue.isEmpty()) {
      V next = queue.remove();
      pathNodes.add(next);
      if (next.equals(target)) {
        break;
      }

      for (V neighbour : graph.getNeighborsForVertex(next)) {
        if (!visited.contains(neighbour)) {
          visited.add(neighbour);
          queue.add(neighbour);
        }
      }
    }

    if (pathNodes.contains(target)) {
      List<V> path = new ArrayList<>();
      path.add(target);
      V curr = target;
      while (!curr.equals(source)) {
        for (V next : graph.getNeighborsForVertex(curr)) {
          if (pathNodes.contains(next)) {
            curr = next;
            path.add(next);
            break;
          }
        }
      }
      Collections.reverse(path);
      return path;
    }
    return Collections.emptyList();
  }
}
