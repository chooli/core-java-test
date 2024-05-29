package core.java.algo.graph.simple;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraph {
    Map<Vertex, Map<Vertex, Integer>> map;

    public WeightedGraph() {
        map = new HashMap<>();
    }

    public Map<Vertex, Integer> getChildren(String label) {
        return map.get(new Vertex(label));
    }

    public Map<Vertex, Integer> getChildren(Vertex vertex) {
        return map.get(vertex);
    }

    public Vertex addVertex(String label) {
        Vertex vertex = new Vertex(label);
        map.putIfAbsent(vertex, new HashMap<>());
        return vertex;
    }

    public void removeVertex(String label) {
        map.remove(new Vertex(label));
    }

    public void addEdge(String fromLabel, String toLabel, int weight) {
        Vertex from = new Vertex(fromLabel);
        Vertex to = new Vertex(toLabel);

        if (map.containsKey(from)) {
            map.get(from).put(to, weight);
        } else {
            HashMap<Vertex, Integer> edgeMap = new HashMap<>();
            edgeMap.put(to, weight);
            map.put(from, edgeMap);
        }
    }

    public void removeEdge(String fromLabel, String toLabel) {
        Vertex from = new Vertex(fromLabel);
        Vertex to = new Vertex(toLabel);

        if (map.containsKey(from)) {
            map.get(from).remove(to);
        }
    }
}
