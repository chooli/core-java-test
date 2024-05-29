package core.java.algo.graph.simple;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GraphTest extends CommonTest {

    UnweightedGraph friends;
    WeightedGraph wGraph;

    @BeforeAll
    void setUp() {
        friends = new UnweightedGraph();
        friends.addVertex("Bob");
        friends.addVertex("Alice");
        friends.addVertex("Mark");
        friends.addVertex("Rob");
        friends.addVertex("Maria");

        friends.addEdge("Bob", "Rob");
        friends.addEdge("Alice", "Mark");
        friends.addEdge("Mark", "Rob");
        friends.addEdge("Alice", "Maria");
        friends.addEdge("Rob", "Maria");

        wGraph = new WeightedGraph();
        wGraph.addVertex("start");
        wGraph.addVertex("a");
        wGraph.addVertex("b");
        wGraph.addVertex("c");
        wGraph.addVertex("d");
        wGraph.addVertex("end");

        wGraph.addEdge("start", "a", 10);
        wGraph.addEdge("a", "b", 20);
        wGraph.addEdge("b", "c", 1);
        wGraph.addEdge("c", "a", 1);
        wGraph.addEdge("b", "end", 30);
    }

    @Test
    void cheapestPath() {

        Map<String, Integer> costMap = new HashMap<>();
        Map<Vertex, Vertex> parentMap = new HashMap<>();

        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex("start"));
        wGraph.getChildren("start");
        costMap.put("start", 0);

        bfsTraverse(vertices, costMap, parentMap);
        println("cheapest cost: " + costMap.get("end"));
        assertTrue(true);
    }

    void bfsTraverse(List<Vertex> vertices, Map<String, Integer> costMap, Map<Vertex, Vertex> parentMap){

        List<Vertex> children = new ArrayList<>();
        for (Vertex vertex : vertices) {
            Integer parentCost = costMap.get(vertex.label);
            Map<Vertex, Integer> childMap = wGraph.getChildren(vertex);
            children.addAll(childMap.keySet());
            for (Vertex child : childMap.keySet()) {
                parentMap.put(child, vertex);
                Integer childWeight = childMap.get(child);
                Integer newCost = childWeight + parentCost;
                Integer weight = costMap.putIfAbsent(child.label, newCost);
                if (weight != null) {
                    if (newCost < weight) costMap.put(child.label, newCost);
                }
            }
        }
        if (!children.isEmpty()) bfsTraverse(children, costMap, parentMap);
    }

    @Test
    void shortestConnection() {

        String start = "Bob";
        String end = "Alice";
        int count = 0;
        Set<Vertex> visited = new HashSet<>();
        count = bfsConnection(friends.getDirectVertices("Bob"), end, count, visited);
        println("count: " + count);
        assertTrue(true);
    }

    private int bfsConnection(List<Vertex> vertices, String endLabel, int count, Set<Vertex> visited) {

        count++;
        for (Vertex friend : vertices) {
            if (!visited.contains(friend)) {
                visited.add(friend);
                if (friend.hasLabel(endLabel)) {
                    return count;
                }
                else return bfsConnection(friends.getDirectVertices(friend.label), endLabel, count, visited);
            }
        }
        return count;
    }
}
