package main;

import java.util.*;

/**
 * Graph class represents a weighted graph using an adjacency list.
 */
public class Graph {

    // Map to store the adjacency list, with nodes as keys and a list of edges as values
    private Map<String, List<Edge>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a new node to the graph.
     * 
     * @param node the name of the node to be added
     */
    public void addNode(String node) {
        if (!adjacencyList.containsKey(node)) {
            adjacencyList.put(node, new ArrayList<>());
        } else {
            System.out.println("Node " + node + " already exists.");
        }
    }

    /**
     * Adds a directed edge with a weight between two nodes.
     * 
     * @param from   the starting node of the edge
     * @param to     the ending node of the edge
     * @param weight the weight of the edge
     */
    public void addEdge(String from, String to, double weight) {
        if (!adjacencyList.containsKey(from)) {
            System.out.println("Node " + from + " does not exist.");
            return;
        }
        if (!adjacencyList.containsKey(to)) {
            System.out.println("Node " + to + " does not exist.");
            return;
        }
        adjacencyList.get(from).add(new Edge(to, weight));
    }

    /**
     * Returns the adjacency list representation of the graph.
     * 
     * @return the adjacency list
     */
    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    /**
     * Displays the graph as an adjacency list.
     */
    public void displayGraph() {
        for (String node : adjacencyList.keySet()) {
            System.out.print(node + " -> ");
            for (Edge edge : adjacencyList.get(node)) {
                System.out.print("(" + edge.to + ", " + edge.weight + ") ");
            }
            System.out.println();
        }
    }

    /**
     * Edge class represents a weighted edge in the graph.
     */
    public static class Edge {
        String to;
        double weight;

        public Edge(String to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        // Adding nodes
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        // Adding edges
        graph.addEdge("A", "B", 4.5);
        graph.addEdge("A", "C", 2.0);
        graph.addEdge("B", "C", 1.5);
        graph.addEdge("C", "D", 3.0);

        // Displaying the graph
        graph.displayGraph();
    }
}
