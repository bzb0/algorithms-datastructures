package com.bzb.ad.algo.graph;

import java.util.Objects;

/**
 * Represents a directed weighted edge in a directed graph.
 *
 * @param <V> The type of the source and target vertices.
 */
class WeightedEdge<V> {

  private final V source;
  private final V target;
  private final int weight;

  public WeightedEdge(final V source, final V target, final int weight) {
    this.source = source;
    this.target = target;
    this.weight = weight;
  }

  /**
   * Returns the source vertex of the edge.
   *
   * @return The source vertex.
   */
  public V getSource() {
    return source;
  }

  /**
   * Returns the target vertex of the edge.
   *
   * @return The target vertex.
   */
  public V getTarget() {
    return target;
  }

  /**
   * Returns the edge weight.
   *
   * @return The edge of the weight.
   */
  public int getWeight() {
    return weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WeightedEdge)) {
      return false;
    }
    WeightedEdge<?> that = (WeightedEdge<?>) o;
    return Objects.equals(source, that.source) && Objects.equals(target, that.target);
  }

  @Override
  public int hashCode() {
    return Objects.hash(source, target);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("WeightedEdge [");
    sb.append("source=").append(source);
    sb.append(", target=").append(target);
    sb.append(", weight=").append(weight);
    sb.append("]");
    return sb.toString();
  }
}
