package main;

import java.util.*;

/**
 * AStarAlgorithm class implements the A* search algorithm for shortest path.
 */
public class AStarAlgorithm {

    private Graph graph;

    // Constructor that accepts a Graph object
    public AStarAlgorithm(Graph graph) {
        this.graph = graph;
    }

    /**
     * Finds the shortest path between two nodes using A* algorithm.
     * 
     * @param graph      the graph to traverse
     * @param start      the starting node
     * @param goal       the target node
     * @param heuristic  a map of heuristic values for each node
     * @return a list representing the shortest path
     */
    public static List<String> findShortestPath(Graph graph, String start, String goal, Map<String, Double> heuristic) {
        Map<String, Double> gScore = new HashMap<>();
        Map<String, Double> fScore = new HashMap<>();
        Map<String, String> cameFrom = new HashMap<>();
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(node -> node.fScore));

        for (String node : graph.getAdjacencyList().keySet()) {
            gScore.put(node, Double.POSITIVE_INFINITY);
            fScore.put(node, Double.POSITIVE_INFINITY);
        }

        gScore.put(start, 0.0);
        fScore.put(start, heuristic.getOrDefault(start, 0.0));
        openSet.add(new Node(start, fScore.get(start)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.name.equals(goal)) {
                return reconstructPath(cameFrom, goal);
            }

            for (Graph.Edge edge : graph.getAdjacencyList().get(current.name)) {
                double tentativeGScore = gScore.get(current.name) + edge.weight;

                if (tentativeGScore < gScore.get(edge.to)) {
                    cameFrom.put(edge.to, current.name);
                    gScore.put(edge.to, tentativeGScore);
                    fScore.put(edge.to, tentativeGScore + heuristic.getOrDefault(edge.to, 0.0));

                    if (!openSet.contains(new Node(edge.to, fScore.get(edge.to)))) {
                        openSet.add(new Node(edge.to, fScore.get(edge.to)));
                    }
                }
            }
        }

        return new ArrayList<>(); // Return empty path if no path is found
    }

    private static List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        path.add(0, current); // Add start node
        return path;
    }

    /**
     * Node class for the priority queue, with a name and fScore.
     */
    private static class Node {
        String name;
        double fScore;

        public Node(String name, double fScore) {
            this.name = name;
            this.fScore = fScore;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return name.equals(node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
