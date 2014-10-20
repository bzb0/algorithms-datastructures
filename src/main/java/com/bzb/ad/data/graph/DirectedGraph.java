package com.bzb.ad.data.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a directed graph comprised of set of vertices and edges.
 *
 * @param <V> The vertex type.
 * @param <E> The edge type.
 */
public class DirectedGraph<V, E> {

  private final Map<V, Map<V, E>> graph = new HashMap<>();

  public DirectedGraph(List<V> vertices) {
    vertices.forEach(this::addVertex);
  }

  /**
   * Adds a new vertex in the graph..
   *
   * @param vertex The vertex to be added in the graph.
   */
  public void addVertex(V vertex) {
    Objects.requireNonNull(vertex, "The provided vertex must not be null.");
    graph.putIfAbsent(vertex, new HashMap<>());
  }

  /**
   * Creates an edge between the source and the target vertex. If an edge between the vertices exist then the existing edge will be overwritten by the
   * new edge.
   *
   * @param source The start vertex.
   * @param target The destination vertex.
   * @throws NoSuchElementException thrown if one of the vertices doesn't exist.
   */
  public void addEdge(V source, V target, E edge) {
    Objects.requireNonNull(source, "The source vertex must not be null.");
    Objects.requireNonNull(target, "The target vertex must not be null.");
    Objects.requireNonNull(edge, "The edge must not be null.");
    if (!graph.containsKey(source) || !graph.containsKey(target)) {
      throw new NoSuchElementException("The source and target vertex must be in the graph.");
    }
    graph.get(source).put(target, edge);
  }

  /**
   * Removes the edge from the source to the target vertex.
   *
   * @param source The source vertex.
   * @param target The target vertex.
   * @throws NoSuchElementException thrown if one of the vertices doesn't exist.
   */
  public void removeEdge(V source, V target) {
    Objects.requireNonNull(source, "The source vertex must not be null.");
    Objects.requireNonNull(target, "The target vertex must not be null.");
    if (!graph.containsKey(source) || !graph.containsKey(target)) {
      throw new NoSuchElementException("The source and target vertex must be in the graph.");
    }
    graph.get(source).remove(target);
  }

  /**
   * Returns a set of the neighboring vertices of the given vertex.
   *
   * @param vertex The input vertex.
   * @return A set of edges leaving the vertex.
   * @throws NoSuchElementException thrown if the vertex doesn't exist.
   */
  public Set<V> getNeighborsForVertex(V vertex) {
    Objects.requireNonNull(vertex, "The vertex must not be null.");
    return Collections.unmodifiableSet(graph.getOrDefault(vertex, new HashMap<>()).keySet());
  }

  /**
   * Returns the edge between a source and a target vertex in the graph.
   *
   * @param source The source vertex.
   * @param target The target vertex.
   * @return The edge between the source and target vertex.
   * @throws NoSuchElementException thrown if one of the vertices doesn't exist.
   */
  public Optional<E> getEdge(V source, V target) {
    /* Confirm both endpoints exist. */
    if (!graph.containsKey(source) || !graph.containsKey(target)) {
      throw new NoSuchElementException("The source and target vertex must be in the graph.");
    }
    return Optional.ofNullable(graph.get(source).get(target));
  }

  /**
   * Returns a map of neighboring vertices and edges, that are leaving the given vertex.
   *
   * @param vertex The input vertex.
   * @return Map of neighboring vertices and edges.
   */
  public Map<V, E> getEdgesFrom(V vertex) {
    Objects.requireNonNull(vertex, "The vertex must not be null.");
    return Collections.unmodifiableMap(graph.getOrDefault(vertex, new HashMap<>()));
  }

  /**
   * Returns a set of all the vertices inside the graph.
   *
   * @return Iterable list of vertices.
   */
  public Set<V> getVertices() {
    return Collections.unmodifiableSet(graph.keySet());
  }
}
