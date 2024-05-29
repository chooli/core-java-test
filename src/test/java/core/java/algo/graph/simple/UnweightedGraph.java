package core.java.algo.graph.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnweightedGraph {

    private final Map<Vertex, List<Vertex>> adjacentVertices;

    public UnweightedGraph() {
        adjacentVertices = new HashMap<>();
    }

    public List<Vertex> getDirectVertices(String label) {
        return adjacentVertices.get(new Vertex(label));
    }

    public void addVertex(String label) {
        adjacentVertices.putIfAbsent(new Vertex(label),  new ArrayList<>());
    }

    public void removeVertex(String label) {
        Vertex val = new Vertex(label);
        adjacentVertices.values().forEach(lst -> lst.remove(val));
        adjacentVertices.remove(new Vertex(label));
    }

    public Vertex addEdge(String fromLabel, String toLabel) {
        Vertex from = new Vertex(fromLabel);
        Vertex to = new Vertex(toLabel);
        //bidirectional
        adjacentVertices.get(from).add(to);
        adjacentVertices.get(to).add(from);
        return from;
    }

    public Vertex removeEdge(String fromLabel, String toLabel) {
        Vertex from = new Vertex(fromLabel);
        Vertex to = new Vertex(toLabel);
        //bidirectional
        if (adjacentVertices.get(from) != null) adjacentVertices.get(from).remove(to);
        if (adjacentVertices.get(to) != null) adjacentVertices.get(to).remove(from);
        return to;
    }
}
