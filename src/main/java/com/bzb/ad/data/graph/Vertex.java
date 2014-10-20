package com.bzb.ad.data.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a single vertex inside the graph.
 */
public class Vertex<T, W extends Number> {
	
	private final T item;
	private final Map<Vertex<T, W>, W> neighbors = new HashMap<>();
	
	public Vertex(T item) {
		Objects.requireNonNull(item, "The provided item must not be null.");
		this.item = item;
	}
	
	public T getItem() {
		return item;
	}
	
	public Iterable<Vertex<T, W>> getNeighbors() {
		return neighbors.keySet();
	}

	public void addNeighbor(Vertex<T, W> vertex, W weight) {
		neighbors.putIfAbsent(vertex, weight);
	}
	
	public void removeNeighbor(Vertex<T, W> neighbor) {
		neighbors.remove(neighbor);
	}

	/**
	 * Returns the weight of the edge for the given neighbor.
	 *
	 * @param neighbor The neighbor if this vertex.
	 * @return
	 */
	public W getWeight(Vertex<T, W> neighbor) {
		return neighbors.get(neighbor);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Vertex<?, ?> vertex = (Vertex<?, ?>) o;
		return Objects.equals(item, vertex.item);
	}

	@Override
	public int hashCode() {
		return Objects.hash(item);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Vertex[");
		sb.append("item=").append(item);
		sb.append(", neighbors=").append(neighbors);
		sb.append("]");
		return sb.toString();
	}
}
