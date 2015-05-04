package com.bzb.ad.algo.graph;

import java.util.List;
import java.util.Objects;

/**
 * Vertex wrapper class, that is used by the {@link UniformCostSearch} algorithm. A {@link UniformCostVertex} wraps a vertex and additionally stores
 * the traversal cost to this vertex and the path to this vertex.
 *
 * @param <V> The vertex type.
 */
class UniformCostVertex<V> implements Comparable<UniformCostVertex<V>> {

  private final V vertex;
  private final int cost;
  private final List<V> pathVertices;

  public UniformCostVertex(final V vertex, final int cost, final List<V> pathVertices) {
    this.vertex = vertex;
    this.cost = cost;
    this.pathVertices = pathVertices;
  }

  /**
   * Returns the wrapped vertex.
   *
   * @return The wrapped vertex.
   */
  public V getVertex() {
    return vertex;
  }

  /**
   * The traversal cost to wrapped vertex.
   *
   * @return Traversal cost to wrapped vertex.
   */
  public int getCost() {
    return cost;
  }

  /**
   * A traversal path of vertices to the wrapped vertex.
   *
   * @return List of vertices that lead to the wrapped vertex.
   */
  public List<V> getPathVertices() {
    return pathVertices;
  }

  public int compareTo(UniformCostVertex<V> other) {
    return Integer.compare(this.cost, other.cost);
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UniformCostVertex)) {
      return false;
    }
    UniformCostVertex<?> that = (UniformCostVertex<?>) o;
    return Objects.equals(vertex, that.vertex);
  }

  public int hashCode() {
    return Objects.hash(vertex);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("UniformCostVertex [");
    sb.append("vertex=").append(vertex);
    sb.append(", cost=").append(cost);
    sb.append(", pathVertices=").append(pathVertices);
    sb.append("]");
    return sb.toString();
  }
}
