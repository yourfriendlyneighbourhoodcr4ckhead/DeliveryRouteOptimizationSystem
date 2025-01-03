package main;

import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to, double weight) {
        adjacencyList.get(from).add(new Edge(from, to, weight));
        adjacencyList.get(to).add(new Edge(to, from, weight)); // Assuming undirected graph
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public void displayGraph() {
        for (Map.Entry<String, List<Edge>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (Edge edge : entry.getValue()) {
                System.out.print(edge.getTo() + " (" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }

    // Method to update edge distance
    public void updateEdgeDistance(String from, String to, double newDistance) {
        for (Edge edge : adjacencyList.get(from)) {
            if (edge.getTo().equals(to)) {
                edge.setWeight(newDistance);
            }
        }
        for (Edge edge : adjacencyList.get(to)) {
            if (edge.getFrom().equals(from)) {
                edge.setWeight(newDistance);
            }
        }
    }

    // Edge class to represent edges between nodes
    public static class Edge {
        public String from;
        public String to;
        public double weight;

        public Edge(String from, String to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }
}
