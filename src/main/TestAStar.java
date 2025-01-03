package main;

import java.util.*;

public class TestAStar {

    public static void main(String[] args) {
        // Step 1: Create a graph
        Graph graph = new Graph();
        
        // Adding nodes
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        // Adding edges (from, to, weight)
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 4);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 6);
        graph.addEdge("C", "D", 3);
        graph.addEdge("C", "E", 5);
        graph.addEdge("D", "E", 2);

        // Step 2: Define heuristic map (straight-line distance to goal)
        Map<String, Double> heuristic = new HashMap<>();
        heuristic.put("A", 7.0);
        heuristic.put("B", 6.0);
        heuristic.put("C", 2.0);
        heuristic.put("D", 1.0);
        heuristic.put("E", 0.0); // Goal node has a heuristic of 0

        // Step 3: Create AStarAlgorithm instance
        AStarAlgorithm aStarAlgorithm = new AStarAlgorithm(graph);

        // Step 4: Find the shortest path using A* algorithm
        String startNode = "A";
        String goalNode = "E";

        List<String> shortestPath = aStarAlgorithm.findShortestPath(startNode, goalNode, heuristic);

        // Step 5: Print the result
        System.out.println("Shortest Path from " + startNode + " to " + goalNode + ": " + shortestPath);
    }
}
