package main;

import java.util.*;

/**
 * KruskalAlgorithm class implements Kruskal's algorithm to find the Minimum Spanning Tree (MST)
 * for optimizing the delivery network.
 */
public class KruskalAlgorithm {

    private Graph graph;

    // Constructor that accepts a Graph object
    public KruskalAlgorithm(Graph graph) {
        this.graph = graph;
    }


    // Union-Find data structure for cycle detection
    static class UnionFind {
        private Map<String, String> parent;
        private Map<String, Integer> rank;

        public UnionFind() {
            parent = new HashMap<>();
            rank = new HashMap<>();
        }

        public void add(String node) {
            parent.put(node, node);
            rank.put(node, 0);
        }

        public String find(String node) {
            if (!parent.get(node).equals(node)) {
                parent.put(node, find(parent.get(node))); // Path compression
            }
            return parent.get(node);
        }

        public void union(String node1, String node2) {
            String root1 = find(node1);
            String root2 = find(node2);

            if (!root1.equals(root2)) {
                // Union by rank
                if (rank.get(root1) > rank.get(root2)) {
                    parent.put(root2, root1);
                } else if (rank.get(root1) < rank.get(root2)) {
                    parent.put(root1, root2);
                } else {
                    parent.put(root2, root1);
                    rank.put(root1, rank.get(root1) + 1);
                }
            }
        }
    }

    // Kruskal's algorithm to find the Minimum Spanning Tree (MST)
    public static List<Graph.Edge> kruskal(Graph graph) {
        List<Graph.Edge> mst = new ArrayList<>();
        List<Graph.Edge> edges = new ArrayList<>();
        
        // Step 1: Add all edges to the list
        for (String node : graph.getAdjacencyList().keySet()) {
            for (Graph.Edge edge : graph.getAdjacencyList().get(node)) {
                edges.add(edge);
            }
        }

        // Step 2: Sort edges by weight (distance or delivery time)
        edges.sort(Comparator.comparingDouble(edge -> edge.weight));

        // Step 3: Initialize Union-Find structure
        UnionFind uf = new UnionFind();
        for (String node : graph.getAdjacencyList().keySet()) {
            uf.add(node);
        }

        // Step 4: Process edges in sorted order and apply Union-Find to avoid cycles
        for (Graph.Edge edge : edges) {
            String from = edge.to; // 'from' is stored in 'to' as the destination node in Edge
            String to = edge.to;

            // If the nodes are in different sets, add the edge to MST
            if (!uf.find(from).equals(uf.find(to))) {
                uf.union(from, to);
                mst.add(edge);
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        // Create the graph
        Graph graph = new Graph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        // Add edges (roads with weights)
        graph.addEdge("A", "B", 4.5);
        graph.addEdge("A", "C", 2.0);
        graph.addEdge("B", "C", 1.5);
        graph.addEdge("C", "D", 3.0);
        graph.addEdge("B", "D", 5.0);

        // Run Kruskal's algorithm to find the MST
        List<Graph.Edge> mst = kruskal(graph);

        // Output the MST (Optimized delivery routes)
        System.out.println("Optimized delivery network (Minimum Spanning Tree):");
        for (Graph.Edge edge : mst) {
            System.out.println(edge.to + " -> " + edge.weight);
        }
    }
}
